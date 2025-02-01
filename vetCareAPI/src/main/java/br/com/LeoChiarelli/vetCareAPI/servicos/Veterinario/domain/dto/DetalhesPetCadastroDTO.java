package br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.dto;

import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.model.Especie;
import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.model.Pet;

import java.time.LocalDate;

public record DetalhesPetCadastroDTO(Long id, String nome, Especie especie, String raca, LocalDate dataAniversario) {

    public DetalhesPetCadastroDTO(Pet pet){
        this(pet.getId(), pet.getNome(), pet.getEspecie(), pet.getRaca(), pet.getDataAniversario());
    }
}
