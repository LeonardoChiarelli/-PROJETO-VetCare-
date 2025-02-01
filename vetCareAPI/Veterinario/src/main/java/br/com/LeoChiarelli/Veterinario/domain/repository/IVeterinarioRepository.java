package br.com.LeoChiarelli.Veterinario.domain.repository;

import br.com.LeoChiarelli.Veterinario.domain.model.Veterinario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IVeterinarioRepository extends JpaRepository<Veterinario, Long> {
    Page<Veterinario> findAllByAtivoTrue(Pageable pageable);

    boolean existsByCrmOrEmail(@NotBlank @Pattern(regexp = "\\d{4,6}") String crm, @NotBlank @Email String email);

    @Query("SELECT v.ativo FROM Veterinario v WHERE v.id = :idVeterinario")
    Boolean findAtivoById(@NotNull Long idVeterinario);
}
