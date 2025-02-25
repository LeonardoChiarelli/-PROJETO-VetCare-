package br.com.LeoChiarelli.api.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "adocoes")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Adocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adotante_id")
    private Tutor tutor;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "abrigo_id")
    private Abrigo abrigo;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_adocao")
    private StatusAdocao status;

    private String justificativa;

    public Adocao(Abrigo abrigo, Pet pet, Tutor tutor) {
        this.dataHora = LocalDateTime.now();
        this.tutor = tutor;
        this.pet = pet;
        this.abrigo = abrigo;
        this.status = StatusAdocao.SOLICITADA;
        pet.mudaStatus(StatusPet.EM_PROCESSO);
    }

    public void aprovar(Pet pet) {
        this.status = StatusAdocao.APROVADA;
        pet.mudaStatus(StatusPet.ADOTADO);
    }

    public void reprovar(@NotBlank String justificativa) {
        this.status = StatusAdocao.NEGADA;
        this.justificativa = justificativa;
    }
}
