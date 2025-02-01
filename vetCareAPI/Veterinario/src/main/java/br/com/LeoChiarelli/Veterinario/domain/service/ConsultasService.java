package br.com.LeoChiarelli.Veterinario.domain.service;

import br.com.LeoChiarelli.Veterinario.domain.dto.AtualizarConsultaDTO;
import br.com.LeoChiarelli.Veterinario.domain.dto.CadastrarConsultaDTO;
import br.com.LeoChiarelli.Veterinario.domain.dto.DetalhesConsultaDTO;
import br.com.LeoChiarelli.Veterinario.domain.dto.ListaConsultasDTO;
import br.com.LeoChiarelli.Veterinario.domain.model.Consulta;
import br.com.LeoChiarelli.Veterinario.domain.repository.IConsultaRepository;
import br.com.LeoChiarelli.Veterinario.domain.repository.IPetRepository;
import br.com.LeoChiarelli.Veterinario.domain.repository.IVeterinarioRepository;
import br.com.LeoChiarelli.Veterinario.general.infra.exception.ValidacaoException;
import br.com.LeoChiarelli.Veterinario.general.infra.validators.IValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultasService {

    @Autowired
    private IConsultaRepository repository;

    @Autowired
    private IPetRepository petRepository;

    @Autowired
    private IVeterinarioRepository veterinarioRepository;

    @Autowired
    private List<IValidation> validadores;

    public DetalhesConsultaDTO agendar(@Valid CadastrarConsultaDTO dto) {

        if (!petRepository.existsById(dto.idPet())) { throw new ValidacaoException("Id do pet informado não existe !"); }
        if (!veterinarioRepository.existsById(dto.idVeterinario())) { throw new ValidacaoException("Id do veterinário informado não existe!"); }

        validadores.forEach(v -> v.validar(dto));

        var pet = petRepository.getReferenceById(dto.idPet());
        var veterinario = veterinarioRepository.getReferenceById(dto.idVeterinario());

        if (dto.detalhes() != null) {
            var consulta = new Consulta(null, pet, veterinario, dto.dataHora(), dto.detalhes());
            repository.save(consulta);
            return new DetalhesConsultaDTO(consulta);
        } else {
            var consulta = new Consulta(null, pet, veterinario, dto.dataHora(), null);
            repository.save(consulta);
            return new DetalhesConsultaDTO(consulta);
        }
    }

    public DetalhesConsultaDTO mudarHorario(@Valid AtualizarConsultaDTO dto) {
        var consulta = repository.getReferenceById(dto.id());
        consulta.mudarHorario(dto);

        return new DetalhesConsultaDTO(consulta);
    }

    public void confirmar(Long id) {
        var consulta = repository.findById(id).orElseThrow(() -> new ValidacaoException("Consulta não encontrada"));
        consulta.confirmar();
    }

    public void concluir(Long id) {
        var consulta = repository.findById(id).orElseThrow(() -> new ValidacaoException("Consulta não encontrada"));
        consulta.concluir();
    }

    public Page<ListaConsultasDTO> listar(Long idVeterinario, Pageable pageable) {
        return repository.findAllByVeterinarioId(idVeterinario, pageable).map(ListaConsultasDTO::new);
    }

    public DetalhesConsultaDTO detalhar(Long id) {
        return new DetalhesConsultaDTO(repository.getReferenceById(id));
    }

    public void cancelar(Long id) {
        repository.deleteById(id);
    }
}
