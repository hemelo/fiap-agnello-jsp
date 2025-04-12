package com.fiap.agnello.controller;

import com.fiap.agnello.dto.CarrinhoDto;
import com.fiap.agnello.model.CupomDesconto;
import com.fiap.agnello.model.Produto;
import com.fiap.agnello.service.AssinaturaService;
import com.fiap.agnello.service.CupomService;
import com.fiap.agnello.service.ProdutoService;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/carrinho")
@RequiredArgsConstructor
@SessionAttributes("carrinho")
public class CarrinhoController {

    private final CarrinhoDto carrinho;
    private final ProdutoService produtoService;
    private final AssinaturaService assinaturaService;
    private final CupomService cupomService;

    @GetMapping
    public ModelAndView visualizar(@RequestParam(required = false) String cupom) {
        ModelAndView mv = new ModelAndView("carrinho");

        List<Map<String, Object>> itensDetalhados = carrinho.getItens().stream().map(item -> {
            Produto produto = produtoService.buscarPorId(item.getProdutoId());
            Map<String, Object> map = new HashMap<>();
            map.put("produto", produto);
            map.put("quantidade", item.getQuantidade());
            return map;
        }).toList();

        mv.addObject("pageTitle", "Carrinho de Compras");
        mv.addObject("cupom", cupom);
        mv.addObject("itens", itensDetalhados);

        try {
            mv.addObject("planoMaisBarato", assinaturaService.buscarPlanoDeAssinaturaMaisBarato());
        } catch (Exception ignored) {}

        if (itensDetalhados.isEmpty()) {
            mv.addObject("erro", "Seu carrinho está vazio.");
            mv.addObject("subtotal", 0.0);
            mv.addObject("desconto", 0.0);
            mv.addObject("total", 0.0);
            return mv;
        }

        double subtotal = itensDetalhados.stream().mapToDouble(i ->
                ((Produto)i.get("produto")).getPreco().doubleValue() * (int)i.get("quantidade")).sum();

        double desconto = 0.0;
        CupomDesconto cupomAplicado = null;

        if (StringUtils.isNotBlank(cupom)) {
            try {
                cupomAplicado = cupomService.buscarCupomValidoPorCodigo(cupom);
                desconto = subtotal * (cupomAplicado.getPercentual() / 100.0);
                carrinho.setCupom(cupom);
            } catch (Exception ex) {
                mv.addObject("erro", "Cupom inválido ou expirado.");
            }
        } else {
            mv.addObject("cupons", cupomService.buscarTodosCuponsValidosExibiveis());
        }

        double total = subtotal - desconto;

        mv.addObject("subtotal", subtotal);
        mv.addObject("desconto", desconto);
        mv.addObject("total", total);
        mv.addObject("cupomAplicado", cupomAplicado);

        return mv;
    }

    @PostMapping("/adicionar")
    public String adicionar(
            @RequestParam Long produtoId,
            @RequestParam(defaultValue = "1") int quantidade
    ) {
        carrinho.adicionarItem(produtoId, quantidade);
        return "redirect:/produtos/" + produtoId;
    }

    @PostMapping("/alterar")
    public String alterarQuantidade(
            @RequestParam Long produtoId,
            @RequestParam int quantidade,
            RedirectAttributes redirectAttributes
    ) {

        if (quantidade < 1) {
            redirectAttributes.addFlashAttribute("erro", "A quantidade mínima é 1.");
            return "redirect:/carrinho";
        }

        carrinho.alterarQuantidade(produtoId, quantidade);
        redirectAttributes.addFlashAttribute("sucesso", "Quantidade atualizada com sucesso.");
        return "redirect:/carrinho";
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
