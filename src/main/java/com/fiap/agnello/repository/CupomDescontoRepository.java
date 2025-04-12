package com.fiap.agnello.repository;

import com.fiap.agnello.model.CupomDesconto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CupomDescontoRepository extends JpaRepository<CupomDesconto, Long> {
    Optional<CupomDesconto> findByCodigoAndAtivoTrue(String codigo);

    List<CupomDesconto> findAllByAtivoTrue();
}