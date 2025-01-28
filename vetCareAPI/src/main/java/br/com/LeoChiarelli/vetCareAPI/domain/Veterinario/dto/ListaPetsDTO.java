package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto;

import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.model.Especie;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.model.Pet;

public record ListaPetsDTO(
        Long id,
        String nome,
        Especie especie,
        String raca
        // Tutor tutor
) {
    public ListaPetsDTO(Pet pet){
        this(pet.getId(), pet.getNome(), pet.getEspecie(), pet.getRaca());
    }
}
