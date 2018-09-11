package app.getfraldas.service.impl;

import app.getfraldas.DTO.PreferenciasDTO;
import app.getfraldas.repository.LojaRepository;
import app.getfraldas.repository.MarcaRepository;
import app.getfraldas.repository.TamanhoRepository;
import app.getfraldas.service.IPreferenciasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fprado on 10/09/18
 */

@Service
public class PreferenciasService implements IPreferenciasService {

    @Autowired
    private LojaRepository lojaRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private TamanhoRepository tamanhoRepository;

    @Override
    public PreferenciasDTO getAllPreferencias() {
        PreferenciasDTO preferencias = new PreferenciasDTO();
        preferencias.setLojas(lojaRepository.findAll());
        preferencias.setMarcas(marcaRepository.findAll());
        preferencias.setTamanhos(tamanhoRepository.findAll());

        return preferencias;
    }
}
