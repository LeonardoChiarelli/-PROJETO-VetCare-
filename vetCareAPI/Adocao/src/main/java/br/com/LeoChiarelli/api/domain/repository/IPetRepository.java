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

    @Query("SELECT p FROM Pet p WHERE p.abrigo.id = :abrigo AND  p.status = 'DISPONIVEL' ")
    Page<Pet> findAllByAbrigoAndStatusDisponivel(Long abrigo, Pageable pageable);

    Optional<Pet> findByAbrigoAndId(Abrigo abrigo, Long id);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Pet p WHERE p.id = :id AND p.status = 'DISPONIVEL'")
    Boolean existsByIdAndStatusDisponivel(@NotNull Long id);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Pet p WHERE p.id = :id AND p.status = 'EM_PROCESSO'")
    Boolean existsByIdAndStatusEmProcesso(@NotNull Long id);
}
