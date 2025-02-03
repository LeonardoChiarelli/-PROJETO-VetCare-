package br.com.LeoChiarelli.Veterinario.domain.model;

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
@Table(name = "usuarios")
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfil_id")
    private List<Perfil> perfis;

    public Usuario(@NotBlank String nome, @NotBlank @Email String email, String senhaEncriptada) {
        this.id = null;
        this.nome = nome;
        this.email = email;
        this.senha = senhaEncriptada;
    }

    public Boolean isAdmin() { return this.perfis.stream().anyMatch(Perfil::isAdmin); }
    public Boolean isTutor() { return this.perfis.stream().anyMatch(Perfil::isTutor); }
    public Boolean isVet() { return this.perfis.stream().anyMatch(Perfil::isVet); }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
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
