package br.com.LeoChiarelli.Veterinario.domain.service;

import br.com.LeoChiarelli.Veterinario.domain.dto.CadastrarPagamentoDTO;
import br.com.LeoChiarelli.Veterinario.domain.dto.DetalhesPagamentoDTO;
import br.com.LeoChiarelli.Veterinario.domain.dto.ListaPagamentosDTO;
import br.com.LeoChiarelli.Veterinario.domain.model.Consulta;
import br.com.LeoChiarelli.Veterinario.domain.model.Pagamento;
import br.com.LeoChiarelli.Veterinario.domain.repository.IConsultaRepository;
import br.com.LeoChiarelli.Veterinario.domain.repository.IMetodoPagamentoRepository;
import br.com.LeoChiarelli.Veterinario.domain.repository.IPagamentoRepository;
import br.com.LeoChiarelli.Veterinario.general.infra.exception.ValidacaoException;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PagamentoService {

    @Autowired
    private IPagamentoRepository repository;

    @Autowired
    private IConsultaRepository consultaRepository;

    @Autowired
    private IMetodoPagamentoRepository metodoPagamentoRepository;

    @Transactional
    public Pagamento cadastrar(CadastrarPagamentoDTO dto) {
        var consulta = consultaRepository.buscarConsulta(dto.consultaId()).orElseThrow(() -> new ValidacaoException("Consulta não encontrada"));
        var metodoDePagamento = metodoPagamentoRepository.buscarMetodo(dto.metodoDePagamentoId()).orElseThrow(() -> new ValidacaoException("Método de pagamento não encontrado"));

        var pagamento = new Pagamento(dto, consulta, metodoDePagamento);
        repository.save(pagamento);
        return pagamento;
    }

    public DetalhesPagamentoDTO aprovar(@NotNull Long id) {
        var pagamento = repository.findById(id).orElseThrow(() -> new ValidacaoException("Pagamento não encontrado"));
        pagamento.aprovar();
        return new DetalhesPagamentoDTO(pagamento);
    }

    public void cancelar(Long id) {
        var pagamento = repository.findById(id).orElseThrow(() -> new ValidacaoException("Pagamento não encontrado"));
        pagamento.reprovar();
    }

    public Page<ListaPagamentosDTO> listarPagamentos(Pageable pageable) {
        return repository.findAll(pageable).map(ListaPagamentosDTO::new);
    }

    public DetalhesPagamentoDTO detalhar(Long id) {
        var pagamento = repository.findById(id).orElseThrow(() -> new ValidacaoException("Pagamento não encontrado"));
        return new DetalhesPagamentoDTO(pagamento);
    }

    public void deletar() {
        repository.deleteByStatus();
    }
}
