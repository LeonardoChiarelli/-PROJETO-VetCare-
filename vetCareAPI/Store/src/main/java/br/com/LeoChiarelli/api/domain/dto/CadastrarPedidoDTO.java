package br.com.LeoChiarelli.api.domain.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CadastrarPedidoDTO(@NotNull @Valid List<ItensPedidosDTO> itens) {
}
