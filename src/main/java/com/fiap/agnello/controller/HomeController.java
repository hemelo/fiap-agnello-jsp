package com.fiap.agnello.controller;

import com.fiap.agnello.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ProdutoService produtoService;

    @GetMapping(value = {"/", "/home", "/index"})
    public String home(Model model) {

        try {
            model.addAttribute("destaque", produtoService.getProdutoDestaque());
        } catch (Exception e) {
            model.addAttribute("destaque", null);
        }

        try {
            model.addAttribute("categorias", produtoService.getCategoriasPorTipo());
        } catch (Exception e) {
            model.addAttribute("categorias", null);
        }

        try {
            model.addAttribute("paises", produtoService.getPaisesComProdutos());
        } catch (Exception e) {
            model.addAttribute("paises", null);
        }

        try {
            model.addAttribute("recomendados", produtoService.getProdutosRecomendados());
        } catch (Exception e) {
            model.addAttribute("recomendados", null);
        }

        return "index";
    }
}
