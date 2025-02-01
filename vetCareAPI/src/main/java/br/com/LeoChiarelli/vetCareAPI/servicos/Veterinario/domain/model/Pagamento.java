package br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.model;

import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.dto.CadastrarPagamentoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagamentos")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal valor;

    private String titularCartao;
    private String numeroCartao;
    private String expiracaoCartao;
    private String codigoDeSeguranca;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;
    private LocalDateTime data;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consulta_id")
    private Long consultaId;

    @ManyToOne
    @JoinColumn(name = "metodo_de_pagamento_id")
    private Long metodoDePagamentoId;

    public Pagamento(CadastrarPagamentoDTO dto) {
        this.id = null;
        this.valor = dto.valor();
        this.titularCartao = dto.titularCartao();
        this.numeroCartao = dto.numeroCartao();
        this.expiracaoCartao = dto.expiracaoCartao();
        this.codigoDeSeguranca = dto.codigoDeSeguranca();
        this.status = StatusPagamento.CRIADO;
        this.data = LocalDateTime.now();
        this.consultaId = dto.consultaId();
        this.metodoDePagamentoId = dto.metodoDePagamentoId();
    }

    public void processarPagamento(){}
    public void gerarRecibo(){}

    public void aprovar() {
        this.status = StatusPagamento.APROVADO;
    }

    public void reprovar() {
        this.status = StatusPagamento.CANCELADO;
    }
}
