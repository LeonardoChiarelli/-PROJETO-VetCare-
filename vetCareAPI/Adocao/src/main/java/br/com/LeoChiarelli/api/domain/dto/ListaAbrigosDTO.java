package br.com.LeoChiarelli.api.domain.dto;

import br.com.LeoChiarelli.api.domain.model.Abrigo;
import br.com.LeoChiarelli.api.domain.model.Endereco;

public record ListaAbrigosDTO(Long id, String nome, String telefone, Endereco endereco) {
    public ListaAbrigosDTO(Abrigo abrigo){
        this(abrigo.getId(), abrigo.getNome(), abrigo.getTelefone(), abrigo.getEndereco());
    }
}
