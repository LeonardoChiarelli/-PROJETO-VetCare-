package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto;

import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.model.Consulta;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.model.Pet;

public record ListaConsultasDTO(
        Long id,
        Pet pet
) {
    public ListaConsultasDTO(Consulta consulta){
        this(consulta.getId(), consulta.getPet());
    }
}
