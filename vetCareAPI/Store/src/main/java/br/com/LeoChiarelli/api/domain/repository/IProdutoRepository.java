package br.com.LeoChiarelli.api.domain.repository;

import br.com.LeoChiarelli.api.domain.model.Produto;
import jakarta.validation.constraints.NotBlank;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IProdutoRepository extends JpaRepository<Produto, Long> {

    Boolean existsByNomeAndDescricao(@NotBlank String nome, @NotBlank String descricao);

    @NotNull Page<Produto> findAll(@NotNull Pageable pageable);

    boolean existsByIdAndAtivoFalse(Long id);
}
