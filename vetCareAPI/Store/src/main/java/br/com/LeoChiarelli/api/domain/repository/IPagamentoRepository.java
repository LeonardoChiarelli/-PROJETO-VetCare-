package br.com.LeoChiarelli.api.domain.repository;

import br.com.LeoChiarelli.api.domain.model.Pagamento;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPagamentoRepository extends JpaRepository<Pagamento, Long> {

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Pagamento p WHERE p.status = 'EM_PROCESSO' OR p.status = 'APROVADO' AND p.pedido.id = :idPedido")
    boolean existsByStatusAndPedido(@NotNull Long idPedido);
}
