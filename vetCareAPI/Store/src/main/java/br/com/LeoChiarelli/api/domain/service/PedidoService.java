package br.com.LeoChiarelli.api.domain.service;

import br.com.LeoChiarelli.api.domain.dto.CadastrarPedidoDTO;
import br.com.LeoChiarelli.api.domain.model.ItemPedido;
import br.com.LeoChiarelli.api.domain.model.Pedido;
import br.com.LeoChiarelli.api.domain.repository.IEstoqueRepository;
import br.com.LeoChiarelli.api.domain.repository.IPedidoRepository;
import br.com.LeoChiarelli.api.domain.repository.IProdutoRepository;
import br.com.LeoChiarelli.api.general.infra.exception.ValidacaoException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PedidoService {

    @Autowired
    private IPedidoRepository repository;

    @Autowired
    private IEstoqueRepository estoqueRepository;

    @Autowired
    private IProdutoRepository produtoRepository;

    public Pedido cadastrar(@Valid CadastrarPedidoDTO dto) {

        var itens = new ArrayList<ItemPedido>();
        for (var itemDTO : dto.itens()){
            var estoque = estoqueRepository.findByProdutoId(itemDTO.idProduto());
            if (estoque.getQuantidade() >= itemDTO.quantidade()){
                var produto = produtoRepository.findById(itemDTO.idProduto()).orElseThrow(() -> new ValidacaoException("Produto não encontrado"));

                if (!produto.isAtivo()){ throw new ValidacaoException("Produto não está ativo"); }

                var itemPedido = new ItemPedido(produto, itemDTO.quantidade());
                itens.add(itemPedido);
                estoque.diminuir(itemDTO.quantidade());
            } else{
                throw new ValidacaoException("Produto indisponível para o produto");
            }
        }

        return repository.save(new Pedido(itens));
    }
}
