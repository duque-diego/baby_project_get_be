package app.getfraldas.service.impl;

import app.getfraldas.DTO.*;
import app.getfraldas.exception.SASServiceException;
import app.getfraldas.models.*;
import app.getfraldas.repository.*;
import app.getfraldas.service.IPromocaoService;
import app.getfraldas.utils.DateUtils;
import app.getfraldas.utils.OneSignalUtil;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;
import java.util.logging.Logger;

@Service
public class PromocaoService implements IPromocaoService {

    private static final Logger LOGGER = Logger.getLogger(PromocaoService.class.getName());
    private static final String TIMEZONE_BRT = "America/Sao_Paulo";
    private static final String TIMEZONE_UTC = "UTC";

    @Autowired
    private PromocaoRepository promocaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LojaRepository lojaRepository;

    @Autowired
    private ModeloRepository modeloRepository;

    @Autowired
    private TamanhoRepository tamanhoRepository;

    @Autowired
    private UsuarioPushAppRepository usuarioPushAppRepository;

    @Autowired
    private CronService cronService;

    @Override
    public Optional<Promocao> getPromocao(Long id) {
        return promocaoRepository.findById(id);
    }

    @Override
    public Iterable<Promocao> getPromocoes() {
        return promocaoRepository.findByAtivoTrue();
    }

    @Override
    public Iterable<Promocao> getLastPromocoes(Date minDate) {
        return null;
    }

    @Override
    public Promocao savePromocao(Promocao promocao) {

        promocao.setLastUpdate(new Date());
//        if(promocao == null){
//
//            promocao.setValorPacote(Double.parseDouble(df2.format(promocao.getValorPacote())));
//            promocao.setValorUnidade(Double.parseDouble(df2.format(promocao.getValorUnidade())));
//
//        }
        DecimalFormat df2 = new DecimalFormat(".##");
        promocao.setValorPacote(Double.parseDouble(df2.format(promocao.getValorPacote())));
        promocao.setValorUnidade(Double.parseDouble(df2.format(promocao.getValorUnidade())));
        return promocaoRepository.save(promocao);

    }

    public DadosPromocaoDTO getDadosPromocao(){
        Iterable<Loja> lojas = lojaRepository.findAll();
        Iterable<Modelo> modelos = modeloRepository.findAll();
        Iterable<Tamanho> tamanhos = tamanhoRepository.findAll();
        DadosPromocaoDTO dadosPromocaoDTO = new DadosPromocaoDTO();
        dadosPromocaoDTO.setLojas(lojas);
        dadosPromocaoDTO.setModelos(modelos);
        dadosPromocaoDTO.setTamanhos(tamanhos);
        return dadosPromocaoDTO;
    }

    public void enviaPushPromocoes() throws SASServiceException {
        CronHistory cronHistory = cronService.getLastCron();
        Date formattedDate = DateUtils.corrigeTimezone(cronHistory.getDoneDate(), TIMEZONE_UTC);

        LOGGER.info("Buscando promoções com datas > " + formattedDate.getTime());
        List<Promocao> promocaoList = promocaoRepository.findByLastUpdateGreaterThanEqual(formattedDate);

        if (promocaoList != null && promocaoList.size() > 0) {
            LOGGER.info("Promoções encontradas: " + promocaoList.toString());

            List<Usuario> usuarios = getUsersToSendNotification(promocaoList);

            LOGGER.info("Buscando usuários");

            if (usuarios != null && usuarios.size() > 0) {
                Iterator<Usuario> usuarioIterator = usuarios.iterator();
                List<Filter> filterList = new ArrayList<>();

                while(usuarioIterator.hasNext()) {
                    Usuario usuario = usuarioIterator.next();
                    Filter filter = new Filter();
                    filter.setField("tag");
                    filter.setKey("email");
                    filter.setRelation("=");
                    filter.setValue(usuario.getEmail());

                    LOGGER.info("Usuário encontrado: " + usuario.getEmail());

                    if(usuarioIterator.hasNext()){
                        filter.setOperator("OR");
                    } else {
                        filter.setOperator("null");
                    }

                    filterList.add(filter);
                    UsuarioPushAppControleDTO usuarioPushAppControleDTO = new UsuarioPushAppControleDTO();
                    usuarioPushAppControleDTO.setFilterList(filterList);
                    usuarioPushAppControleDTO.setPushTerminated(false);
                    usuarioPushAppRepository.put(usuarioPushAppControleDTO);
                }

//                try{
//                    //dispara Push
//                    if(filterList.size() > 0){
//                        Contents contents = OneSignalUtil.montaContentOneSignal("Temos promoções de fraldas para você.");
//                        OneSignalUtil.callPushNotificationService(contents, filterList);
//                    }
//                }catch (SASServiceException e){
//                    throw new  SASServiceException(e.getMessage());
//                }
            }
        }
    }


