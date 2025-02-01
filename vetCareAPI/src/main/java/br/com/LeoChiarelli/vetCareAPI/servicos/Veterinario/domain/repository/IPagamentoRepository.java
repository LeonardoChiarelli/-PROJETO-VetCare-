package br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.repository;

import br.com.LeoChiarelli.vetCareAPI.servicos.Veterinario.domain.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface IPagamentoRepository extends JpaRepository<Pagamento, Long> {

    @Query("DELETE FROM Pagamento p WHERE p.status_pagamento = 'CANCELADO' ")
    @Modifying
    void deleteByStatus();
}
