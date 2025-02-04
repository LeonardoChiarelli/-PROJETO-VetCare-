package br.com.LeoChiarelli.api.domain.repository;

import br.com.LeoChiarelli.api.domain.model.MetodoDePagamento;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IMetodoPagamentoRepository extends JpaRepository<MetodoDePagamento, Long> {

    @Query("SELECT m FROM Metodo m WHERE m.id = :metodoDePagamentoId")
    Optional<MetodoDePagamento> buscarMetodo(@NotNull Long metodoDePagamentoId);
}
