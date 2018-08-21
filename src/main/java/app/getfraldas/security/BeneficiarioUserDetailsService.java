package app.getfraldas.security;


import app.getfraldas.DTO.CadastroUsuarioAppDTO;
import app.getfraldas.repository.CadastroUsuarioAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class BeneficiarioUserDetailsService implements UserDetailsService {


    @Autowired
    private CadastroUsuarioAppRepository cadastroUsuarioAppRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        CadastroUsuarioAppDTO cadastroUsuario = cadastroUsuarioAppRepository.getByProperty("email", email);

        if(cadastroUsuario != null) {

            String name = cadastroUsuario.getNome();
            String surname = cadastroUsuario.getCpf();
            String password = cadastroUsuario.getSenha();
            BeneficiarioUserDetails beneficiarioUserDetails = new BeneficiarioUserDetails(email,password);
            return beneficiarioUserDetails;
        }
        return null;
    }
}
