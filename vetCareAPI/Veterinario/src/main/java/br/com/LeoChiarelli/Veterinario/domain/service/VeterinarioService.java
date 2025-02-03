package br.com.LeoChiarelli.Veterinario.domain.service;

import br.com.LeoChiarelli.Veterinario.domain.dto.AtualizarDadosVeterinarioDTO;
import br.com.LeoChiarelli.Veterinario.domain.dto.CadastrarVeterinarioDTO;
import br.com.LeoChiarelli.Veterinario.domain.dto.DetalhesVeterinarioDTO;
import br.com.LeoChiarelli.Veterinario.domain.dto.ListaVeterinariosDTO;
import br.com.LeoChiarelli.Veterinario.domain.model.Veterinario;
import br.com.LeoChiarelli.Veterinario.domain.repository.IPerfilRepository;
import br.com.LeoChiarelli.Veterinario.general.infra.exception.ValidacaoException;
import br.com.LeoChiarelli.Veterinario.domain.repository.IVeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VeterinarioService {

    @Autowired
    private IVeterinarioRepository repository;

    @Autowired
    private IPerfilRepository perfilRepository;

    public Veterinario cadastrar(CadastrarVeterinarioDTO dto) {

        boolean jaCadastrado = repository.existsByCrmOrEmail(dto.crm(), dto.email());

        if (jaCadastrado) { throw new ValidacaoException("Email ou CRM já cadastrados para outro veterinário, verfique os dados informados"); }

        var perfil = perfilRepository.getReferenceById(3L);
        var veterinario = new Veterinario(dto, perfil);
        repository.save(veterinario);
        return veterinario;
    }

    public Veterinario atualizar(AtualizarDadosVeterinarioDTO dto){
        var veterinario = repository.getReferenceById(dto.id());
        veterinario.atualizarInfo(dto);
        return veterinario;
    }

    public void desativar(Long id){
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
