package com.fiap.agnello.advice;

import com.fiap.agnello.security.UsuarioDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class UsuarioLogadoAdvice {

    @ModelAttribute
    public void adicionarUsuarioLogado(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
            Object principal = auth.getPrincipal();
            if (principal instanceof UsuarioDetails userDetails) {
                model.addAttribute("usuarioLogado", userDetails);
            } else {
                model.addAttribute("usuarioLogado", principal.toString());
            }
        }
    }
}