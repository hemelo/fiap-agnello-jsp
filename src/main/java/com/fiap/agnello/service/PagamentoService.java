package com.fiap.agnello.service;

import com.fiap.agnello.model.Pagamento;
import com.fiap.agnello.model.Pedido;
import com.fiap.agnello.repository.CupomDescontoRepository;
import com.fiap.agnello.repository.PagamentoRepository;
import com.fiap.agnello.repository.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final CupomDescontoRepository cupomRepository;
    private final PedidoRepository pedidoRepository;
    private final PagamentoRepository pagamentoRepository;

    public Pagamento registrarPagamento(Long pedidoId, String metodo, Double valor) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));

        if (!pedido.getValorTotal().equals(valor)) {
            throw new IllegalArgumentException("Valor não bate com o pedido");
        }

        Pagamento pagamento = new Pagamento();
        pagamento.setMetodo(metodo);
        pagamento.setValor(valor);
        pagamento.setStatus("APROVADO");
        pagamento.setPedido(pedido);

        pedido.setStatus("PAGO");

        pagamentoRepository.save(pagamento);
        pedidoRepository.save(pedido);

        return pagamento;
    }

    public Optional<Pagamento> buscarPorPedido(Long pedidoId) {
        return pagamentoRepository.findByPedidoId(pedidoId);
    }
}
