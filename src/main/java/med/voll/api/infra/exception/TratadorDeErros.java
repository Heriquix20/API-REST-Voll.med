package med.voll.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;

@RestControllerAdvice // TRATADOR DE ERROS
public class TratadorDeErros {


    @ExceptionHandler(EntityNotFoundException.class)  // PRA QUAL EXCESSAO VAI SER CHAMADA
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();  // RETORNAR O ERRO
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)  // PRA QUAL EXCESSAO VAI SER CHAMADA
    public ResponseEntity tratarErro400(MethodArgumentNotValidException e) { // PASSAR NO PARAMETRO
        var errors = e.getFieldErrors();  // PEGANDO A LISTA DE ERROS E COLOCANDO NA VARIAVEL

        // TRANSFORMAR EM LISTA RECORD
        return ResponseEntity.badRequest().body(errors.stream()
                .map(DadosErrosValidacao::new).toList());
    }


    // RECORD PARA TRANSFORMAR O JSON DO ERRO APENAS NO QUE QUEREMOS
    private record DadosErrosValidacao(String campo, String mensagem) {

        public DadosErrosValidacao(FieldError error) {
            // campo ----------------- mensagem
            this(error.getField(), error.getDefaultMessage());
        }
    }


    // OUTROS ERROS COMUMS
        @ExceptionHandler(HttpMessageNotReadableException.class)
        public ResponseEntity tratarErro400(HttpMessageNotReadableException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

        @ExceptionHandler(BadCredentialsException.class)
        public ResponseEntity tratarErroBadCredentials() {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }

        @ExceptionHandler(AuthenticationException.class)
        public ResponseEntity tratarErroAuthentication() {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação");
        }

        @ExceptionHandler(AccessDeniedException.class)
        public ResponseEntity tratarErroAcessoNegado() {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado");
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity tratarErro500(Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " +ex.getLocalizedMessage());
        }

        private record DadosErroValidacao(String campo, String mensagem) {
            public DadosErroValidacao(FieldError erro) {
                this(erro.getField(), erro.getDefaultMessage());
            }
        }
    }

