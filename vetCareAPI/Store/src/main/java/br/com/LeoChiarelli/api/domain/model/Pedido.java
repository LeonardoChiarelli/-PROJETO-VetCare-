package br.com.LeoChiarelli.api.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataPedido;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
    private List<ItemPedido> itens = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private BigDecimal valorTotal;

    public Pedido(ArrayList<ItemPedido> itens, Usuario usuario) {
        this.dataPedido = LocalDateTime.now();
        this.itens = itens;
        this.usuario = usuario;

    }

    public void salvarItens(ArrayList<ItemPedido> itens) {
        this.itens = itens;
    }

    public void salvarValor(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
}
