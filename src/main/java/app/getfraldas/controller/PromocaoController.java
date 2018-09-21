package app.getfraldas.controller;

import app.getfraldas.DTO.DadosPromocaoDTO;
import app.getfraldas.DTO.PromocaoDTO;
import app.getfraldas.models.Promocao;
import app.getfraldas.service.impl.PromocaoService;
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
    private PromocaoService promocaoService;

    @GetMapping("/promocao/{id}")
    public ResponseEntity<Optional<Promocao>> getPromocao(@PathVariable Long id) {
        return ResponseEntity.ok(promocaoService.getPromocao(id));
    }

    @GetMapping("/promocoes-app")
    public ResponseEntity<Iterable<PromocaoDTO>> getPromocoesApp(@RequestParam Long userId) {
        if (userId != null) {
            return ResponseEntity.ok(promocaoService.getPromocoesByTamanho(userId));
        }

        return ResponseEntity.ok(promocaoService.getPromocoesApp());
    }

    @GetMapping("/promocoes")
    public ResponseEntity<Iterable<Promocao>> getPromocoes() {
        return ResponseEntity.ok(promocaoService.getPromocoes());
    }

    @PutMapping("/promocao")
    public ResponseEntity<Promocao> savePromocao(@RequestBody Promocao promocao) {
        return ResponseEntity.ok(promocaoService.savePromocao(promocao));
    }

    @GetMapping("/dados-promocao")
    public ResponseEntity<DadosPromocaoDTO> getDadosPromocoes() {
        return ResponseEntity.ok(promocaoService.getDadosPromocao());
    }
}
