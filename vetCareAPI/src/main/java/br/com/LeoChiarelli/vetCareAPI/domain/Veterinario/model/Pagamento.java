package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

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

    @Enumerated(EnumType.STRING)
    private Metodo metodo;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;
    private LocalDate data;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consulta_id")
    private Consulta consulta;

    public void processarPagamento(){}
    public void gerarRecibo(){}
}
