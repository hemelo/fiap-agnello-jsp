package com.fiap.agnello.service;

import com.fiap.agnello.dto.ItemPedidoDto;
import com.fiap.agnello.model.*;
import com.fiap.agnello.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioService usuarioService;
    private final ProdutoService produtoService;
    private final EnderecoService enderecoService;
    private final CupomService cupomService;

    public Pedido criarPedido(Long usuarioId, Long enderecoId, List<ItemPedidoDto> itens, String codigoCupom) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(usuarioId);

        Endereco endereco = enderecoService.buscarEnderecoPorId(enderecoId);
        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setEndereco(endereco);
        pedido.setStatus("PENDENTE");
        pedido.setItens(new ArrayList<>());

        double total = 0;

        for (ItemPedidoDto itemDTO : itens) {
            Produto produto = produtoService.buscarPorId(itemDTO.getProdutoId());

            ItemPedido item = new ItemPedido();
            item.setProduto(produto);
            item.setQuantidade(itemDTO.getQuantidade());
            item.setPrecoUnitario(produto.getPreco());
            item.setPedido(pedido);

            pedido.getItens().add(item);
            total += item.getPrecoUnitario().doubleValue() * item.getQuantidade();
        }

        if (codigoCupom != null) {
            CupomDesconto cupom = cupomService.buscarCupomValidoPorCodigo(codigoCupom);

            if (cupom.getValidade().isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("Cupom expirado");
            }

            total -= (cupom.getPercentual() * total / 100);

            pedido.setCupomDesconto(cupom);
        }

        pedido.setValorTotal(total);
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarPorUsuario(Long usuarioId) {
        return pedidoRepository.findByUsuarioId(usuarioId);
    }

    public Pedido buscarPedidoPorId(Long pedidoId) {
        return pedidoRepository.findById(pedidoId).orElseThrow(
                () -> new EntityNotFoundException("Pedido não encontrado"));
    }
}
