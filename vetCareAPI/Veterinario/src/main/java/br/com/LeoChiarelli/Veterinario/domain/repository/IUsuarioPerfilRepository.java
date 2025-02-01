package br.com.LeoChiarelli.Veterinario.domain.repository;

import br.com.LeoChiarelli.Veterinario.domain.model.UsuarioPerfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioPerfilRepository extends JpaRepository<UsuarioPerfil, Long> {

}
