package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.model;

import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto.AtualizarDadosVeterinarioDTO;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto.CadastrarVeterinarioDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "medicos")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Veterinario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String crm;
    private String email;
    private String telefone;
    private boolean ativo;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    private LocalDateTime agenda;

    public Veterinario(CadastrarVeterinarioDTO dto){
        this.ativo = true;
        this.id = null;
        this.nome = dto.nome();
        this.crm = dto.crm();
        this.email = dto.email();
        this.telefone = dto.telefone();
        this.especialidade = dto.especialidade();
    }

    public void agendarConsulta(Pet pet, LocalDateTime horario){}
    public void listarConsultas(){}

    public void atualizarInfo(@Valid AtualizarDadosVeterinarioDTO dto) {
        if (dto.email() != null) { this.email = dto.email(); }
        if (dto.telefone() != null) { this.telefone = dto.telefone(); }
    }

    public void desativar() {
        this.ativo = false;
    }
}
