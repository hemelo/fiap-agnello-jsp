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
        endereco.setId(null); // Garantir que o ID seja nulo para criar um novo registro
        endereco.setUsuario(usuario);
        return enderecoRepository.save(endereco);
    }

    public List<Endereco> listarPorUsuario(Long usuarioId) {
        return enderecoRepository.findByUsuarioId(usuarioId);
    }

    public Endereco buscarPorId(Long id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado"));
    }

    public Endereco atualizar(Long id, Endereco novoEndereco) {
        Endereco existente = buscarPorId(id);

        existente.setLogradouro(novoEndereco.getLogradouro());
        existente.setNumero(novoEndereco.getNumero());
        existente.setComplemento(novoEndereco.getComplemento());
        existente.setBairro(novoEndereco.getBairro());
        existente.setCidade(novoEndereco.getCidade());
        existente.setEstado(novoEndereco.getEstado());
        existente.setCep(novoEndereco.getCep());

        return enderecoRepository.save(existente);
    }
}
