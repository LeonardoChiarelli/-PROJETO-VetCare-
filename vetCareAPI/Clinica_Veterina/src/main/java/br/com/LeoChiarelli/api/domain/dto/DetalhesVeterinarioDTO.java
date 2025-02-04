package br.com.LeoChiarelli.api.domain.dto;

import br.com.LeoChiarelli.api.domain.model.Veterinario;

public record DetalhesVeterinarioDTO(Long id, String nome, String crm, String email, String telefone) {
    public DetalhesVeterinarioDTO(Veterinario veterinario){
        this(veterinario.getId(), veterinario.getNome(), veterinario.getCrm(), veterinario.getEmail(), veterinario.getTelefone());
    }
}
