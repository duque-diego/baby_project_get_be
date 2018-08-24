package app.getfraldas.controller;

import app.getfraldas.DTO.PromocaoDTO;
import app.getfraldas.service.impl.PromocaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by fprado on 22/08/18.
 */

@RestController
@RequestMapping("/api")
public class PromocaoController {

    @Autowired
    private PromocaoService promocaoService;

    @GetMapping("/promocao/{id}")
    public ResponseEntity<PromocaoDTO> getPromocao(@RequestParam Long id) {
        return ResponseEntity.ok(promocaoService.getPromocao(id));
    }

    @GetMapping("/promocoes")
    public ResponseEntity<List<PromocaoDTO>> getPromocoes() {
        return ResponseEntity.ok(promocaoService.getPromocoes());
    }
}
