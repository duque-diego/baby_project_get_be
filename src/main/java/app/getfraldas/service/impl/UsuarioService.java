package app.getfraldas.service.impl;

import app.getfraldas.models.Bebe;
import app.getfraldas.models.Usuario;
import app.getfraldas.repository.BebeRepository;
import app.getfraldas.repository.UsuarioRepository;
import app.getfraldas.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

/**
 * Created by fprado on 13/09/18
 */

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private BebeRepository bebeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario saveUsuario(Usuario usuario) {

        if (usuario != null && usuario.getBebes() != null && usuario.getBebes().size() > 0) {
            Bebe bebe = usuario.getBebes().iterator().next();
            bebe.setUsuario(usuario);

            bebe = bebeRepository.save(bebe);
            HashSet<Bebe> bebes = new HashSet<>();
            bebes.add(bebe);

            usuario.setBebes(bebes);
        }

        return usuarioRepository.save(usuario);
    }
}
