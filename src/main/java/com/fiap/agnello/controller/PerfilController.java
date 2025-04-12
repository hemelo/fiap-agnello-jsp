package com.fiap.agnello.controller;

import com.fiap.agnello.model.Assinatura;
import com.fiap.agnello.model.Endereco;
import com.fiap.agnello.security.UsuarioDetails;
import com.fiap.agnello.service.AssinaturaService;
import com.fiap.agnello.service.EnderecoService;
import com.fiap.agnello.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/perfil")
@Slf4j
@RequiredArgsConstructor
public class PerfilController {
    private final EnderecoService enderecoService;
    private final UsuarioService usuarioService;
    private final AssinaturaService assinaturaService;

    @GetMapping
    public ModelAndView visualizarPerfil(@AuthenticationPrincipal UsuarioDetails userDetails) {

        List<Endereco> enderecos = enderecoService.listarPorUsuario(userDetails.getUsuario().getId());

        Assinatura assinatura = null;

        try {
            if (userDetails != null)
                assinatura = assinaturaService.buscarAssinaturaAtivaPorUsuario(userDetails.getUsuario().getId());
        } catch (Exception e) {
            // Log the exception if needed
        }

        ModelAndView mv = new ModelAndView("perfil");
        mv.addObject("pageTitle", "Perfil");
        mv.addObject("usuario", userDetails.getUsuario());
        mv.addObject("enderecos", enderecos);
        mv.addObject("assinatura", assinatura);
        return mv;
    }

    @PostMapping("/atualizar")
    public String atualizarNome(@RequestParam String nome, @AuthenticationPrincipal UsuarioDetails userDetails, RedirectAttributes redirectAttributes) {

        try {
            usuarioService.editarNomeUsuario(userDetails.getUsuario().getId(), nome);
            redirectAttributes.addFlashAttribute("sucesso", "Nome atualizado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao atualizar nome. Tente novamente.");
        }

        return "redirect:/perfil";
    }

    @PostMapping("/endereco/novo")
    public String adicionarEndereco(@ModelAttribute Endereco endereco, @AuthenticationPrincipal UsuarioDetails userDetails, RedirectAttributes redirectAttributes) {

        try {
            enderecoService.cadastrarEndereco(userDetails.getUsuario().getId(), endereco);
            redirectAttributes.addFlashAttribute("sucesso", "Endereço adicionado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao adicionar endereço. Tente novamente.");
        }

        return "redirect:/perfil";
    }

}
