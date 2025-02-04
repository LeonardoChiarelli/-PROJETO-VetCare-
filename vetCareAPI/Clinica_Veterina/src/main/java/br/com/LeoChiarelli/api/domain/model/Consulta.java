package br.com.LeoChiarelli.api.domain.model;

import br.com.LeoChiarelli.api.domain.dto.AtualizarConsultaDTO;
import br.com.LeoChiarelli.api.general.infra.validators.Antecedencia;
import br.com.LeoChiarelli.api.general.infra.validators.HorarioFuncionamento;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Consulta")
@Table(name = "consultas")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veterinario_id")
    private Veterinario veterinario;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    private Status status;
    private String detalhes;

    public Consulta(Long id, Pet pet, Veterinario veterinario, @NotNull @Future LocalDateTime dataHora, String detalhes) {
        this.id = id;
        this.pet = pet;
        this.veterinario = veterinario;
        this.dataHora = dataHora;
        this.status = Status.EM_ESPERA;
        this.detalhes = detalhes;
    }

    public void mudarHorario(@Valid AtualizarConsultaDTO dto) {
        Antecedencia.validarTempo(dto);
        HorarioFuncionamento.validarTempo(dto);

        this.dataHora = dto.dataHora();

        if (dto.detalhes() != null){ this.detalhes = dto.detalhes(); }
    }

    public void confirmar() {
        this.status = Status.AGENDADA;
    }

    public void concluir() {
        this.status = Status.CONCLUIDA;
    }
}
