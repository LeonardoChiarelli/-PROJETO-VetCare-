package br.com.LeoChiarelli.Veterinario.domain.repository;

import br.com.LeoChiarelli.Veterinario.domain.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPetRepository extends JpaRepository<Pet, Long> {

}
