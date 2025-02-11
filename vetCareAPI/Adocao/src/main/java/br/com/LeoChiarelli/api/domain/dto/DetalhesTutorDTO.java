package br.com.LeoChiarelli.api.domain.dto;

import br.com.LeoChiarelli.api.domain.model.Tutor;

public record DetalhesTutorDTO(Long id, String nome, String telefone, String email, String cpf) {
    public DetalhesTutorDTO(Tutor tutor){
        this(tutor.getId(), tutor.getNome(), tutor.getTelefone(), tutor.getEmail(), tutor.getCpf());
    }
}
