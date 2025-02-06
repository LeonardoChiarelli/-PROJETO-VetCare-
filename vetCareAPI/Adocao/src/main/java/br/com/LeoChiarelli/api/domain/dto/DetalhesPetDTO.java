package br.com.LeoChiarelli.api.domain.dto;

import br.com.LeoChiarelli.api.domain.model.Especie;
import br.com.LeoChiarelli.api.domain.model.Pet;
import br.com.LeoChiarelli.api.domain.model.Porte;
import br.com.LeoChiarelli.api.domain.model.StatusPet;

import java.time.LocalDate;

public record DetalhesPetDTO(String nome, LocalDate dataNascimento, int idade, Especie especie, Porte porte, Float peso, String cor, String descricao, StatusPet status) {
    public DetalhesPetDTO(Pet pet) {
        this(pet.getNome(), pet.getDataNascimento(), pet.getIdade(), pet.getEspecie(), pet.getPorte(), pet.getPeso(), pet.getCor(), pet.getDescricao(), pet.getStatus());
    }
}
