package com.fiap.agnello.repository;

import com.fiap.agnello.model.AssinaturaPlano;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssinaturaPlanoRepository extends JpaRepository<AssinaturaPlano, Long> {
    List<AssinaturaPlano> findByAtivoTrue();
    Optional<AssinaturaPlano> findFirstByAtivoTrueOrderByPrecoMensalAsc();
}
