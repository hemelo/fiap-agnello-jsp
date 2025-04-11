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
    }
}
