package com.fiap.agnello.service;

import com.fiap.agnello.model.Endereco;
import com.fiap.agnello.model.Usuario;
import com.fiap.agnello.repository.EnderecoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final UsuarioService usuarioService;

    public Endereco cadastrarEndereco(Long usuarioId, Endereco endereco) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(usuarioId);
        endereco.setUsuario(usuario);
        return enderecoRepository.save(endereco);
    }

    public List<Endereco> listarPorUsuario(Long usuarioId) {
        return enderecoRepository.findByUsuarioId(usuarioId);
    }

    public Endereco buscarEnderecoPorId(Long id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado"));
    }
}
