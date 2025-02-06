package br.com.LeoChiarelli.api.domain.repository;

import br.com.LeoChiarelli.api.domain.model.Adocao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdocaoRepository extends JpaRepository<Adocao, Long> {
}
