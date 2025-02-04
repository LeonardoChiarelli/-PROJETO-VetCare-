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
    @Column(name = "status_pagamento")
    private StatusPagamento status;

    @Column(name = "data_pagamento")
    private LocalDateTime data;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consulta_id")
    private Consulta consulta;

    @ManyToOne
    @JoinColumn(name = "metodo_de_pagamento_id")
    private MetodoDePagamento metodoDePagamento;

    public Pagamento(CadastrarPagamentoDTO dto, Consulta consulta, MetodoDePagamento metodoDePagamento) {
        this.valor = dto.valor();
        this.titularCartao = dto.titularCartao();
        this.numeroCartao = dto.numeroCartao();
        this.expiracaoCartao = dto.expiracaoCartao();
        this.codigoDeSeguranca = dto.codigoDeSeguranca();
        this.status = StatusPagamento.CRIADO;
        this.data = LocalDateTime.now();
        this.consulta = consulta;
        this.metodoDePagamento = metodoDePagamento;
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
