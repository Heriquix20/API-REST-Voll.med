package med.voll.api.infra.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration  // CLASSE DE CONFIGURACAO
@EnableWebSecurity // PERSONALIZAR AS CONFIG DE SEGURANCA
public class SecurityConfiguration {

    @Autowired
    private SecutityFilter secutityFilter;


    // DESATIVAR SEGURANCA PADRAO DO SPRING POIS SERA UTILIZADO TOKENS
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return
                http.csrf(csrf -> csrf.disable())
                        .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .authorizeHttpRequests(req -> {
                            req.requestMatchers("/login").permitAll();  // permite o login
                            req.requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll();
                            req.anyRequest().authenticated();  // qualquer outra bloqueia
                        })
                        .addFilterBefore(secutityFilter, UsernamePasswordAuthenticationFilter.class)
                        .build();
    }


    // METODO PARA VOLTAR UM AUTHENTICATION MANAGER E UTILIZAR NA AUTENTICACAO CONTROLLER
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        // CRIAR O OBJETO AUTHENTICATION MANAGER
        return configuration.getAuthenticationManager();
    }


    // METODO PARA CRIPTOGRAFAR A SENHA
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
