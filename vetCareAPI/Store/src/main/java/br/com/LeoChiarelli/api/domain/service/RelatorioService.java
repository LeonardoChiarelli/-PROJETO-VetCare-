package br.com.LeoChiarelli.api.domain.service;

import br.com.LeoChiarelli.api.domain.dto.DetalhesProdutoDTO;
import br.com.LeoChiarelli.api.domain.dto.RelatorioEstoqueDTO;
import br.com.LeoChiarelli.api.domain.dto.RelatorioFaturamentoDTO;
import br.com.LeoChiarelli.api.domain.repository.IEstoqueRepository;
import br.com.LeoChiarelli.api.domain.repository.IPedidoRepository;
import br.com.LeoChiarelli.api.domain.repository.IProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

@Service
public class RelatorioService {

    @Autowired
    private IProdutoRepository produtoRepository;

    @Autowired
    private IEstoqueRepository estoqueRepository;

    @Autowired
    private IPedidoRepository pedidoRepository;

    @Async
    public CompletableFuture<RelatorioEstoqueDTO> infoEstoque() {
        var produtoSemEstoque = estoqueRepository.produtosSemEstoque()
                .stream()
                .map(DetalhesProdutoDTO::new)
                .toList();
        return CompletableFuture.completedFuture(new RelatorioEstoqueDTO(produtoSemEstoque));
    }

    @Async
    public CompletableFuture<RelatorioFaturamentoDTO> infoFaturamento() {
        var dataOntem = LocalDate.now().minusDays(1);
        var faturamentoTotal = pedidoRepository.faturamentoTotalDoDia(dataOntem);

        var estatisticas = pedidoRepository.faturamentoTotalPorCategoriaEDia(dataOntem);

        return CompletableFuture.completedFuture(new RelatorioFaturamentoDTO(faturamentoTotal, estatisticas));
    }
}
