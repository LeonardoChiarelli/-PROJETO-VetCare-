package br.com.LeoChiarelli.api.domain.repository;

import br.com.LeoChiarelli.api.domain.model.Tutor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ITutorRepository extends JpaRepository<Tutor, Long> {

    @Query("SELECT t FROM Tutor t WHERE t.cpf = :cpf")
    Optional<Tutor> findByCpf(Object cpf);

    boolean existsByCpf(@NotBlank @Pattern(regexp = "\\d{11,15}") String cpf);

    boolean existsByEmail(@NotBlank @Email String email);

    Tutor getReferencesByEmail(@NotBlank @Email String email);

    Tutor getReferenceByCpf(String cpf);
}
