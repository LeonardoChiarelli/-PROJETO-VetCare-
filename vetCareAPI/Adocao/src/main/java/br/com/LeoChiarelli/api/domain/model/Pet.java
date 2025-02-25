package br.com.LeoChiarelli.api.domain.model;

import br.com.LeoChiarelli.api.domain.dto.CadastrarPetDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "pets")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private LocalDate dataNascimento;
    private int idade;

    @Enumerated(EnumType.STRING)
    private Especie especie;

    @Enumerated(EnumType.STRING)
    private Porte porte;

    private Float peso;
    private String cor;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusPet status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "abrigo_id")
    private Abrigo abrigo;

    @OneToOne(mappedBy = "pet", fetch = FetchType.LAZY)
    private Adocao adocao_id;

    public Pet(@Valid CadastrarPetDTO dto, Abrigo abrigo) {
        this.nome = dto.nome();
        this.dataNascimento = dto.dataNascimento();
        this.idade = getIdade();
        this.especie = dto.especie();
        this.porte = dto.porte();
        this.peso = dto.peso();
        this.cor = dto.cor();
        this.descricao = dto.descricao();
        this.status = StatusPet.DISPONIVEL;
        this.abrigo = abrigo;
    }

    // Getter dinâmico para calcular a idade corretamente
    public int getIdade() {
        if (dataNascimento == null) {
            return 0;
        }
        return Period.between(this.dataNascimento, LocalDate.now()).getYears();
    }

    public void mudaStatus(StatusPet status) {
        this.status = status;
    }
}
