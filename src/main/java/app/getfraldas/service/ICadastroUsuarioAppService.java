package app.getfraldas.service;

import app.getfraldas.DTO.CadastroUsuarioAppDTO;

/**
 * Created by diegods on 28/05/18.
 */

public interface ICadastroUsuarioAppService {

    CadastroUsuarioAppDTO realizaCadastro(CadastroUsuarioAppDTO cadastroUsuarioAppDTO);
    CadastroUsuarioAppDTO getCadastro(String email);
    CadastroUsuarioAppDTO updateCadastro(CadastroUsuarioAppDTO cadastroUsuarioAppDTO);
}
