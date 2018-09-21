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
        promocao.setLastUpdate(new Date());
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
        }

        return usuarioRepository.findDistinctEmails(lojas, tamanhos, modelos);
    }

    public Iterable<PromocaoDTO> getPromocoesApp(){

        Iterable<Promocao> promocoes = promocaoRepository.findAll();
        List<PromocaoDTO> promocoesDTO = new ArrayList<>();
        for(Promocao promocao : promocoes){
            promocoesDTO.add(PromocaoDTO.toPromocaoDTO(promocao));
        }
        return promocoesDTO;
    }
}
