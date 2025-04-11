package com.fiap.agnello.controller;

import com.fiap.agnello.model.Assinatura;
import com.fiap.agnello.model.Usuario;
import com.fiap.agnello.security.UsuarioDetails;
import com.fiap.agnello.service.AssinaturaService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/assinatura")
@RequiredArgsConstructor
public class AssinaturaController {

    private final AssinaturaService assinaturaService;

    @GetMapping
    public ModelAndView assinaturaPage(@AuthenticationPrincipal UsuarioDetails userDetails) {

        Optional<Assinatura> assinatura = assinaturaService.buscarAtivaPorUsuario(userDetails.getUsuario().getId());

        ModelAndView mv = new ModelAndView("views/assinatura");
        mv.addObject("pageTitle", "Assinatura");
        mv.addObject("assinatura", assinatura.orElse(null));
        return mv;
    }

    @PostMapping
    public String criar(@RequestParam String plano, @AuthenticationPrincipal UsuarioDetails userDetails) {


        assinaturaService.criarAssinatura(userDetails.getUsuario().getId(), plano);
        return "redirect:/assinatura";
    }

    @PostMapping("/cancelar")
    public String cancelar(@RequestParam Long id) {
        assinaturaService.cancelarAssinatura(id);
        return "redirect:/assinatura";
    }
}
