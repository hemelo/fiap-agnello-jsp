package com.fiap.agnello.controller;

import com.fiap.agnello.model.Assinatura;
import com.fiap.agnello.model.AssinaturaPlano;
import com.fiap.agnello.security.UsuarioDetails;
import com.fiap.agnello.service.AssinaturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/assinatura")
@RequiredArgsConstructor
public class AssinaturaController {

    private final AssinaturaService assinaturaService;

    @GetMapping
    public ModelAndView assinaturaPage(@AuthenticationPrincipal UsuarioDetails userDetails) {

        Assinatura assinatura = null;

        try {
            if (userDetails != null)
                assinatura = assinaturaService.buscarAssinaturaAtivaPorUsuario(userDetails.getUsuario().getId());
        } catch (Exception e) {
            // Log the exception if needed
        }

        List<AssinaturaPlano> planos = assinaturaService.listarPlanosDeAssinatura();

        ModelAndView mv = new ModelAndView("assinatura");
        mv.addObject("pageTitle", "Assinatura");
        mv.addObject("planos", planos);
        mv.addObject("assinatura", assinatura);
        return mv;
    }

    @PostMapping
    public String criar(@RequestParam Long planoId, @AuthenticationPrincipal UsuarioDetails userDetails) {
        assinaturaService.criarAssinatura(userDetails.getUsuario().getId(), planoId);
        return "redirect:/assinatura";
    }

    @PostMapping("/cancelar")
    public String cancelar(@RequestParam Long id) {
        assinaturaService.cancelarAssinatura(id);
        return "redirect:/assinatura";
    }
}
