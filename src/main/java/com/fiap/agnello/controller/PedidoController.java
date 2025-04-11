package com.fiap.agnello.controller;

import com.fiap.agnello.dto.CarrinhoDto;
import com.fiap.agnello.model.Pedido;
import com.fiap.agnello.model.Usuario;
import com.fiap.agnello.security.UsuarioDetails;
import com.fiap.agnello.service.EnderecoService;
import com.fiap.agnello.service.PedidoService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/checkout")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;
    private final EnderecoService enderecoService;

    @GetMapping
    public ModelAndView checkoutPage(@AuthenticationPrincipal UsuarioDetails userDetails) {

        ModelAndView mv = new ModelAndView("views/checkout");
        mv.addObject("pageTitle", "Checkout");
        mv.addObject("enderecos", enderecoService.listarPorUsuario(userDetails.getUsuario().getId()));
        return mv;
    }

    @PostMapping
    public String finalizarPedido(
            @RequestParam Long enderecoId,
            @RequestParam(required = false) String cupom,
            @ModelAttribute CarrinhoDto carrinho,
            @AuthenticationPrincipal UsuarioDetails userDetails,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {
        Pedido pedido = pedidoService.criarPedido(
                userDetails.getUsuario().getId(),
                enderecoId,
                carrinho.getItens(),
                cupom
        );

        session.removeAttribute("carrinho");
        redirectAttributes.addFlashAttribute("pedidoId", pedido.getId());

        return "redirect:/pagamento/" + pedido.getId();
    }
}
