package com.fiap.agnello.service;

import com.fiap.agnello.model.CupomDesconto;
import com.fiap.agnello.repository.CupomDescontoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CupomService {

    private final CupomDescontoRepository cupomDescontoRepository;

    public CupomDesconto buscarCupomValidoPorCodigo(String codigo) {
        CupomDesconto cupom = cupomDescontoRepository.findByCodigoAndAtivoTrue(codigo)
                .orElseThrow(() -> new IllegalArgumentException("Cupom inválido ou expirado"));

        if (cupom.getValidade().isBefore(java.time.LocalDate.now())) {
            cupom.setAtivo(false);
            cupomDescontoRepository.save(cupom);
            throw new IllegalArgumentException("Cupom expirado");
        }

        return cupom;
    }

    public List<CupomDesconto> buscarTodosCuponsValidos() {
        List<CupomDesconto> cupons = cupomDescontoRepository.findAllByAtivoTrue();

        for (CupomDesconto cupom : cupons) {
            if (cupom.getValidade().isBefore(java.time.LocalDate.now())) {
                cupom.setAtivo(false);
                cupomDescontoRepository.save(cupom);
                cupons.remove(cupom);
            }
        }

        if (cupons.isEmpty()) {
            throw new IllegalArgumentException("Nenhum cupom válido encontrado");
        }

        return cupons;
    }

    public List<CupomDesconto> buscarTodosCuponsValidosExibiveis() {
        List<CupomDesconto> cupons = buscarTodosCuponsValidos();

        return cupons.stream()
                .filter(CupomDesconto::getExibir)
                .toList();

    }

}
