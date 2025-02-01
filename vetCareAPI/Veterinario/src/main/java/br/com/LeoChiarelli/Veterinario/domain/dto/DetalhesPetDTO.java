package br.com.LeoChiarelli.Veterinario.domain.dto;

import br.com.LeoChiarelli.Veterinario.domain.model.Especie;
import br.com.LeoChiarelli.Veterinario.domain.model.Pet;

import java.time.LocalDate;

public record DetalhesPetDTO(Long id, String nome, Especie especie, String raca, LocalDate dataAniversario, String nomeVeterinario, String telefone, String email) {
    public DetalhesPetDTO(Pet pet){
        this(pet.getId(), pet.getNome(), pet.getEspecie(), pet.getRaca(), pet.getDataAniversario(), pet.getVeterinario().getNome(), pet.getVeterinario().getTelefone(), pet.getVeterinario().getEmail());
    }
}
