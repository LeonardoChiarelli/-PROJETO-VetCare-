package br.com.LeoChiarelli.Veterinario.domain.repository;

import br.com.LeoChiarelli.Veterinario.domain.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPerfilRepository extends JpaRepository<Perfil, Long> {
}
