package com.fiap.agnello.controller;

import com.fiap.agnello.dto.RegistroDto;
import com.fiap.agnello.model.Usuario;
import com.fiap.agnello.repository.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registro")
    public String registroForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("pageTitle", "Registro");
        return "registro";
    }

    @PostMapping("/registro")
    public String registrar(@ModelAttribute @Valid RegistroDto registroDto, BindingResult result, RedirectAttributes redirect) throws InterruptedException {
        if (result.hasErrors()) {
            redirect.addFlashAttribute("org.springframework.validation.BindingResult.registroDto", result);
            redirect.addFlashAttribute("registroDto", registroDto);
            redirect.addFlashAttribute("erroCadastro", "Existem erros no formulário. Corrija e tente novamente.");
            return "redirect:/registro";
        }

        if (usuarioRepository.existsByEmail(registroDto.getEmail())) {
            redirect.addFlashAttribute("erroCadastro", "Email já cadastrado.");
            return "redirect:/registro";
        }

        Usuario usuario = new Usuario();
        usuario.setNome(registroDto.getNome());
        usuario.setEmail(registroDto.getEmail());
        usuario.setSenha(encoder.encode(registroDto.getSenha()));
        usuario.setRole("CLIENTE");
        usuarioRepository.save(usuario);

        redirect.addFlashAttribute("sucessoCadastro", "Cadastro realizado com sucesso! Por favor, faça login.");

        return "redirect:/login";
    }
}
