package med.voll.api.domain.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Getter
@NoArgsConstructor  // construtor padrao
@AllArgsConstructor // construtos com todos os campos
@EqualsAndHashCode(of = "id")  // gerar o hashcode

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {  // IMPLEMENTAR INTERFACE

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String senha;


// MUDAR TUDO PARA TRUE CASO NAO QUEIRA CONTROLAR O USUARIO


    // SIMULAR APENAS PARA COMPILAR
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER")); // PREFIXO
    }

    // REPRESENTA A SENHA
    @Override
    public String getPassword() {
        return senha;
    }

    // REPRESENTA LOGIN
    @Override
    public String getUsername() {
        return login;
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
