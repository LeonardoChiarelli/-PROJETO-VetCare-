package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UsuarioPerfilID implements Serializable {
    private Long idUsuario;
    private Long idPerfil;
}
