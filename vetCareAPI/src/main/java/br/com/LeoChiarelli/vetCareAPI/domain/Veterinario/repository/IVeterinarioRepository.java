package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.repository;

import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.model.Veterinario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVeterinarioRepository extends JpaRepository<Veterinario, Long> {
    Page<Veterinario> findAllByAtivoTrue(Pageable pageable);

    boolean existsByCrmOrEmail(@NotBlank @Pattern(regexp = "\\d{4,6}") String crm, @NotBlank @Email String email);
}
