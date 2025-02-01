package br.com.LeoChiarelli.Veterinario.domain.dto;

import br.com.LeoChiarelli.Veterinario.domain.model.Especie;
import br.com.LeoChiarelli.Veterinario.domain.model.Pet;

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
