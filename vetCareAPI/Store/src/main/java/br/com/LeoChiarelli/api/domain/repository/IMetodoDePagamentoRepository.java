package br.com.LeoChiarelli.api.domain.repository;

import br.com.LeoChiarelli.api.domain.model.MetodoDePagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMetodoDePagamentoRepository extends JpaRepository<MetodoDePagamento, Long> {

    boolean existsByNome(String nome);

    boolean existsByNomeAndAtivoFalse(String nome);
}
