package med.voll.api.infra.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//  TODAS AS REQUISICOES AGORA VAO PRECISAR DO TOKEN PARA CONTINUAR

@Component  // apenas para o spring carregar a classe
public class SecutityFilter extends OncePerRequestFilter { // Extender a classe

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository repository;


    // FAZER A VALIDACAO E PEGAR O SUBJECT
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var tokenJWT = recuperarToken(request);   // Recupera o token do cabecalho

        if (tokenJWT != null) {  // Se tiver
            var subject = tokenService.getSubject(tokenJWT); // Valida e Pega o Subject com o metodo da classe token service

            // Se passou daqui e porque ja esta logado
            var usuario = repository.findByLogin(subject);  // Pegar o usuario completo pelo login

            // Tranformar o usuario em authentication dto
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

            // Autenticar
            SecurityContextHolder.getContext().setAuthentication(authentication);  // Setar authenticacao no usuario
        }

        filterChain.doFilter(request,response);  // filtro
    }


    // RECUPERAR O TOKEN
    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");  // Pegar o cabecalho do Authorization com token
        if (authorizationHeader != null) {   // Validar se veio ou nao
            return authorizationHeader.replace("Bearer ", "");  // Retirar o prefixo
        }
        return null;
    }
}
