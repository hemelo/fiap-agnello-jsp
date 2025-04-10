package com.fiap.agnello.dto;

import br.com.vinheriaagnello.dto.ItemPedidoDTO;
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
public class Carrinho {

    private List<ItemPedidoDTO> itens = new ArrayList<>();

    public void adicionarItem(Long produtoId, int quantidade) {
        for (ItemPedidoDTO item : itens) {
            if (item.produtoId().equals(produtoId)) {
                int novaQtd = item.quantidade() + quantidade;
                itens.remove(item);
                itens.add(new ItemPedidoDTO(produtoId, novaQtd));
                return;
            }
        }
        itens.add(new ItemPedidoDTO(produtoId, quantidade));
    }

    public void removerItem(Long produtoId) {
        itens.removeIf(item -> item.produtoId().equals(produtoId));
    }

    public void limpar() {
        itens.clear();
    }
}
