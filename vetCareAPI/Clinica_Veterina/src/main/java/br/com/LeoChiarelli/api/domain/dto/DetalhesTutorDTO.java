package br.com.LeoChiarelli.api.domain.dto;

import br.com.LeoChiarelli.api.domain.model.Tutor;

public record DetalhesTutorDTO(Long id, String nome, String cpf, String telefone, String email) {

    public DetalhesTutorDTO(Tutor tutor){
        this(tutor.getId(), tutor.getNome(), tutor.getCpf(), tutor.getTelefone(), tutor.getEmail());
    }
}
