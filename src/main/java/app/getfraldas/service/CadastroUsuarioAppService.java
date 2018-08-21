package app.getfraldas.service;

import app.getfraldas.DTO.CadastroUsuarioAppDTO;
import app.getfraldas.repository.CadastroUsuarioAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by diegods on 28/05/18.
 */

@Service
public class CadastroUsuarioAppService implements ICadastroUsuarioAppService {


    @Autowired
    private CadastroUsuarioAppRepository cadastroUsuarioAppRepository;

    public CadastroUsuarioAppDTO realizaCadastro(CadastroUsuarioAppDTO cadastroUsuarioAppDTO){
        cadastroUsuarioAppRepository.put(cadastroUsuarioAppDTO);
        return cadastroUsuarioAppDTO;
    }

    @Override
    public CadastroUsuarioAppDTO getCadastro(String email) {
        return cadastroUsuarioAppRepository.getByProperty("email", email);
    }

    @Override
    public CadastroUsuarioAppDTO updateCadastro(CadastroUsuarioAppDTO cadastroUsuarioAppDTO) {
        CadastroUsuarioAppDTO cadastroUsuario = cadastroUsuarioAppRepository.getByProperty("email", cadastroUsuarioAppDTO.getEmail());
        cadastroUsuario.setNome(cadastroUsuarioAppDTO.getNome());
        cadastroUsuario.setEmail(cadastroUsuarioAppDTO.getEmail());
        cadastroUsuario.setTelefone(cadastroUsuarioAppDTO.getTelefone());
        cadastroUsuario.setSenha(cadastroUsuarioAppDTO.getSenha());
        cadastroUsuario.setPreferencias(cadastroUsuarioAppDTO.getPreferencias());
        cadastroUsuarioAppRepository.put(cadastroUsuario);
        return cadastroUsuario;
    }
}
