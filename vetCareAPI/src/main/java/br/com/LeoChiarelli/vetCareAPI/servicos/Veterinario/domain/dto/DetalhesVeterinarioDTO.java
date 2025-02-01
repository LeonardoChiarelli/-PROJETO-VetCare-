package br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.dto;

import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.model.Veterinario;

public record DetalhesVeterinarioDTO(Long id, String nome, String crm, String email, String telefone) {
    public DetalhesVeterinarioDTO(Veterinario veterinario){
        this(veterinario.getId(), veterinario.getNome(), veterinario.getCrm(), veterinario.getEmail(), veterinario.getTelefone());
    }
}
