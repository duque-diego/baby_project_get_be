package app.getfraldas.service.impl;

import app.getfraldas.DTO.DadosPromocaoDTO;
import app.getfraldas.DTO.PromocaoDTO;
import app.getfraldas.models.Loja;
import app.getfraldas.models.Modelo;
import app.getfraldas.models.Tamanho;
import app.getfraldas.repository.LojaRepository;
import app.getfraldas.repository.ModeloRepository;
import app.getfraldas.repository.PromocaoRepository;
import app.getfraldas.repository.TamanhoRepository;
import app.getfraldas.service.IPromocaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@Service
public class PromocaoService implements IPromocaoService {

    private Connection conn;

    private static final Logger LOGGER = Logger.getLogger(PromocaoService.class.getName());

    @Autowired
    private PromocaoRepository promocaoRepository;

    @Autowired
    private LojaRepository lojaRepository;

    @Autowired
    private ModeloRepository modeloRepository;

    @Autowired
    private TamanhoRepository tamanhoRepository;

    @Override
    public PromocaoDTO getPromocao(Long id) {
//        return promocaoRepository.get(id);
        return null;
    }

    @Override
    public List<PromocaoDTO> getPromocoes() {
        return null;
//        return promocaoRepository.listAll();
    }

    @Override
    public PromocaoDTO savePromocao(PromocaoDTO promocaoDTO) {

        //teste repository
//        connectDataBase();
//        getPromocao();


//        promocaoRepository.put(promocaoDTO);
        return promocaoDTO;
    }

    private void getPromocao() {
        final String selectSql = "SELECT * FROM promocao LIMIT 10;";

        try (ResultSet rs = conn.prepareStatement(selectSql).executeQuery()) {
            while (rs.next()) {
                String valorUnidade = rs.getString("valorUnidade");
                LOGGER.info("Promocao: " + valorUnidade);
            }
        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
        }
    }


    private void connectDataBase() {
        String url = System.getProperty("cloudsql");
        try {
            LOGGER.info("conectando no database");
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
        }
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

}
