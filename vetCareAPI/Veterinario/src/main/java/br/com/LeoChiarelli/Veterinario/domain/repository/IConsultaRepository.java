package br.com.LeoChiarelli.Veterinario.domain.repository;

import br.com.LeoChiarelli.Veterinario.domain.model.Consulta;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface IConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByIdAndDataHora(@NotNull Long idVeterinario, @NotNull @Future LocalDateTime dataHora);

    Boolean existsByPetIdAndDataHoraBetween(@NotNull Long idPet, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

    Page<Consulta> findAllByVeterinarioId(Long idVeterinario, Pageable pageable);

    @Query("SELECT c FROM Consulta c WHERE c.id = :consultaId")
    Optional<Consulta> buscarConsulta(@NotNull Long consultaId);

    Boolean existsByVeterinarioIdAndDataHora(@NotNull Long idVeterinario, @NotNull @Future LocalDateTime localDateTime);
}
