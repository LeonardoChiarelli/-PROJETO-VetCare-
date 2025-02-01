package br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.service;

import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.dto.CadastrarPagamentoDTO;
import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.dto.DetalhesPagamentoDTO;
import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.dto.ListaPagamentosDTO;
import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.model.Pagamento;
import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.repository.IPagamentoRepository;
import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.general.infra.exception.ValidacaoException;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    @Autowired
    private IPagamentoRepository repository;

    public Pagamento cadastrar(CadastrarPagamentoDTO dto) {
        var pagamento = new Pagamento(dto);
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
