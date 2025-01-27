package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.repository;

import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPagamentoRepository extends JpaRepository<Pagamento, Long> {
}
