package br.com.LeoChiarelli.api.domain.service;

import br.com.LeoChiarelli.api.domain.dto.CadastrarAdotanteDTO;
import br.com.LeoChiarelli.api.domain.dto.DetalhesAdotanteDTO;
import br.com.LeoChiarelli.api.domain.model.Adotante;
import br.com.LeoChiarelli.api.domain.repository.IAdotanteRepository;
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
}
