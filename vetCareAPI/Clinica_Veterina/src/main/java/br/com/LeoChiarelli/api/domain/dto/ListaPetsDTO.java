package br.com.LeoChiarelli.api.domain.dto;

import br.com.LeoChiarelli.api.domain.model.Especie;
import br.com.LeoChiarelli.api.domain.model.Pet;

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
