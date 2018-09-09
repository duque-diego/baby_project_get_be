package app.getfraldas.controller;

import app.getfraldas.models.Bebe;
import app.getfraldas.repository.BebeRepository;
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
public class BebeController {

    @Autowired
    private BebeRepository bebeRepository;

    @GetMapping("/bebes")
    public ResponseEntity<Iterable<Bebe>> getBebes() {
        return ResponseEntity.ok().body(bebeRepository.findAll());
    }

    @GetMapping("/bebe/{id}")
    public ResponseEntity<Optional<Bebe>> getBebe(@PathVariable Long id) {
        return ResponseEntity.ok(bebeRepository.findById(id));
    }

    @PutMapping("/bebe")
    public ResponseEntity<Bebe> setBebe(@RequestBody Bebe bebe) {
        return ResponseEntity.ok().body(bebeRepository.save(bebe));
    }

}
