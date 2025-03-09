package br.com.LeoChiarelli.api.domain.model;

import br.com.LeoChiarelli.api.domain.dto.CadastrarPagamentoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valor;
    private String titularCartao;
    private String numeroCartao;
    private String expiracaoCartao;
    private String codigoSeguranca;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    private LocalDateTime data;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "metodo_de_pagamento_id")
    private MetodoDePagamento metodoDePagamento;

    public Pagamento(CadastrarPagamentoDTO dto, Pedido pedido, MetodoDePagamento metodo) {
        this.valor = dto.valor();
        this.titularCartao = dto.titularCartao();
        this.numeroCartao = dto.numeroCartao();
        this.expiracaoCartao = dto.expiracaoCartao();
        this.codigoSeguranca = dto.codigoSeguranca();
        this.status = StatusPagamento.EM_PROCESSO;
        this.data = LocalDateTime.now();
        this.pedido = pedido;
        this.metodoDePagamento = metodo;
    }

    public void mudarStatus(StatusPagamento statusPagamento) {
        this.status = statusPagamento;
    }
}
