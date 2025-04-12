package com.fiap.agnello.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
@Getter
@Setter
public class CarrinhoDto {

    private String cupom;
    private List<ItemPedidoDto> itens = new ArrayList<>();

    public void adicionarItem(Long produtoId, int quantidade) {
        for (ItemPedidoDto item : itens) {
            if (item.getProdutoId().equals(produtoId)) {
                int novaQtd = item.getQuantidade() + quantidade;
                itens.remove(item);
                itens.add(new ItemPedidoDto(produtoId, novaQtd));
                return;
            }
        }

        itens.add(new ItemPedidoDto(produtoId, quantidade));
    }

    public void removerItem(Long produtoId) {
        itens.removeIf(item -> item.getProdutoId().equals(produtoId));
    }

    public void limpar() {
        itens.clear();
        cupom = null;
    }

    public void alterarQuantidade(Long produtoId, int novaQuantidade) {
        if (novaQuantidade <= 0) {
            removerItem(produtoId);
            return;
        }

        for (int i = 0; i < itens.size(); i++) {
            if (itens.get(i).getProdutoId().equals(produtoId)) {
                itens.set(i, new ItemPedidoDto(produtoId, novaQuantidade));
                return;
            }
        }
    }
}
