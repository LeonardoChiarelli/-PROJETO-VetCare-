package br.com.LeoChiarelli.api.domain.dto;

import br.com.LeoChiarelli.api.domain.model.ItemPedido;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ItensPedidosDTO(@NotNull Long idProduto, @NotNull @Min(1) Integer quantidade) {
    public ItensPedidosDTO(ItemPedido i){
        this(i.getProduto().getId(), i.getQuantidade());
    }
}
