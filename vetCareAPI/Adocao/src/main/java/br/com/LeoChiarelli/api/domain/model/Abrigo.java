package br.com.LeoChiarelli.api.domain.model;

import br.com.LeoChiarelli.api.domain.dto.AtualizarAbrigoDTO;
import br.com.LeoChiarelli.api.domain.dto.CadastrarAbrigoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "abrigos")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Abrigo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cnpj;
    private String telefone;
    private String email;

    @Embedded
    private Endereco endereco;

    @ElementCollection(targetClass = Perfil.class)
    @ManyToOne
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;

    @OneToMany(mappedBy = "abrigo", cascade = CascadeType.ALL)
    private List<Pet> pets;

    public Abrigo(CadastrarAbrigoDTO dto, Perfil perfil) {
        this.nome = dto.nome();
        this.cnpj = dto.cnpj();
        this.telefone = dto.telefone();
        this.email = dto.email();
        this.endereco = new Endereco(dto.endereco());
        this.perfil = perfil;
    }

    public void atualizarInfo(AtualizarAbrigoDTO dto) {
        if (dto.email() != null) { this.email = dto.email(); }
        if (dto.telefone() != null) { this.telefone = dto.telefone(); }
        if (dto.endereco() != null) { this.endereco.atualizar(dto.endereco()); }
    }
}
