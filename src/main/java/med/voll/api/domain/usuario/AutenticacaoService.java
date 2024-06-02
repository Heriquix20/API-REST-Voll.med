package med.voll.api.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


// CLASSE SERVICE
@Service
public class AutenticacaoService implements UserDetailsService { // IMPLEMENTAR A CLASSE USER DETAILS

    // REESCREVER PARA O SPRING
    @Autowired
    private UsuarioRepository repository;

    // SEMPRE QUE O USUARIO FIZER LOGIN O SPRING VAI CHAMAR ESSE METODO
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }
}
