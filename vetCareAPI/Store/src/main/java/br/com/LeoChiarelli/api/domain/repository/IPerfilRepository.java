package br.com.LeoChiarelli.api.domain.repository;

import br.com.LeoChiarelli.api.domain.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPerfilRepository extends JpaRepository<Perfil, Long> {
    Optional<Perfil> findByNome(String roleComprador);
}
