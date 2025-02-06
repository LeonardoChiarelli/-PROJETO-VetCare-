package br.com.LeoChiarelli.api.domain.repository;

import br.com.LeoChiarelli.api.domain.model.Abrigo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAbrigoRepository extends JpaRepository<Abrigo, Long> {

    boolean existsByCnpj(@NotBlank @Pattern(regexp = "\\d{14}") String cnpj);

    Optional<Abrigo> findByNome(String idOuNome);
}
