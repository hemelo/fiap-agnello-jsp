package com.fiap.agnello.controller;

import com.fiap.agnello.service.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pagamento")
@RequiredArgsConstructor
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @GetMapping("/{pedidoId}")
    public ModelAndView pagamentoPage(@PathVariable Long pedidoId) {
        ModelAndView mv = new ModelAndView("views/pagamento");
        mv.addObject("pageTitle", "Pagamento");
        mv.addObject("pedidoId", pedidoId);
        return mv;
    }

    @PostMapping("/{pedidoId}")
    public String pagar(
            @PathVariable Long pedidoId,
            @RequestParam String metodo,
            @RequestParam Double valor,
            RedirectAttributes redirectAttributes
    ) {
        pagamentoService.registrarPagamento(pedidoId, metodo, valor);
        redirectAttributes.addFlashAttribute("sucesso", "Pagamento realizado com sucesso!");
        return "redirect:/pedido/confirmacao/" + pedidoId;
    }
}
