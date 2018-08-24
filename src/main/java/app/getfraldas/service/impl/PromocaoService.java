package app.getfraldas.service.impl;

import app.getfraldas.DTO.PromocaoDTO;
import app.getfraldas.repository.PromocaoRepository;
import app.getfraldas.service.IPromocaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromocaoService implements IPromocaoService {

    @Autowired
    private PromocaoRepository promocaoRepository;

    @Override
    public PromocaoDTO getPromocao(Long id) {
        return promocaoRepository.get(id);
    }

    @Override
    public List<PromocaoDTO> getPromocoes() {
        return promocaoRepository.listAll();
    }

}
