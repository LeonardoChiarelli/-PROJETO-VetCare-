package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.repository;

import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPetRepository extends JpaRepository<Pet, Long> {

}
