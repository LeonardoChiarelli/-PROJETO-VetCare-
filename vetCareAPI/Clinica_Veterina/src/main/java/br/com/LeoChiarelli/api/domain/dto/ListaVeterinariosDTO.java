package br.com.LeoChiarelli.api.domain.dto;

import br.com.LeoChiarelli.api.domain.model.Veterinario;

public record ListaVeterinariosDTO(
        Long id,
        String nome,
        String telefone,
        String crm
) {
    public ListaVeterinariosDTO(Veterinario veterinario){
        this(veterinario.getId(), veterinario.getNome(), veterinario.getTelefone(), veterinario.getCrm());
    }
}
