package br.com.LeoChiarelli.api.domain.repository;

import br.com.LeoChiarelli.api.domain.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
}
