package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.usuario.DadosAutenticacao;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.infra.security.DadosTokenJWT;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    // CHAMANDO O MANAGER
    @Autowired
    private AuthenticationManager manager;  // CLASSE PARA CHAMAR O AUTENTICACAO SERVICE
    // CHAMANDO O TOKEN SERVICE
    @Autowired
    private TokenService tokenService;


    // Request BODY sempre que for receber um corpo de fora
    @PostMapping
    public ResponseEntity realizarLogin(@RequestBody @Valid DadosAutenticacao dados) {  // VAI TRAZER UM JSON PARA SER VALIDADO, NO CASO O LOGIN E A SENHA

        // CONVERTE O DTO EM UM NEW UsernamePasswordAuthenticationToken
        var autenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());

        // DEVOLVER UM OBJETO USUARIO AUTENTICADO NO SISTEMA
        var autentication = manager.authenticate(autenticationToken);  // ENCONTROU A CLASSE QUE USA O REPOSITORY E FEZ A CONSULTA NO BANCO DE DADOS

        // O AUTENTICATION JA ARMAZENA UM USUARIO MAS ESTA EM FORMATO OBJECT
        // RETORNA O TOKEN GERADO PARA O USUARIO     // CAST PARA PASSA PARA USUARIO
        var tokenJWT = tokenService.gerarToken((Usuario) autentication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT)); // CRIA UM DTO DO TOKEN E RETORNA UM JSON DO TOKEN
    }
}
