package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import med.voll.api.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


// CLASSE PARA SERVICOS DO TOKEN
@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;  // VARIAVEL DE AMBIENTE NO PROPERTIES



    // METODO PARA GERAR TOKEN
    public String gerarToken(Usuario usuario) {
        try {                                                      // SENHA DA ASSINATURA DO TOKEN
            var algoritmo = Algorithm.HMAC256(secret);            // USAR UM ALGORITMO PARA CRIAR O TOKEN
            return JWT.create()                                  // CRIAR E RETORNAR O TOKEN
                    .withIssuer("API_Voll.med")                 // IDENTIFICAR QUEM E O DONO DO TOKEN QUEM ESTA GERANDO
                    .withSubject(usuario.getLogin())           // GUARDAR DE QUEM E O TOKEN A QUEM PERTENCE
                    .withClaim("id", usuario.getId())   //  PASSA QUALQUE OUTRA INFORMACAO
                    .withExpiresAt(dataExpiracao())          // DEFINIR UMA DATA DE EXPIRACAO
                    .sign(algoritmo);                       // FAZER A ASSINATURA
        } catch (JWTCreationException exception){
            throw new RuntimeException("erro ao gerar token jwt, " + exception);  // LANCAR UM ERRO
        }

    }

    // Verificar se o token esta valido e devolver o objeto
    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);     // algoritmo igual
            return JWT.require(algoritmo)
                    .withIssuer("API_Voll.med")           // verificar se o issuer esta vindo corretamente
                    .build()
                    .verify(tokenJWT)                     // verificar o tokenJWT
                    .getSubject();                        // pegar o objeto
        } catch (JWTVerificationException exception){
            throw new RuntimeException("TokenJWT inválido ou expirado");
        }
    }


    // DATA DE EXPIRACAO
    private Instant dataExpiracao() {
        // local data   . de agora  . quantidade de horas  .  fuso horario do brasil
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }


}
