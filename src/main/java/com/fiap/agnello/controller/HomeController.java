package com.fiap.agnello.controller;

import com.fiap.agnello.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final ProdutoService produtoService;

    @GetMapping(value = {"/", "/home", "/index"})
    public String home(Model model) {

        try {
            model.addAttribute("destaque", produtoService.getProdutoDestaque());
        } catch (EmptyResultDataAccessException e) {
            log.debug("Produto destaque não encontrado");
        }

        try {
            model.addAttribute("categorias", produtoService.getCategoriasPorTipo());
        } catch (EmptyResultDataAccessException e) {
            log.debug("Categorias não encontradas");
        }

        try {
            model.addAttribute("paises", produtoService.getPaisesComProdutos());
        } catch (EmptyResultDataAccessException e) {
            log.debug("Paises não encontrados");
        }

        try {
            model.addAttribute("recomendados", produtoService.getProdutosRecomendados());
        } catch (EmptyResultDataAccessException e) {
            log.debug("Produtos recomendados não encontrados");
        }

        return "index";
    }
}
