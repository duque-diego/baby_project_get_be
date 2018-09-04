package app.getfraldas.controller;

import app.getfraldas.DTO.CadastroUsuarioAppDTO;
import app.getfraldas.service.impl.CadastroUsuarioAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

/**
 * Created by diegods on 31/05/18.
 */

@CrossOrigin
@RestController
public class CadastroController {

    @Autowired
    private CadastroUsuarioAppService iCadastroUsuarioAppService;

    @GetMapping("/")
    public String hello() {
        return "Hello Spring Boot, I'm on App Engine!!!";
    }

    @GetMapping("/time")
    public String time() {
        return Calendar.getInstance().getTime().toString();
    }

    @ResponseBody
    @RequestMapping(value = "/cadastroUsuarioApp", method = RequestMethod.POST)
    public CadastroUsuarioAppDTO cadastroUsuarioApp(@RequestBody CadastroUsuarioAppDTO cadastroUsuarioAppDTO) {
        return iCadastroUsuarioAppService.realizaCadastro(cadastroUsuarioAppDTO);
    }

    @ResponseBody
    @RequestMapping(value = "/getDadosUsuarioApp/{email}", method = RequestMethod.GET)
    public CadastroUsuarioAppDTO getDadosUsuarioApp(@PathVariable("email") String email) {
        return iCadastroUsuarioAppService.getCadastro(email);
    }

    @ResponseBody
    @RequestMapping(value = "/updateDadosUsuarioApp", method = RequestMethod.POST)
    public CadastroUsuarioAppDTO updateDadosUsuarioApp(@RequestBody CadastroUsuarioAppDTO cadastroUsuarioAppDTO) {
        return iCadastroUsuarioAppService.updateCadastro(cadastroUsuarioAppDTO);
    }
}
