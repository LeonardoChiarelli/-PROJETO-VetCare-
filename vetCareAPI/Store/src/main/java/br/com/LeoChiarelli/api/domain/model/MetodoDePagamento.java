package br.com.LeoChiarelli.api.domain.model;

import br.com.LeoChiarelli.api.domain.dto.CadastrarMetodoDePagamentoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "metodos_de_pagamento")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class MetodoDePagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private boolean ativo;

    public MetodoDePagamento(CadastrarMetodoDePagamentoDTO dto) {
        this.nome = dto.nome();
        this.ativo = true;
    }

    public void mudarEstado(boolean estado) {
        this.ativo = estado;
    }
}
