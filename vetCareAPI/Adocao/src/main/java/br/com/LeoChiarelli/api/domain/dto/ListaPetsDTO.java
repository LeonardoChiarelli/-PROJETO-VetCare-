package br.com.LeoChiarelli.api.domain.dto;

import br.com.LeoChiarelli.api.domain.model.Especie;
import br.com.LeoChiarelli.api.domain.model.Pet;
import br.com.LeoChiarelli.api.domain.model.Porte;

public record ListaPetsDTO(Long id, String nome, int idade, Especie especie, Porte porte, Float peso) {
    public ListaPetsDTO(Pet pet){
        this(pet.getId(), pet.getNome(), pet.getIdade(), pet.getEspecie(), pet.getPorte(), pet.getPeso());
    }
}
