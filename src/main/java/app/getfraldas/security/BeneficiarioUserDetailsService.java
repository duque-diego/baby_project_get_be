package app.getfraldas.security;


import app.getfraldas.models.Usuario;
import app.getfraldas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class BeneficiarioUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByEmailEquals(email);

        if(usuario != null) {
            String password = usuario.getSenha();
            BeneficiarioUserDetails beneficiarioUserDetails = new BeneficiarioUserDetails(email,password);
            return beneficiarioUserDetails;
        }
        return null;
    }
}
