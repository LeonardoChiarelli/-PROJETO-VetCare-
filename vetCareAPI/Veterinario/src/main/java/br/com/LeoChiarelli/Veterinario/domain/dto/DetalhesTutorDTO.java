package br.com.LeoChiarelli.Veterinario.domain.dto;

import br.com.LeoChiarelli.Veterinario.domain.model.Pet;
import br.com.LeoChiarelli.Veterinario.domain.model.Tutor;

import java.util.List;

public record DetalhesTutorDTO(Long id, String nome, String cpf, String telefone, String email, List<Pet> pets) {

    public DetalhesTutorDTO(Tutor tutor){
        this(tutor.getId(), tutor.getNome(), tutor.getCpf(), tutor.getTelefone(), tutor.getEmail(), tutor.getPets());
    }
}
