package br.com.LeoChiarelli.api.domain.repository;

import br.com.LeoChiarelli.api.domain.model.UsuarioPerfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioPerfilRepository extends JpaRepository<UsuarioPerfil, Long> {
}
