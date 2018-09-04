package app.getfraldas.controller;

import app.getfraldas.models.Loja;
import app.getfraldas.repository.LojaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by fprado on 03/09/18
 */

@Controller
@RequestMapping("/api")
public class LojaController {

    @Autowired
    private LojaRepository lojaRepository;

    @GetMapping("/loja")
    public ResponseEntity<Iterable<Loja>> getLojas() {
        return ResponseEntity.ok().body(lojaRepository.findAll());
    }

    @PutMapping("/loja")
    public ResponseEntity<Loja> setLoja(@RequestBody Loja loja) {
        return ResponseEntity.ok().body(lojaRepository.save(loja));
    }

}
