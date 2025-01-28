package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto;

import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.model.Especie;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.model.Pet;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.model.Veterinario;

import java.time.LocalDate;

public record DetalhesPetDTO(Long id, String nome, Especie especie, String raca, LocalDate dataAniversario, Veterinario veterinario) {
    public DetalhesPetDTO(Pet pet){
        this(pet.getId(), pet.getNome(), pet.getEspecie(), pet.getRaca(), pet.getDataAniversario(), pet.getVeterinario());
    }
}
