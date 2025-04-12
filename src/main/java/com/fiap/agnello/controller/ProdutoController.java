package com.fiap.agnello.controller;

import com.fiap.agnello.dto.CarrinhoDto;
import com.fiap.agnello.model.Produto;
import com.fiap.agnello.repository.CupomDescontoRepository;
import com.fiap.agnello.repository.ProdutoRepository;
import com.fiap.agnello.repository.spec.ProdutoSpec;
import com.fiap.agnello.service.AssinaturaService;
import com.fiap.agnello.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;
    private final CarrinhoDto carrinho;

    @ModelAttribute("carrinho")
    public CarrinhoDto carrinho() {
        return carrinho;
    }

    @GetMapping
    public ModelAndView listar(@RequestParam(required = false) String nome,
                               @RequestParam(required = false) String classificacao,
                               @RequestParam(required = false) String pais,
                               @RequestParam(required = false) Double teor,
                               @RequestParam(required = false) String vinicola,
                                 @RequestParam(required = false) String tipo,
                               @PageableDefault(size = 10, sort = "nome") Pageable pageable) {

        Page<Produto> pagina = produtoService.filtrarProdutos(
                Specification.where(ProdutoSpec.nomeContem(nome))
                        .and(ProdutoSpec.classificacaoIgual(classificacao))
                        .and(ProdutoSpec.paisIgual(pais))
                        .and(ProdutoSpec.teorIgual(teor))
                        .and(ProdutoSpec.tipoIgual(tipo))
                        .and(ProdutoSpec.vinicolaIgual(vinicola)),
                pageable
        );

        ModelAndView mv = new ModelAndView("produtos");
        mv.addObject("pageTitle", "Vinhos");
        mv.addObject("pagina", pagina);
        mv.addObject("produtos", pagina.getContent());

        try {
            mv.addObject("classificacoes", produtoService.buscarClassificacoes());
        } catch (Exception ignored) {}

        try {
            mv.addObject("teores", produtoService.buscarTeores());
        } catch (Exception ignored) {}

        try {
            mv.addObject("paises", produtoService.buscarPaises());
        } catch (Exception ignored) {}

        try {
            mv.addObject("vinicolas", produtoService.buscarVinicolas());
        } catch (Exception ignored) {}

        try {
            mv.addObject("tipos", produtoService.buscarTipos());
        } catch (Exception ignored) {}

        // mantém filtros para recarregar no form e na URL
        mv.addObject("nome", nome);
        mv.addObject("classificacao", classificacao);
        mv.addObject("pais", pais);
        mv.addObject("teor", teor);
        mv.addObject("vinicola", vinicola);
        mv.addObject("tipo", tipo);

        if (pagina.getContent().isEmpty()) {
            mv.addObject("erro", "Nenhum produto encontrado com os filtros aplicados.");
        }

        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView detalhes(@PathVariable Long id) {
        Produto produto = produtoService.buscarPorId(id);
        ModelAndView mv = new ModelAndView("produto");
        mv.addObject("produto", produto);
        return mv;
    }

    @PostMapping("/adicionar")
    public String adicionar(@RequestParam Long produtoId,
                            @RequestParam(defaultValue = "1") int quantidade,
                            RedirectAttributes redirectAttributes) {
        carrinho.adicionarItem(produtoId, quantidade);
        redirectAttributes.addFlashAttribute("sucesso", "Produto adicionado ao carrinho.");
        return "redirect:/produtos";
    }
}
