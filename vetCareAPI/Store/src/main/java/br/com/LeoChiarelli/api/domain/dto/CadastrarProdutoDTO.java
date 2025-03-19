package br.com.LeoChiarelli.api.domain.dto;

import br.com.LeoChiarelli.api.domain.model.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CadastrarProdutoDTO(
        Long id,

        @NotBlank
        String nome,

        @NotBlank
        String descricao,

        @NotNull
        Categoria categoria,

        @NotNull
        BigDecimal preco,

        @NotNull
        Integer quantidade
) {
}
