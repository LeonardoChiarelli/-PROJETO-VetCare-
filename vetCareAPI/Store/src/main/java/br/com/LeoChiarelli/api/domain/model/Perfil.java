package br.com.LeoChiarelli.api.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "perfis")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Perfil implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    public boolean isAdmin(){
        return this.nome.equals("ROLE_ADMIN");
    }

    public boolean isComprador(){
        return this.nome.equals("ROLE_COMPRADOR");
    }

    @Override
    public String getAuthority() {
        return nome;
    }
}
