package br.com.LeoChiarelli.api.domain.repository;

import br.com.LeoChiarelli.api.domain.model.Estoque;
import br.com.LeoChiarelli.api.domain.model.Produto;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IEstoqueRepository extends JpaRepository<Estoque, Long> {

    @Query("SELECT CASE WHEN COUNT(e.produto) > 0 THEN true ELSE false FROM Estoque e WHERE e.produto.id = :idProduto")
    Boolean existsByIdProduto(@NotNull Long idProduto);

    @Query("SELECT e.produto FROM Estoque e WHERE e.produto.id = :idProduto")
    Produto getReferenceByIdProduto(@NotNull Long idProduto);

    Estoque findByProdutoId(@NotNull Long idProduto);

    @Query("SELECT e.produto FROM Estoque e WHERE e.quantidade = 0")
    List<Produto> produtosSemEstoque();
}
