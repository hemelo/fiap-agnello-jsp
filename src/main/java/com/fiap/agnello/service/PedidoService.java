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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EnderecoRepository enderecoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final CupomDescontoRepository cupomRepository;

    public Pedido criarPedido(Long usuarioId, Long enderecoId, List<ItemPedidoDto> itensDTO, String codigoCupom) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Endereco endereco = enderecoRepository.findById(enderecoId)
                .orElseThrow(() -> new EntityNotFoundException("Endereço inválido"));

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setEndereco(endereco);
        pedido.setStatus("PENDENTE");
        pedido.setItens(new ArrayList<>());

        double total = 0;

        for (ItemPedidoDto itemDTO : itensDTO) {
            Produto produto = produtoRepository.findById(itemDTO.produtoId())
                    .orElseThrow(() -> new EntityNotFoundException("Produto inválido"));

            ItemPedido item = new ItemPedido();
            item.setProduto(produto);
            item.setQuantidade(itemDTO.quantidade());
            item.setPrecoUnitario(produto.getPreco());
            item.setPedido(pedido);

            pedido.getItens().add(item);
            total += item.getPrecoUnitario().doubleValue() * item.getQuantidade();
        }

        if (codigoCupom != null) {
            CupomDesconto cupom = cupomRepository.findByCodigoAndAtivoTrue(codigoCupom)
                    .orElseThrow(() -> new EntityNotFoundException("Cupom inválido ou expirado"));

            if (cupom.getValidade().isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("Cupom expirado");
            }

            total -= cupom.getValor();
        }

        pedido.setValorTotal(total);
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarPorUsuario(Long usuarioId) {
        return pedidoRepository.findByUsuarioId(usuarioId);
    }

    public Optional<Pedido> buscarPorId(Long pedidoId) {
        return pedidoRepository.findById(pedidoId);
    }
}
