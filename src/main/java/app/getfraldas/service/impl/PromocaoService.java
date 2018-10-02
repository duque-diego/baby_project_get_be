package app.getfraldas.service.impl;

import app.getfraldas.DTO.DadosPromocaoDTO;
import app.getfraldas.DTO.PromocaoDTO;
import app.getfraldas.exception.SASServiceException;
import app.getfraldas.models.*;
import app.getfraldas.repository.*;
import app.getfraldas.service.IPromocaoService;
import app.getfraldas.utils.DateUtils;
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
    private CronService cronService;

    @Override
    public Optional<Promocao> getPromocao(Long id) {
        return promocaoRepository.findById(id);
    }

    @Override
    public Iterable<Promocao> getPromocoes() {
        return promocaoRepository.findAll();
    }

    @Override
    public Iterable<Promocao> getLastPromocoes(Date minDate) {
        return null;
    }

    @Override
    public Promocao savePromocao(Promocao promocao) {

        DecimalFormat df2 = new DecimalFormat(".##");
        promocao.setLastUpdate(new Date());
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

        List<Promocao> promocaoList = promocaoRepository.findByLastUpdateGreaterThanEqual(formattedDate);

        if (promocaoList == null || promocaoList.isEmpty()) {
            promocaoList = Lists.newArrayList(this.getPromocoes());
        }

        if (promocaoList.size() > 0) {

            List<Usuario> usuarios = getUsersToSendNotification(promocaoList);

            //TODO: add lista de emails no envio para One Signal

            //dispara Push
//            Contents contents = OneSignalUtil.montaContentOneSignal("Temos promoções de fraldas para você.");
//            try{
//                OneSignalUtil.callPushNotificationService(contents);
//            }catch (SASServiceException e){
//                throw new  SASServiceException(e.getMessage());
//            }
        }
    }

    private List<Usuario> getUsersToSendNotification(List<Promocao> promocoes) {
        Double maxValue = 0d;
        HashSet<Long> modelos = new HashSet<>();
        HashSet<Long> lojas = new HashSet<>();
        HashSet<Long> tamanhos = new HashSet<>();

        for (Promocao promocao: promocoes) {
            if (promocao.getModelo() != null) {
                modelos.add(promocao.getModelo().getId());
            }

            if (promocao.getLoja() != null) {
                lojas.add(promocao.getLoja().getId());
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

        return usuarioRepository.findDistinctEmails(maxValue, lojas, tamanhos, modelos);
    }

    public Iterable<PromocaoDTO> getPromocoesApp () {
        Iterable<Promocao> promocoes = promocaoRepository.findAll();
        return modelToDto(promocoes);
    }

    public Iterable<PromocaoDTO> getPromocoesToUserId(Long userId) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(userId);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            if (usuario.getTamanhos() != null && !usuario.getTamanhos().isEmpty() &&
                    usuario.getMarcas() != null && !usuario.getMarcas().isEmpty()) {

                return modelToDto(promocaoRepository.findByTamanhoIdInAndModeloMarcaIdInOrderByValorUnidadeAsc(
                        getTamanhosIds(usuario.getTamanhos()), getMarcasIds(usuario.getMarcas())
                ));
            }

            if (usuario.getTamanhos() != null && !usuario.getTamanhos().isEmpty()) {
                return modelToDto(promocaoRepository.findByTamanhoIdInOrderByValorUnidadeAsc(getTamanhosIds(usuario.getTamanhos())));
            }

            if (usuario.getMarcas() != null && !usuario.getMarcas().isEmpty()) {
                return modelToDto(promocaoRepository.findByModeloMarcaIdInOrderByValorUnidadeAsc(getMarcasIds(usuario.getMarcas())));
            }

            return modelToDto(promocaoRepository.findAll());
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
