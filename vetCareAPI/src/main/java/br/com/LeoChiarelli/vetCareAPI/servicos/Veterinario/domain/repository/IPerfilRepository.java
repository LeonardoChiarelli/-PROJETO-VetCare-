package br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.repository;

import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPerfilRepository extends JpaRepository<Perfil, Long> {
}
