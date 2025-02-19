package br.com.LeoChiarelli.api.domain.model;

import jakarta.persistence.*;
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
    @Column(name = "usuario_id")
    private Long idUsuario;

    @Id
    private Long idPerfil;

}
