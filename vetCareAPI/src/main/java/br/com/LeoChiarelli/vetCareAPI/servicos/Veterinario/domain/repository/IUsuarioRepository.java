package br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.repository;

import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);
}
