package br.com.LeoChiarelli.api.domain.dto;

import br.com.LeoChiarelli.api.domain.model.Categoria;
import br.com.LeoChiarelli.api.domain.model.Produto;

import java.math.BigDecimal;

public record ListaProdutosDTO(Long id, String nome, BigDecimal preco, Categoria categoria) {
    public ListaProdutosDTO(Produto produto){
        this(produto.getId(), produto.getNome(), produto.getPreco(), produto.getCategoria());
    }
}
