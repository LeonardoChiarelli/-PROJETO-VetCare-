package br.com.LeoChiarelli.api.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Usuarios")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String senha;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuarios_perfis", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "perfil_id"))
    @Column(name = "perfil_id")
    private List<Perfil> perfis = new ArrayList<>();

    public Usuario(@NotBlank @Email String email, @NotBlank String nome, List<Perfil> perfil, String senhaEncriptada) {
        this.nome = nome;
        this.email = email;
        this.senha = senhaEncriptada;
        this.perfis = perfil;
    }

    public Boolean isAdmin(){
        return this.perfis.stream().anyMatch(Perfil::isAdmin);
    }

    public Boolean isComprador(){
        return this.perfis.stream().anyMatch(Perfil::isComprador);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println("Perfis carregados para " + this.email + ": " + this.perfis);
        if (this.perfis.isEmpty()) {
            System.out.println("⚠️ Nenhum perfil foi carregado!");
        }
        return this.perfis;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
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
