package br.com.LeoChiarelli.Veterinario.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios_perfis")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@IdClass(UsuarioPerfilID.class)
public class UsuarioPerfil {

    @Id
    private Long idUsuario;

    @Id
    private Long idPerfil;
}
