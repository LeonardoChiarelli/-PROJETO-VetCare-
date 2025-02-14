package br.com.LeoChiarelli.api.domain.repository;

import br.com.LeoChiarelli.api.domain.model.Abrigo;
import br.com.LeoChiarelli.api.domain.model.Pet;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IPetRepository extends JpaRepository<Pet, Long> {

    @Query("SELECT p FROM Pet p WHERE p.abrigo_id = :abrigo AND  p.status = 'DISPONIVEL' ")
    Page<Pet> findByAbrigoAndStatusDisponivel(Abrigo abrigo, Pageable pageable);

    Optional<Pet> findByAbrigoAndId(Abrigo abrigo, Long id);

    Boolean existsByIdAndStatusDisponivel(@NotNull Long id);

    Boolean existsByIdAndStatusEmProcesso(@NotNull Long id);
}
