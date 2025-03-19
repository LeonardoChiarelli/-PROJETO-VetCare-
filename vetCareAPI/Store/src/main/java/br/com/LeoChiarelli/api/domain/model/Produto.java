package br.com.LeoChiarelli.api.domain.model;

import br.com.LeoChiarelli.api.domain.dto.CadastrarProdutoDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Produtos")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@ToString
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    private BigDecimal preco;
    private boolean ativo;

    public Produto(CadastrarProdutoDTO dto) {
        this.nome = dto.nome();
        this.descricao = dto.descricao();
        this.categoria = dto.categoria();
        this.preco = dto.preco();
        this.ativo = true;
    }

    public void mudarEstado(boolean estado) {
        this.ativo = estado;
    }
}
