package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.service;

import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto.AtualizarDadosVeterinarioDTO;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto.CadastrarVeterinarioDTO;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.model.Veterinario;
import br.com.LeoChiarelli.vetCareAPI.general.infra.exception.ValidacaoException;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.repository.IVeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VeterinarioService {

    @Autowired
    private IVeterinarioRepository repository;

    public Veterinario cadastrar(CadastrarVeterinarioDTO dto) {

        boolean jaCadastrado = repository.existsByCrmOrEmail(dto.crm(), dto.email());

        if (jaCadastrado) { throw new ValidacaoException("Email ou CRM já cadastrados para outro veterinário, verfique os dados informados"); }

        var veterinario = new Veterinario(dto);
        repository.save(veterinario);
        return veterinario;
    }

    public Veterinario atualizar(AtualizarDadosVeterinarioDTO dto){
        var veterinario = repository.getReferenceById(dto.id());
        veterinario.atualizarInfo(dto);
        return veterinario;
    }

    public void deletar(Long id){
        var veterinario = repository.getReferenceById(id);

        veterinario.desativar();
    }

    public void ativar(Long id) {
        var veterinario = repository.getReferenceById(id);

        veterinario.ativar();
    }
}
