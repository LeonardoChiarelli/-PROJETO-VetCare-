package br.com.LeoChiarelli.api.domain.model;

import br.com.LeoChiarelli.api.domain.dto.AtualizarDadosTutorDTO;
import br.com.LeoChiarelli.api.domain.dto.CadastrarTutorDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tutores")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private String telefone;
    private String email;

    private boolean ativo;

    @ElementCollection(targetClass = Perfil.class)
    @ManyToOne
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;

    public Tutor(@Valid CadastrarTutorDTO dto, Perfil perfil) {
        this.nome = dto.nome();
        this.cpf = dto.cpf();
        this.telefone = dto.telefone();
        this.email = dto.email();
        this.ativo = true;
        this.perfil = perfil;
    }

    public void atualizar(@Valid AtualizarDadosTutorDTO dto) {
        if (dto.telefone() != null) { this.telefone = dto.telefone(); }
    }

    public void desativar() {
        this.ativo = false;
    }

    public void ativar(){
        this.ativo = true;
    }

}
