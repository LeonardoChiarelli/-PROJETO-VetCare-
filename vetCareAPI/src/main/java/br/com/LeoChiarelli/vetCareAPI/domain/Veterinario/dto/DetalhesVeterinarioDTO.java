package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto;

import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.model.Especialidade;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.model.Veterinario;

public record DetalhesVeterinarioDTO(Long id, String nome, String crm, String email, String telefone, Especialidade especialidade) {
    public DetalhesVeterinarioDTO(Veterinario veterinario){
        this(veterinario.getId(), veterinario.getNome(), veterinario.getCrm(), veterinario.getEmail(), veterinario.getTelefone(), veterinario.getEspecialidade());
    }
}
