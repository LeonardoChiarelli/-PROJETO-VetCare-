package br.com.LeoChiarelli.api.domain.repository;

import br.com.LeoChiarelli.api.domain.dto.EstatisticasVendasDTO;
import br.com.LeoChiarelli.api.domain.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IPedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("SELECT SUM(i.precoUnitario * i.quantidade) FROM Pedido p JOIN p.itens i WHERE p.dataPedido = :dataOntem")
    BigDecimal faturamentoTotalDoDia(LocalDate dataOntem);

    @Query("""
            SELECT NEW br.com.LeoChiarelli.api.domain.dto.EstatisticasVendasDTO(
                prod.categoria,
                SUM(i.quantidade),
                SUM(i.precoUnitario * i.quantidade)
            )
            FROM Pedido p
            JOIN p.itens i
            JOIN i.produto prod
            WHERE p.dataPedido = :dataOntem
            GROUP BY prod.categoria
            """)
    List<EstatisticasVendasDTO> faturamentoTotalPorCategoriaEDia(LocalDate dataOntem);
}
