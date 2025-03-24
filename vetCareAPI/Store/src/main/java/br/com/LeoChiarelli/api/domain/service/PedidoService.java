package br.com.LeoChiarelli.api.domain.service;

import br.com.LeoChiarelli.api.domain.dto.CadastrarPedidoDTO;
import br.com.LeoChiarelli.api.domain.model.ItemPedido;
import br.com.LeoChiarelli.api.domain.model.Pedido;
import br.com.LeoChiarelli.api.domain.repository.*;
import br.com.LeoChiarelli.api.general.infra.exception.ValidacaoException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
public class PedidoService {

    @Autowired
    private IPedidoRepository repository;

    @Autowired
    private IEstoqueRepository estoqueRepository;

    @Autowired
    private IProdutoRepository produtoRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IItemPedidoRepository itemPedidoRepository;


    public Pedido cadastrar(@Valid CadastrarPedidoDTO dto, HttpServletRequest request) {
        var idUsuario = tokenService.getIdUsuarioDoToken(identificarId(request));
        BigDecimal valorTotal = BigDecimal.valueOf(0);

        try {
            var usuario = usuarioRepository.findById(idUsuario)
                    .orElseThrow(() -> new ValidacaoException("Usuário não encontrado"));

            // Cria e salva o pedido inicialmente sem itens
            var pedido = new Pedido(new ArrayList<>(), usuario);
            pedido = repository.save(pedido);

            var itens = new ArrayList<ItemPedido>();

            for (var itemDTO : dto.itens()) {
                if (itemDTO.idProduto() == null) {
                    throw new ValidacaoException("ID do produto não pode ser nulo");
                }

                var estoque = estoqueRepository.findByProduto_Id(itemDTO.idProduto());
                if (estoque == null) {
                    throw new ValidacaoException("Estoque não encontrado para o produto");
                }

                if (estoque.getQuantidade() < itemDTO.quantidade()) {
                    throw new ValidacaoException("Estoque indisponível para o produto");
                }

                var produto = produtoRepository.findById(itemDTO.idProduto())
                        .orElseThrow(() -> new ValidacaoException("Produto não encontrado"));

                if (!produto.isAtivo()) {
                    throw new ValidacaoException("Produto não está ativo");
                }

                // Criar o item já com o pedido associado
                var itemPedido = new ItemPedido(pedido, produto, itemDTO.quantidade(), produto.getPreco());
                itens.add(itemPedido);

                estoque.diminuir(itemDTO.quantidade());

                valorTotal = valorTotal.add(itemPedido.getPrecoUnitario().multiply(BigDecimal.valueOf(itemPedido.getQuantidade())));

            }

            // Salva todos os itens de uma vez
            itemPedidoRepository.saveAll(itens);


            pedido.salvarItens(itens);
            pedido.salvarValor(valorTotal);
            repository.save(pedido);

            return pedido;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private String identificarId(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")){
            System.out.println("Token extraído: " + authHeader.substring(7));
            return authHeader.substring(7);
        } else { return null; }
    }
}
