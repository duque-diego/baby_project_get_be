package app.getfraldas.controller;

import app.getfraldas.DTO.PromocaoDTO;
import app.getfraldas.exception.SASServiceException;
import app.getfraldas.service.impl.PromocaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by diegods on 15/09/18 d.C..
 */

@RestController
public class CronController {


    @Autowired
    private PromocaoService promocaoService;

    @ResponseBody
    @RequestMapping(value = "/cron/disparaPushesPromocao", method = RequestMethod.GET)
    public String cronTest() throws SASServiceException {

        promocaoService.enviaPushPromocoes();
        return "Rolou";
    }
}

