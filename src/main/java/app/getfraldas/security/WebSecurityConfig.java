package app.getfraldas.security;


import com.googlecode.objectify.ObjectifyFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

/**
 * Created by diegods on 31/05/18.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public UserDetailsService beneficiarioUserDetailsService() {
        return new BeneficiarioUserDetailsService();
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        final CorsConfiguration config = new CorsConfiguration();

//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("GET");
//        config.addAllowedMethod("PUT");
//        config.addAllowedMethod("POST");
//        source.registerCorsConfiguration("/**", config);

        httpSecurity.csrf().disable().authorizeRequests()
                .antMatchers("/cadastroUsuarioApp").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers("/getDadosUsuarioApp/*").permitAll()
                .antMatchers("/updateDadosUsuarioApp").permitAll()
                .anyRequest().authenticated()
                .and()

                .addFilterBefore(new ObjectifyFilter(), UsernamePasswordAuthenticationFilter.class)

                // filtra requisições de login
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)

                // filtra outras requisições para verificar a presença do JWT no header
                .addFilterBefore(new JWTAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // cria uma conta default

//        PasswordEncoder encoder =
//                PasswordEncoderFactories.createDelegatingPasswordEncoder();
//
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password(encoder.encode("password"))
//                .roles("ADMIN");
//
//        auth.parentAuthenticationManager().

        UserDetailsService userDetailsService = beneficiarioUserDetailsService();
        auth.userDetailsService(userDetailsService);
    }


}
