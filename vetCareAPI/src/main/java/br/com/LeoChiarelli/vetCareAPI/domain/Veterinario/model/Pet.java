package br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.model;

import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto.AtualizarDadosPetDTO;
import br.com.LeoChiarelli.vetCareAPI.domain.Veterinario.dto.CadastrarPetDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
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

    public Pet(CadastrarPetDTO dto) {
        this.id = null;
        this.nome = dto.nome();
        this.especie = dto.especie();
        this.raca = dto.raca();
        this.dataAniversario = dto.dataAniversario();
    }

    public void adicionarHistorico(Veterinario veterinario){}
    public void listarHistorico(){}

    public void atualizarVeterinario(@Valid AtualizarDadosPetDTO dto) {
        if (dto.veterinario() != null) { this.veterinario.atualizarInfoPet(dto.veterinario()); }
    }
}
