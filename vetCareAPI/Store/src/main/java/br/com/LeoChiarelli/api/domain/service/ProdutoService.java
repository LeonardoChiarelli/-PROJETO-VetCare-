package br.com.LeoChiarelli.api.domain.service;

import br.com.LeoChiarelli.api.domain.dto.CadastrarProdutoDTO;
import br.com.LeoChiarelli.api.domain.dto.ListaProdutosDTO;
import br.com.LeoChiarelli.api.domain.model.Estoque;
import br.com.LeoChiarelli.api.domain.model.Produto;
import br.com.LeoChiarelli.api.domain.repository.IEstoqueRepository;
import br.com.LeoChiarelli.api.domain.repository.IProdutoRepository;
import br.com.LeoChiarelli.api.general.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private IProdutoRepository repository;

    @Autowired
    private IEstoqueRepository estoqueRepository;

    public Produto cadastrar(CadastrarProdutoDTO dto) {

        var produtoExistente = repository.existsByNomeAndDescricao(dto.nome(), dto.descricao());

        if (produtoExistente){
            throw new ValidacaoException("Produto já existente");
        }

        var produto = new Produto(dto);
        repository.save(produto);


        var produtoEstoque = new Estoque(produto, dto.quantidade());
        estoqueRepository.save(produtoEstoque);

        return produto;
    }

    public Page<ListaProdutosDTO> listar(Pageable pageable) {
        return repository.findAll(pageable).map(ListaProdutosDTO::new);
    }

    public Produto detalhar(Long id) {
        return repository.findById(id).orElseThrow(() -> new ValidacaoException("Produto não encontrado"));
    }

    public void reativar(Long id) {
        var produtoDesativado = repository.existsByIdAndAtivoFalse(id);
        if (!produtoDesativado){ throw new ValidacaoException("Produto não encontrado ou já está ativo"); }

        var produto = repository.getReferenceById(id);
        produto.mudarEstado(true);
    }

    public void desativar(Long id) {
        var produto = repository.findById(id).orElseThrow(() -> new ValidacaoException("Produto não encontrado"));
        produto.mudarEstado(false);
    }
}
