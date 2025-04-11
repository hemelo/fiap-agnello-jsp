package com.fiap.agnello.repository;

import com.fiap.agnello.model.Assinatura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssinaturaRepository extends JpaRepository<Assinatura, Long> {
    Optional<Assinatura> findByUsuarioIdAndAtivo(Long usuarioId, Boolean ativo);
}
