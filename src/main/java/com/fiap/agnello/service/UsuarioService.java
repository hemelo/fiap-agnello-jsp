package com.fiap.agnello.service;

import com.fiap.agnello.model.Usuario;
import com.fiap.agnello.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public void editarUsuario(Long usuarioId, Usuario usuario) {
        Usuario usuarioExistente = buscarUsuarioPorId(usuarioId);
        usuarioExistente.setNome(usuario.getNome());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setSenha(usuario.getSenha());
        usuarioRepository.save(usuarioExistente);
    }

    public void editarNomeUsuario(Long usuarioId, String nome) {
        Usuario usuarioExistente = buscarUsuarioPorId(usuarioId);
        usuarioExistente.setNome(nome);
        usuarioRepository.save(usuarioExistente);
    }

    public Usuario buscarUsuarioPorId(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o ID: " + usuarioId));
    }
}
