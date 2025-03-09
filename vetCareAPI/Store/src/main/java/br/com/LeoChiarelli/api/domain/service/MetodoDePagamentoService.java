package br.com.LeoChiarelli.api.domain.service;

import br.com.LeoChiarelli.api.domain.dto.CadastrarMetodoDePagamentoDTO;
import br.com.LeoChiarelli.api.domain.model.MetodoDePagamento;
import br.com.LeoChiarelli.api.domain.repository.IMetodoDePagamentoRepository;
import br.com.LeoChiarelli.api.general.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetodoDePagamentoService {

    @Autowired
    private IMetodoDePagamentoRepository repository;

    public String cadastrar(CadastrarMetodoDePagamentoDTO dto) {

        var mensagem = "";

        var metodoExistente = repository.existsByNome(dto.nome());
        if (metodoExistente) { throw new ValidacaoException("Método de pagamento já existente"); }

        var metodoDesativado = repository.existsByNomeAndAtivoFalse(dto.nome());
        if (metodoDesativado) {
            var metodo = repository.getReferenceById(dto.id());
            metodo.mudarEstado(true);
            mensagem = "Método de pagamento reativado";
            return mensagem;
        }

        repository.save(new MetodoDePagamento(dto));
        mensagem = "Método de pagamento registrado";
        return mensagem;
    }

    public void desativar(Long id) {
        var metodo = repository.getReferenceById(id);
        metodo.mudarEstado(false);
    }
}
