package br.com.LeoChiarelli.Veterinario.domain.model;

import br.com.LeoChiarelli.Veterinario.domain.dto.AtualizarDadosTutorDTO;
import br.com.LeoChiarelli.Veterinario.domain.dto.CadastrarTutorDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @OneToMany
    @JoinColumn(name = "pet_id")
    private List<Pet> pets;

    private boolean ativo;

    public Tutor(@Valid CadastrarTutorDTO dto, Pet pet) {
        this.nome = dto.nome();
        this.cpf = dto.cpf();
        this.email = dto.email();
        this.pets.add(pet);
        this.ativo = true;
    }

    public void atualizar(@Valid AtualizarDadosTutorDTO dto) {
        if (dto.telefone() != null) { this.telefone = dto.telefone(); }
        if (dto.email() != null) { this.email = dto.email(); }
    }

    public void desativar() {
        this.ativo = false;
    }

    public void adicionarPet(Pet pet) {
        this.pets.add(pet);
    }
}
