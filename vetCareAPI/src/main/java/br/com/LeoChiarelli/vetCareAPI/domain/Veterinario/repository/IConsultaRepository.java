package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.repository;

import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IConsultaRepository extends JpaRepository<Consulta, Long> {
}
