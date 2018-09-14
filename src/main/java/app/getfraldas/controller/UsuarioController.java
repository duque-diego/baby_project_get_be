package app.getfraldas.controller;

import app.getfraldas.models.Usuario;
import app.getfraldas.repository.UsuarioRepository;
import app.getfraldas.service.impl.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by fprado on 03/09/18
 */

@Controller
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuarios")
    public ResponseEntity<Iterable<Usuario>> getUsuarios() {
        return ResponseEntity.ok().body(usuarioRepository.findAll());
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<Optional<Usuario>> getUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioRepository.findById(id));
    }

    @GetMapping("/usuario")
    public ResponseEntity<Usuario> getUsuarioByEmail(@RequestParam String email) {
        return ResponseEntity.ok(usuarioRepository.findByEmailEquals(email));
    }

    @PutMapping("/usuario")
    public ResponseEntity<Usuario> setUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok().body(usuarioService.saveUsuario(usuario));
    }

}
