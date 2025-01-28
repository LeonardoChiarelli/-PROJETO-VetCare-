package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto;

import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.model.Veterinario;

public record ListaVeterinariosDTO(
        Long id,
        String nome,
        String telefone,
        Especialidade especialidade,
        String crm
) {
    public ListaVeterinariosDTO(Veterinario veterinario){
        this(veterinario.getId(), veterinario.getNome(), veterinario.getTelefone(), veterinario.getEspecialidade(), veterinario.getCrm());
    }
}
