package br.com.LeoChiarelli.api.general.infra.strategies;

import br.com.LeoChiarelli.api.domain.dto.CadastrarAdocaoDTO;
import br.com.LeoChiarelli.api.domain.repository.IPetRepository;
import br.com.LeoChiarelli.api.general.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;

public class PetComAdocaoEmAndamento implements IStrategy{

    @Autowired
    private IPetRepository repository;

    @Override
    public void validar(CadastrarAdocaoDTO dto) {

        var petStatus = repository.existsByIdAndStatusEmProcesso(dto.idPet());

        if (petStatus) { throw new ValidacaoException("Pet já está com uma adoção em andamento no momento"); }
    }
}
