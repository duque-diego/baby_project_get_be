package app.getfraldas.controller;

import app.getfraldas.DTO.PromocaoDTO;
import app.getfraldas.exception.SASServiceException;
import app.getfraldas.repository.CronHistoryRepository;
import app.getfraldas.service.impl.CronService;
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

    @Autowired
    private CronService cronService;

    @ResponseBody
    @RequestMapping(value = "/cron/disparaPushesPromocao", method = RequestMethod.GET)
    public String cronTest() throws SASServiceException {

        promocaoService.enviaPushPromocoes();
        cronService.saveCron();
        return "Rolou";
    }


    @ResponseBody
    @RequestMapping(value = "/cron/disparaPushesPromocaoUsuario", method = RequestMethod.GET)
    public String cronEnvia() throws SASServiceException {

        promocaoService.enviaPushPromocoesUsers();
        return "Rolou";
    }
}

