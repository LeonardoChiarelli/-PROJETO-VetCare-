package br.com.LeoChiarelli.api.general.infra.strategies;

import br.com.LeoChiarelli.api.domain.dto.CadastrarAdocaoDTO;
import br.com.LeoChiarelli.api.domain.repository.IPetRepository;
import br.com.LeoChiarelli.api.general.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PetDisponivel implements IStrategy{

    @Autowired
    private IPetRepository repository;

    @Override
    public void validar(CadastrarAdocaoDTO dto) {

        var petDisponivel = repository.existsByIdAndStatusDisponivel(dto.idPet());

        if (!petDisponivel) { throw new ValidacaoException("Pet não está disponível para adoção no momento"); }
    }
}
