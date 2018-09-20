package app.getfraldas.service;

import app.getfraldas.DTO.DadosPromocaoDTO;
import app.getfraldas.DTO.PromocaoDTO;
import app.getfraldas.exception.SASServiceException;
import app.getfraldas.models.Promocao;

import java.util.List;

public interface IPromocaoService {
    PromocaoDTO getPromocao(Long id);
    Iterable<Promocao> getPromocoes();
    PromocaoDTO savePromocao(PromocaoDTO promocaoDTO);
    DadosPromocaoDTO getDadosPromocao();
    void enviaPushPromocoes() throws SASServiceException;
    Iterable<PromocaoDTO> getPromocoesApp();
}
