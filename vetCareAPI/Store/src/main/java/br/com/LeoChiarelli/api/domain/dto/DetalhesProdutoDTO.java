package br.com.LeoChiarelli.api.domain.dto;

import br.com.LeoChiarelli.api.domain.model.Categoria;
import br.com.LeoChiarelli.api.domain.model.Produto;

import java.math.BigDecimal;

public record DetalhesProdutoDTO(Long id, String nome, String descricao, Categoria categoria, BigDecimal preco) {
    public DetalhesProdutoDTO(Produto produto){
        this(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getCategoria(), produto.getPreco());
    }
}
