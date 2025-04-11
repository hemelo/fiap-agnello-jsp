package com.fiap.agnello.controller;

import com.fiap.agnello.model.Produto;
import com.fiap.agnello.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping
    public ModelAndView listar() {
        List<Produto> produtos =  produtoService.listarTodos();

        ModelAndView mv = new ModelAndView("views/catalogo");
        mv.addObject("pageTitle", "Catálogo");
        mv.addObject("produtos", produtos);
        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView detalhes(@PathVariable Long id) {
        Produto produto = produtoService.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        ModelAndView mv = new ModelAndView("views/produto");
        mv.addObject("produto", produto);
        return mv;
    }
}
