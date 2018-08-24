package app.getfraldas.service;

import app.getfraldas.DTO.PromocaoDTO;

import java.util.List;

public interface IPromocaoService {
    PromocaoDTO getPromocao(Long id);
    List<PromocaoDTO> getPromocoes();
}
