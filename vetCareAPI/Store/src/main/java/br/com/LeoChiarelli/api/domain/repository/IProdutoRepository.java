package br.com.LeoChiarelli.api.domain.repository;

import br.com.LeoChiarelli.api.domain.model.Produto;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface IProdutoRepository extends JpaRepository<Produto, Long> {

    Boolean existsByNomeAndDescricao(@NotBlank String nome, @NotBlank String descricao);

    Page<Produto> findAll(Pageable pageable);

    boolean existsByIdAndAtivoFalse(Long id);
}
