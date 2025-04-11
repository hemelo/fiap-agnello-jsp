package com.fiap.agnello.controller;

import com.fiap.agnello.dto.CarrinhoDto;
import com.fiap.agnello.model.Produto;
import com.fiap.agnello.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/carrinho")
@RequiredArgsConstructor
@SessionAttributes("carrinho")
public class CarrinhoController {

    private final CarrinhoDto carrinho;
    private final ProdutoRepository produtoRepository;

    @ModelAttribute("carrinho")
    public CarrinhoDto carrinho() {
        return carrinho;
    }

    @PostMapping("/adicionar")
    public String adicionar(
            @RequestParam Long produtoId,
            @RequestParam(defaultValue = "1") int quantidade
    ) {
        carrinho.adicionarItem(produtoId, quantidade);
        return "redirect:/produtos/" + produtoId;
    }

    @GetMapping
    public ModelAndView visualizar() {
        ModelAndView mv = new ModelAndView("views/carrinho");

        List<Map<String, Object>> itensDetalhados = carrinho.getItens().stream().map(item -> {
            Produto produto = produtoRepository.findById(item.produtoId())
                    .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
            Map<String, Object> map = new HashMap<>();
            map.put("produto", produto);
            map.put("quantidade", item.quantidade());
            return map;
        }).toList();

        mv.addObject("itens", itensDetalhados);
        return mv;
    }

    @PostMapping("/remover")
    public String remover(@RequestParam Long produtoId) {
        carrinho.removerItem(produtoId);
        return "redirect:/carrinho";
    }

    @PostMapping("/limpar")
    public String limpar() {
        carrinho.limpar();
        return "redirect:/carrinho";
    }
}
