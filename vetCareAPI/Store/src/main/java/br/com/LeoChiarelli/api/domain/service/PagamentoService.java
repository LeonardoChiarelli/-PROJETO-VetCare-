package br.com.LeoChiarelli.api.domain.service;

import br.com.LeoChiarelli.api.domain.dto.CadastrarPagamentoDTO;
import br.com.LeoChiarelli.api.domain.model.Pagamento;
import br.com.LeoChiarelli.api.domain.model.StatusPagamento;
import br.com.LeoChiarelli.api.domain.repository.IMetodoDePagamentoRepository;
import br.com.LeoChiarelli.api.domain.repository.IPagamentoRepository;
import br.com.LeoChiarelli.api.domain.repository.IPedidoRepository;
import br.com.LeoChiarelli.api.general.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    @Autowired
    private IPagamentoRepository repository;

    @Autowired
    private IPedidoRepository pedidoRepository;

    @Autowired
    private IMetodoDePagamentoRepository metodoRepository;

    public Pagamento cadastrar(CadastrarPagamentoDTO dto) {

        var pagamentoExistente = repository.existsByStatusAndPedido(dto.idPedido());
        if (pagamentoExistente) { throw new ValidacaoException("Pagamento em processo ou já aprovado."); }

        var pedido = pedidoRepository.findById(dto.idPedido()).orElseThrow(() -> new ValidacaoException("Pedido não encontrado"));
        var metodo = metodoRepository.findById(dto.idMetodo()).orElseThrow(() -> new ValidacaoException("Método de pagamento não encontrado"));
        var pagamento = new Pagamento(pedido.getValorTotal(), dto, pedido, metodo);
        repository.save(pagamento);
        return pagamento;
    }

    public Pagamento mudarStatusPagamento(Long id, StatusPagamento status){
        var pagamento = repository.findById(id).orElseThrow(() -> new ValidacaoException("Pagamento não encontrado"));
        pagamento.mudarStatus(status);
        repository.save(pagamento);
        return pagamento;
    }
}
