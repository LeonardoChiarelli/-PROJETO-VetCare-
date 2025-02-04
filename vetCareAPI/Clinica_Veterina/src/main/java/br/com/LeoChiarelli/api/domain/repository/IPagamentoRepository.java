package br.com.LeoChiarelli.api.domain.repository;

import br.com.LeoChiarelli.api.domain.model.Pagamento;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface IPagamentoRepository extends JpaRepository<Pagamento, Long> {

    @Query("DELETE FROM Pagamento p WHERE p.status = 'CANCELADO' ")
    @Modifying
    @Transactional
    void deleteByStatus();
}
