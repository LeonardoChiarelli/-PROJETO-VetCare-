package br.com.LeoChiarelli.api.domain.service;

import br.com.LeoChiarelli.api.domain.dto.AtualizarEstoqueDTO;
import br.com.LeoChiarelli.api.domain.dto.DetalhesEstoqueDTO;
import br.com.LeoChiarelli.api.domain.repository.IEstoqueRepository;
import br.com.LeoChiarelli.api.general.infra.exception.ValidacaoException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class EstoqueService {

    @Autowired
    private IEstoqueRepository repository;

    public Page<DetalhesEstoqueDTO> listarProdutos(Pageable pageable) {
        return repository.findAll(pageable).map(DetalhesEstoqueDTO::new);
    }

    public void atualizarEstoque(@Valid AtualizarEstoqueDTO dto) {
        var produtoExistente = repository.existsByIdProduto(dto.idProduto());
        var estoque = repository.findByProdutoId(dto.idProduto());

        if (!produtoExistente | !estoque.getProduto().isAtivo()) { throw new ValidacaoException("Produto não encontrado no estoque ou não está ativo"); }

        estoque.atualizarEstoque(dto.quantidade());
    }
}
