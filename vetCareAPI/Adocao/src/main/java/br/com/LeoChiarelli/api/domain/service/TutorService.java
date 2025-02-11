package br.com.LeoChiarelli.api.domain.service;

import br.com.LeoChiarelli.api.domain.dto.AtualizarInfoTutorDTO;
import br.com.LeoChiarelli.api.domain.dto.CadastrarTutorDTO;
import br.com.LeoChiarelli.api.domain.dto.DetalhesTutorDTO;
import br.com.LeoChiarelli.api.domain.model.Tutor;
import br.com.LeoChiarelli.api.domain.repository.ITutorRepository;
import br.com.LeoChiarelli.api.general.infra.exception.ValidacaoException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorService {

    @Autowired
    private ITutorRepository repository;

    public DetalhesTutorDTO cadastrar(@Valid CadastrarTutorDTO dto) {
        var adotante = new Tutor(dto);
        repository.save(adotante);
        return  new DetalhesTutorDTO(adotante);
    }

    public DetalhesTutorDTO atualizar(@Valid AtualizarInfoTutorDTO dto) {
        var adotante = repository.findByCpf(dto.cpf()).orElseThrow(() -> new ValidacaoException("Adoante não encontrado"));
        adotante.atualizarInfo(dto);
        return new DetalhesTutorDTO(adotante);
    }

    public DetalhesTutorDTO detalhar(String cpf) {
        return new DetalhesTutorDTO(repository.findByCpf(cpf).orElseThrow(() -> new ValidacaoException("Tutor não encontrado")));
    }

    public void excluir(String cpf) {
        repository.deleteByCpf(cpf);
    }
}
