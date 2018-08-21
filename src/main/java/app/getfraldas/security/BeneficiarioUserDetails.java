package app.getfraldas.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;
/**
 * Created by gkatzioura on 9/27/16.
 */
public class BeneficiarioUserDetails  implements UserDetails{
    private String username;
    private String password;
    private List<GrantedAuthority> grantedAuthorities;

    public BeneficiarioUserDetails(String username,String password,String[] authorities) {
        this.username = username;
        this.password = password;
        this.grantedAuthorities = AuthorityUtils.createAuthorityList(authorities);
    }

    public BeneficiarioUserDetails(String username,String password) {
        PasswordEncoder encoder =
                PasswordEncoderFactories.createDelegatingPasswordEncoder();

        this.username = username;
        this.password = encoder.encode(password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
