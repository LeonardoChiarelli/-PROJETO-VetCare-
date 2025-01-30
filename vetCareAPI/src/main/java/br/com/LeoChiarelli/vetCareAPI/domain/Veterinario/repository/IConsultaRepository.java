package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.repository;

import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.model.Consulta;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface IConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByIdAndDataHora(@NotNull Long idVeterinario, @NotNull @Future LocalDateTime dataHora);

    Boolean existsByPetIdAndDataHoraBetween(@NotNull Long idPet, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

    Page<Consulta> findAllByVeterinarioId(Long idVeterinario, Pageable pageable);
}
