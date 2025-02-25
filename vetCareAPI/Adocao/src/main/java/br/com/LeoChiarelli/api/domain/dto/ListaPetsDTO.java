package br.com.LeoChiarelli.api.domain.dto;

import br.com.LeoChiarelli.api.domain.model.Especie;
import br.com.LeoChiarelli.api.domain.model.Pet;
import br.com.LeoChiarelli.api.domain.model.Porte;
import br.com.LeoChiarelli.api.domain.model.StatusPet;

public record ListaPetsDTO(Long id, String nome, int idade, Especie especie, Porte porte, Float peso, StatusPet status) {
    public ListaPetsDTO(Pet pet){
        this(pet.getId(), pet.getNome(), pet.getIdade(), pet.getEspecie(), pet.getPorte(), pet.getPeso(), pet.getStatus());
    }
}
