package app.getfraldas.controller;

import app.getfraldas.models.Modelo;
import app.getfraldas.repository.ModeloRepository;
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
public class ModeloController {

    @Autowired
    private ModeloRepository modeloRepository;

    @GetMapping("/modelos")
    public ResponseEntity<Iterable<Modelo>> getModelos() {
        return ResponseEntity.ok().body(modeloRepository.findAll());
    }

    @GetMapping("/modelo/{id}")
    public ResponseEntity<Optional<Modelo>> getModelo(@PathVariable Long id) {
        return ResponseEntity.ok(modeloRepository.findById(id));
    }

    @PutMapping("/modelo")
    public ResponseEntity<Modelo> setModelo(@RequestBody Modelo modelo) {
        return ResponseEntity.ok().body(modeloRepository.save(modelo));
    }

}
