package com.fiap.agnello.service;

import com.fiap.agnello.model.CupomDesconto;
import com.fiap.agnello.repository.CupomDescontoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CupomDescontoService {

    private final CupomDescontoRepository cupomRepository;

    public Optional<CupomDesconto> buscarPorCodigo(String codigo) {
        return cupomRepository.findByCodigoAndAtivoTrue(codigo.toUpperCase());
    }
}
