package br.com.LeoChiarelli.api.domain.service;

import br.com.LeoChiarelli.api.domain.dto.AtualizarInfoAdotanteDTO;
import br.com.LeoChiarelli.api.domain.dto.CadastrarAdotanteDTO;
import br.com.LeoChiarelli.api.domain.dto.DetalhesAdotanteDTO;
import br.com.LeoChiarelli.api.domain.model.Adotante;
import br.com.LeoChiarelli.api.domain.repository.IAdotanteRepository;
import br.com.LeoChiarelli.api.general.infra.exception.ValidacaoException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdotanteService {

    @Autowired
    private IAdotanteRepository repository;

    public DetalhesAdotanteDTO cadastrar(@Valid CadastrarAdotanteDTO dto) {
        var adotante = new Adotante(dto);
        repository.save(adotante);
        return  new DetalhesAdotanteDTO(adotante);
    }

    public DetalhesAdotanteDTO atualizar(@Valid AtualizarInfoAdotanteDTO dto) {
        var adotante = repository.findByCpf(dto.cpf()).orElseThrow(() -> new ValidacaoException("Adoante não encontrado"));
        adotante.atualizarInfo(dto);
        return new DetalhesAdotanteDTO(adotante);
    }

    public DetalhesAdotanteDTO detalhar(String cpf) {
        return new DetalhesAdotanteDTO(repository.findByCpf(cpf).orElseThrow(() -> new ValidacaoException("Adotante não encontrado")));
    }

    public void excluir(String cpf) {
        repository.deleteByCpf(cpf);
    }
}
