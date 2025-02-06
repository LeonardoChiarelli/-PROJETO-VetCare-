package br.com.LeoChiarelli.api.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "adocoes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Adocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adotante_id")
    private Adotante adotante;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "abrigo_id")
    private Abrigo abrigo;

    @Enumerated(EnumType.STRING)
    private StatusAdocao status;

    private String justificativa;

    public Adocao(Abrigo abrigo, Pet pet, Adotante adotante) {
        this.dataHora = LocalDateTime.now();
        this.adotante = adotante;
        this.pet = pet;
        this.abrigo = abrigo;
        this.status = StatusAdocao.SOLICITADA;
    }

    public void aprovar() {
        this.status = StatusAdocao.APROVADA;
    }

    public void reprovar(@NotBlank String justificativa) {
        this.status = StatusAdocao.NEGADA;
        this.justificativa = justificativa;
    }
}
