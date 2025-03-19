package br.com.LeoChiarelli.api.domain.repository;

import br.com.LeoChiarelli.api.domain.model.Estoque;
import br.com.LeoChiarelli.api.domain.model.Produto;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IEstoqueRepository extends JpaRepository<Estoque, Long> {

    boolean existsByProduto_Id(@NotNull Long idProduto);

    Estoque findByProduto_Id(@NotNull Long idProduto);

    @Query("SELECT e.produto FROM Estoque e WHERE e.quantidade = 0")
    List<Produto> produtosSemEstoque();
}
