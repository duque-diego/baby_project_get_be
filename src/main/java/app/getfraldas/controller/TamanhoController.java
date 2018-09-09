package app.getfraldas.controller;

import app.getfraldas.models.Tamanho;
import app.getfraldas.repository.TamanhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by fprado on 07/09/18
 */

@Controller
@RequestMapping("/api")
public class TamanhoController {

    @Autowired
    private TamanhoRepository tamanhoRepository;

    @GetMapping("/tamanhos")
    public ResponseEntity<Iterable<Tamanho>> getTamanhos() {
        return ResponseEntity.ok().body(tamanhoRepository.findAll());
    }

    @GetMapping("/tamanho/{id}")
    public ResponseEntity<Optional<Tamanho>> getTamanho(@PathVariable Long id) {
        return ResponseEntity.ok(tamanhoRepository.findById(id));
    }

    @PutMapping("/tamanho")
    public ResponseEntity<Tamanho> setTamanho(@RequestBody Tamanho tamanho) {
        return ResponseEntity.ok().body(tamanhoRepository.save(tamanho));
    }

}
