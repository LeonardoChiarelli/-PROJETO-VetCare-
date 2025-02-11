package br.com.LeoChiarelli.api.domain.model;

import br.com.LeoChiarelli.api.domain.dto.AtualizarInfoAdotanteDTO;
import br.com.LeoChiarelli.api.domain.dto.CadastrarAdotanteDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Adotante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private Endereco endereco;

    public Adotante(@Valid CadastrarAdotanteDTO dto) {
        this.nome = dto.nome();
        this.cpf = dto.cpf();
        this.telefone = dto.telefone();
        this.email = dto.email();
        this.endereco = new Endereco(dto.endereco());
    }

    public void atualizarInfo(@Valid AtualizarInfoAdotanteDTO dto) {
        if (dto.telefone() != null) { this.telefone = dto.telefone(); }
        if (dto.email() != null) { this.email = dto.email(); }
        if (dto.endereco() != null) { this.endereco.atualizar(dto.endereco()); }
    }
}
