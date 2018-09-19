package app.getfraldas.service;

import app.getfraldas.DTO.DadosPromocaoDTO;
import app.getfraldas.exception.SASServiceException;
import app.getfraldas.models.Promocao;

import java.util.Date;
import java.util.Optional;

public interface IPromocaoService {
    Optional<Promocao> getPromocao(Long id);
    Iterable<Promocao> getPromocoes();
    Iterable<Promocao> getLastPromocoes(Date minDate);
    Promocao savePromocao(Promocao promocao);
    DadosPromocaoDTO getDadosPromocao();
    void enviaPushPromocoes() throws SASServiceException;
}
