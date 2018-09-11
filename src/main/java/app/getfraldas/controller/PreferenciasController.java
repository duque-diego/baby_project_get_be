package app.getfraldas.controller;

import app.getfraldas.DTO.PreferenciasDTO;
import app.getfraldas.service.impl.PreferenciasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by fprado on 10/09/18
 */

@Controller
@RequestMapping("/api")
public class PreferenciasController {

    @Autowired
    private PreferenciasService preferenciasService;

    @GetMapping("/preferencias")
    public ResponseEntity<PreferenciasDTO> getAllPreferencias() {
        return ResponseEntity.ok().body(preferenciasService.getAllPreferencias());
    }
}
