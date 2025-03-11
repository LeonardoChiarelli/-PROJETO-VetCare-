package br.com.LeoChiarelli.api.domain.repository;

import br.com.LeoChiarelli.api.domain.dto.EstatisticasVendasDTO;
import br.com.LeoChiarelli.api.domain.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface IPedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("SELECT SUM(i.precoUnitario * i.quantidade) FROM Pedido p JOIN p.itens i WHERE p.data = :dataOntem")
    BigDecimal faturamentoTotalDoDia(LocalDate dataOntem);

    @Query("""
            SELECT NEW br.com.LeoChiarelli.api.domain.dto.EstatisticasVendasDTO(
                prod.cataegoria,
                SUM(i.quantidade),
                SUM(i.precoUnitario * i.quantidade)
            )
            FROM Pedido p
            JOIN p.itens i
            JOIN i.produto prod
            WHERE p.data = :dataOntem
            GROUP BY prod.catagoria
            """)
    List<EstatisticasVendasDTO> faturamentoTotalPorCategoriaEDia(LocalDate dataOntem);
}
