package app.getfraldas.controller;

import app.getfraldas.models.Promocao;
import app.getfraldas.repository.PromocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by fprado on 22/08/18.
 */

@RestController
@RequestMapping("/api")
public class PromocaoController {

    @Autowired
    private PromocaoRepository promocaoRepository;

    @GetMapping("/promocao/{id}")
    public ResponseEntity<Optional<Promocao>> getPromocao(@PathVariable Long id) {
        return ResponseEntity.ok(promocaoRepository.findById(id));
    }

    @GetMapping("/promocoes")
    public ResponseEntity<Iterable<Promocao>> getPromocoes() {
        return ResponseEntity.ok(promocaoRepository.findAll());
    }

    @PutMapping("/promocao")
    public ResponseEntity<Promocao> savePromocao(@RequestBody Promocao promocao) {
        return ResponseEntity.ok(promocaoRepository.save(promocao));
    }
}
