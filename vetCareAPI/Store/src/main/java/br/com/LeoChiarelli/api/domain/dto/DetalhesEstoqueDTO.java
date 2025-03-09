package br.com.LeoChiarelli.api.domain.dto;

import br.com.LeoChiarelli.api.domain.model.Estoque;
import br.com.LeoChiarelli.api.domain.model.Produto;

public record DetalhesEstoqueDTO(Produto produto, Integer quantidade) {
    public DetalhesEstoqueDTO(Estoque e){
        this(e.getProduto(), e.getQuantidade());
    }
}
