package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.repository;

import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.model.UsuarioPerfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioPerfilRepository extends JpaRepository<UsuarioPerfil, Long> {

}
