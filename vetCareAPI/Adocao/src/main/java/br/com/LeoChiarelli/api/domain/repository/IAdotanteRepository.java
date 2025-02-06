package br.com.LeoChiarelli.api.domain.repository;

import br.com.LeoChiarelli.api.domain.model.Adotante;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IAdotanteRepository extends JpaRepository<Adotante, Long> {

    @Query("SELECT a FROM Adotante a WHERE a.cpf = :cpf")
    Optional<Adotante> findByCpf(@NotBlank String cpf);
}