    public void enviaPushPromocoesUsers() throws SASServiceException {

        List<UsuarioPushAppControleDTO> usuarioPushAppControleDTOList = usuarioPushAppRepository.
                listByProperty("pushTerminated", false);

        UsuarioPushAppControleDTO usuarioPushAppControleDTO = usuarioPushAppControleDTOList.get(0);
        List<Filter> filterList = usuarioPushAppControleDTO.getFilterList();
        LOGGER.info("Tamanho da lista: " + filterList.size());
        if(filterList.size() > 50){
            LOGGER.info("Tamanho da lista é maior que 50: " + filterList.size());
            usuarioPushAppControleDTO.setPushTerminated(false);
            List<Filter> filterListRestante = filterList.subList(50, filterList.size());
            usuarioPushAppControleDTO.setFilterList(filterListRestante);

            filterList = filterList.subList(0, 49);
        }else{
            LOGGER.info("Tamanho da lista é menor que 50: " + filterList.size());
            usuarioPushAppControleDTO.setPushTerminated(true);
        }
        usuarioPushAppRepository.put(usuarioPushAppControleDTO);

        try{
            //dispara Push
            if(filterList.size() > 0){
                Contents contents = OneSignalUtil.montaContentOneSignal("Temos promoções de fraldas para você.");
                OneSignalUtil.callPushNotificationService(contents, filterList);
            }
        }catch (SASServiceException e){
            throw new  SASServiceException(e.getMessage());
        }
    }

    private List<Usuario> getUsersToSendNotification(List<Promocao> promocoes) {
        Double maxValue = 0d;
        HashSet<Long> marcas = new HashSet<>();
        HashSet<Long> tamanhos = new HashSet<>();

        for (Promocao promocao: promocoes) {
            if (promocao.getModelo() != null) {
                marcas.add(promocao.getModelo().getMarca().getId());
            }

            if (promocao.getTamanho() != null) {
                tamanhos.add(promocao.getTamanho().getId());
            }

            if (promocao.getValorUnidade() != null) {
                if (maxValue.equals(0d)) {
                    maxValue = promocao.getValorUnidade();

                } else if (maxValue.compareTo(promocao.getValorUnidade()) > 0) {
                    maxValue = promocao.getValorUnidade();
                }
            }
        }

        LOGGER.info("Buscando usuarios - maxValue: " + maxValue + " tamanhos: " + tamanhos.toString() + " marcas: " + marcas.toString());

        return usuarioRepository.findDistinctEmails(maxValue, tamanhos, marcas);
    }

    public Iterable<PromocaoDTO> getPromocoesApp () {
        Iterable<Promocao> promocoes = promocaoRepository.findByAtivoTrue();
        return modelToDto(promocoes);
    }

    public Iterable<PromocaoDTO> getPromocoesToUserId(Long userId) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(userId);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            Boolean possuiValorMax = usuario.getValorUnidadeMax() != null && usuario.getValorUnidadeMax().compareTo(0d) > 0;

            if (usuario.getTamanhos() != null && !usuario.getTamanhos().isEmpty() &&
                    usuario.getMarcas() != null && !usuario.getMarcas().isEmpty()) {

                if (possuiValorMax) {
                    return modelToDto(promocaoRepository.findByValorUnidadeLessThanEqualAndTamanhoIdInAndModeloMarcaIdInAndAtivoTrueOrderByValorUnidadeAsc(
                            usuario.getValorUnidadeMax(), getTamanhosIds(usuario.getTamanhos()), getMarcasIds(usuario.getMarcas())
                    ));
                }

                return modelToDto(promocaoRepository.findByTamanhoIdInAndModeloMarcaIdInAndAtivoTrueOrderByValorUnidadeAsc(
                        getTamanhosIds(usuario.getTamanhos()), getMarcasIds(usuario.getMarcas())
                ));
            }

            if (usuario.getTamanhos() != null && !usuario.getTamanhos().isEmpty()) {
                if (possuiValorMax) {
                    return modelToDto(promocaoRepository.findByValorUnidadeLessThanEqualAndTamanhoIdInAndAtivoTrueOrderByValorUnidadeAsc(
                            usuario.getValorUnidadeMax(), getTamanhosIds(usuario.getTamanhos())
                    ));
                }

                return modelToDto(promocaoRepository.findByTamanhoIdInAndAtivoTrueOrderByValorUnidadeAsc(getTamanhosIds(usuario.getTamanhos())));
            }

            if (usuario.getMarcas() != null && !usuario.getMarcas().isEmpty()) {
                if (possuiValorMax) {
                    return modelToDto(promocaoRepository.findByValorUnidadeLessThanEqualAndModeloMarcaIdInAndAtivoTrueOrderByValorUnidadeAsc(
                            usuario.getValorUnidadeMax(), getMarcasIds(usuario.getMarcas())
                    ));
                }

                return modelToDto(promocaoRepository.findByModeloMarcaIdInAndAtivoTrueOrderByValorUnidadeAsc(getMarcasIds(usuario.getMarcas())));
            }

            return modelToDto(promocaoRepository.findByAtivoTrue());
        }

        return null;
    }

    private HashSet<Long> getTamanhosIds(Set<Tamanho> tamanhos) {
        HashSet<Long> tamanhosId = new HashSet<>();

        if (tamanhos != null) {
            for (Tamanho tamanho : tamanhos) {
                tamanhosId.add(tamanho.getId());
            }
        }

        return tamanhosId;
    }

    private HashSet<Long> getMarcasIds(Set<Marca> marcas) {
        HashSet<Long> marcasId = new HashSet<>();

        if (marcas != null) {
            for (Marca marca: marcas) {
                marcasId.add(marca.getId());
            }
        }

        return marcasId;
    }

    private List<PromocaoDTO> modelToDto(Iterable<Promocao> promocoesModel) {
        List<PromocaoDTO> promocoesDTO = new ArrayList<>();

        for(Promocao promocao : promocoesModel){
            promocoesDTO.add(PromocaoDTO.toPromocaoDTO(promocao));
        }

        return promocoesDTO;
    }
}
