package br.com.LeoChiarelli.api.domain.dto;

import br.com.LeoChiarelli.api.domain.model.Abrigo;
import br.com.LeoChiarelli.api.domain.model.Endereco;

public record DetalhesAbrigoDTO(Long id, String nome, String telefone, String email, Endereco endereco) {

    public DetalhesAbrigoDTO(Abrigo abrigo){
        this(abrigo.getId(), abrigo.getNome(), abrigo.getTelefone(), abrigo.getEmail(), abrigo.getEndereco());
    }
}
