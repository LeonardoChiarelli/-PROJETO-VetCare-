package br.com.LeoChiarelli.api.domain.repository;

import br.com.LeoChiarelli.api.domain.model.Tutor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ITutorRepository extends JpaRepository<Tutor, Long> {

    @Query("SELECT a FROM Tutor a WHERE a.cpf = :cpf")
    Optional<Tutor> findByCpf(@NotBlank String cpf);

    @Modifying
    void deleteByCpf(String cpf);

    Tutor getReferenceByEmail(@NotBlank @Email String email);

    Boolean existsByEmail(@NotBlank @Email String email);
}
