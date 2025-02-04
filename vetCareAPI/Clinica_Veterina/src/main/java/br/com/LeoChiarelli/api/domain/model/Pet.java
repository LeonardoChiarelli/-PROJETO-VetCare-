package br.com.LeoChiarelli.api.domain.model;

import br.com.LeoChiarelli.api.domain.dto.CadastrarPetDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

    @Enumerated(EnumType.STRING)
    private Especie especie;
    private String raca;
    private LocalDate dataAniversario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veterinario_id")
    private Veterinario veterinario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    public Pet(CadastrarPetDTO dto, Veterinario veterinario, Tutor tutor) {
        this.id = null;
        this.nome = dto.nome();
        this.especie = dto.especie();
        this.raca = dto.raca();
        this.dataAniversario = dto.dataAniversario();
        this.veterinario = veterinario;
        this.tutor = tutor;
    }

    public void adicionarHistorico(Veterinario veterinario){}
    public void listarHistorico(){}

    public void vincular(Veterinario veterinario) {
        this.veterinario = veterinario;
    }
}
