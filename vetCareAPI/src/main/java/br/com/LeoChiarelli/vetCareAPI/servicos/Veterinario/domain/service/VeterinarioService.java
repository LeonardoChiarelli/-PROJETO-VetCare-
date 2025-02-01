package br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.service;

import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.dto.AtualizarDadosVeterinarioDTO;
import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.dto.CadastrarVeterinarioDTO;
import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.dto.DetalhesVeterinarioDTO;
import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.dto.ListaVeterinariosDTO;
import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.model.Veterinario;
import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.general.infra.exception.ValidacaoException;
import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.repository.IVeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<ListaVeterinariosDTO> listar(Pageable pageable) {
        return repository.findAllByAtivoTrue(pageable).map(ListaVeterinariosDTO::new);
    }

    public DetalhesVeterinarioDTO detalhar(Long id) {
        return new DetalhesVeterinarioDTO(repository.getReferenceById(id));
    }
}
