package com.fiap.agnello.controller;

import com.fiap.agnello.model.Pedido;
import com.fiap.agnello.model.Usuario;
import com.fiap.agnello.security.UsuarioDetails;
import com.fiap.agnello.service.PedidoService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoUsuarioController {

    private final PedidoService pedidoService;

    @GetMapping
    public ModelAndView listarPedidos(@AuthenticationPrincipal UsuarioDetails userDetails) {
        List<Pedido> pedidos = pedidoService.listarPorUsuario(userDetails.getUsuario().getId());

        ModelAndView mv = new ModelAndView("views/pedidos");
        mv.addObject("pageTitle", "Meus Pedidos");
        mv.addObject("pedidos", pedidos);
        return mv;
    }

    @GetMapping("/confirmacao/{id}")
    public ModelAndView confirmacao(@PathVariable Long id) {

        Pedido pedido = pedidoService.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        ModelAndView mv = new ModelAndView("views/confirmacao");
        mv.addObject("pedido", pedido);
        return mv;
    }
}
