package br.com.LeoChiarelli.api.domain.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UsuarioPerfilID {

    @Column(name = "usuario_id")
    private Long idUsuario;
    private Long idPerfil;
}
