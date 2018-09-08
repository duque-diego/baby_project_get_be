package app.getfraldas.controller;

import app.getfraldas.models.Marca;
import app.getfraldas.repository.MarcaRepository;
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
public class MarcaController {

    @Autowired
    private MarcaRepository marcaRepository;

    @GetMapping("/marcas")
    public ResponseEntity<Iterable<Marca>> getMarcas() {
        return ResponseEntity.ok().body(marcaRepository.findAll());
    }

    @GetMapping("/marca/{id}")
    public ResponseEntity<Optional<Marca>> getMarca(@PathVariable Long id) {
        return ResponseEntity.ok(marcaRepository.findById(id));
    }

    @PutMapping("/marca")
    public ResponseEntity<Marca> setMarca(@RequestBody Marca marca) {
        return ResponseEntity.ok().body(marcaRepository.save(marca));
    }

}
