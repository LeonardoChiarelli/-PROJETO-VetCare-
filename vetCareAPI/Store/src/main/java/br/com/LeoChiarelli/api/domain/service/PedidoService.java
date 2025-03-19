package br.com.LeoChiarelli.api.domain.service;

import br.com.LeoChiarelli.api.domain.dto.CadastrarPedidoDTO;
import br.com.LeoChiarelli.api.domain.model.ItemPedido;
import br.com.LeoChiarelli.api.domain.model.Pedido;
import br.com.LeoChiarelli.api.domain.repository.IEstoqueRepository;
import br.com.LeoChiarelli.api.domain.repository.IPedidoRepository;
import br.com.LeoChiarelli.api.domain.repository.IProdutoRepository;
import br.com.LeoChiarelli.api.domain.repository.IUsuarioRepository;
import br.com.LeoChiarelli.api.general.infra.exception.ValidacaoException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
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

    @PostConstruct
    public void init(){
        System.out.println("PedidoService carregado com sucesso!");
    }

    public Pedido cadastrar(@Valid CadastrarPedidoDTO dto, HttpServletRequest request) {
        var idUsuario = tokenService.getIdUsuarioDoToken(identificarId(request));
        System.out.println("Id usuário: " + idUsuario);
        try {
            var itens = new ArrayList<ItemPedido>();
            for (var itemDTO : dto.itens()) {

                // DEBUG
                if (itemDTO.idProduto() == null) { throw new ValidacaoException("ID do produto não pode ser nulo"); }

                var estoque = estoqueRepository.findByProduto_Id(itemDTO.idProduto());

                // DEBUG
                if (estoque == null) { throw new ValidacaoException("Estoque não encontrado para o produto"); }

                if (estoque.getQuantidade() >= itemDTO.quantidade()) {
                    var produto = produtoRepository.findById(itemDTO.idProduto()).orElseThrow(() -> new ValidacaoException("Produto não encontrado"));

                    if (!produto.isAtivo()) {
                        throw new ValidacaoException("Produto não está ativo");
                    }

                    var pedido = repository.findByDataPedidoAndUsuario_Id(LocalDateTime.now(), idUsuario).orElseThrow(() -> new ValidacaoException("Pedido não econtrado"));
                    var itemPedido = new ItemPedido(pedido, produto, itemDTO.quantidade());
                    itens.add(itemPedido);
                    estoque.diminuir(itemDTO.quantidade());
                } else {
                    throw new ValidacaoException("Estoque indisponível para o produto");
                }
            }

            var usuario = usuarioRepository.findById(idUsuario).orElseThrow(() -> new ValidacaoException("Usuário não encontrado"));
            return repository.save(new Pedido(itens, usuario));
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
