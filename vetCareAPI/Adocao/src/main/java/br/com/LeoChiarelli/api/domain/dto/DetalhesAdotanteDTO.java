package br.com.LeoChiarelli.api.domain.dto;

import br.com.LeoChiarelli.api.domain.model.Adotante;

public record DetalhesAdotanteDTO(Long id, String nome, String telefone, String email, String cpf) {
    public DetalhesAdotanteDTO(Adotante adotante){
        this(adotante.getId(), adotante.getNome(), adotante.getTelefone(), adotante.getEmail(), adotante.getCpf());
    }
}
