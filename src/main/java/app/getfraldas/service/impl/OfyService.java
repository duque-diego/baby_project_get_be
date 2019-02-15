package app.getfraldas.service.impl;

import app.getfraldas.DTO.CadastroUsuarioAppDTO;
import app.getfraldas.DTO.PromocaoDTO;
import app.getfraldas.DTO.UsuarioPushAppControleDTO;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import org.springframework.stereotype.Service;

@Service
public class OfyService {

    private OfyService() {
        super();
    }

    static {
        ObjectifyService.register(CadastroUsuarioAppDTO.class);
        ObjectifyService.register(UsuarioPushAppControleDTO.class);
//        ObjectifyService.register(PromocaoDTO.class);
    }

    public static Objectify ofy(){
        return ObjectifyService.ofy();
    }

}
