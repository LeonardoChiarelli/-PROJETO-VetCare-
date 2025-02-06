package br.com.LeoChiarelli.api.domain.model;

import br.com.LeoChiarelli.api.domain.dto.EnderecoDTO;
import jakarta.persistence.Embeddable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String numero;
    private String complemento;

    public Endereco(@NotNull @Valid EnderecoDTO dto) {
        this.logradouro = dto.logradouro();
        this.bairro = dto.bairro();
        this.cep = dto.cep();
        this.cidade = dto.cidade();
        this.uf = dto.uf();
        this.numero = dto.numero();
        this.complemento = dto.complemento();
    }

    public void atualizar(@Valid EnderecoDTO dto) {
        if(dto.logradouro() != null) { this.logradouro = dto.logradouro(); }
        if(dto.bairro() != null) { this.bairro = dto.bairro(); }
        if(dto.cep() != null) { this.cep = dto.cep(); }
        if(dto.cidade() != null) { this.cidade = dto.cidade(); }
        if(dto.uf() != null) { this.uf = dto.uf(); }
        if(dto.numero() != null) { this.numero = dto.numero(); }
        if(dto.complemento() != null) { this.complemento = dto.complemento(); }
    }
}
