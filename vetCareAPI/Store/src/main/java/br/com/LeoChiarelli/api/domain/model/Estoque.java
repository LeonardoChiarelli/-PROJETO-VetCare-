package br.com.LeoChiarelli.api.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "estoques")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantidade;

    @OneToOne
    private Produto produto;

    @Version
    private Integer versao;

    public Estoque(Produto produto, @NotNull Integer quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public void atualizarEstoque(Integer quantidade){
        this.quantidade += quantidade;
    }

    public void diminuir(@NotNull @Min(1) Integer quantidade) { this.quantidade -= quantidade; }
}
