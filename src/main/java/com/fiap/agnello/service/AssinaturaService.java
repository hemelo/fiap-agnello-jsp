package com.fiap.agnello.service;

import com.fiap.agnello.model.Assinatura;
import com.fiap.agnello.model.AssinaturaPlano;
import com.fiap.agnello.model.Usuario;
import com.fiap.agnello.repository.AssinaturaPlanoRepository;
import com.fiap.agnello.repository.AssinaturaRepository;
import com.fiap.agnello.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssinaturaService {

    private final AssinaturaRepository assinaturaRepository;
    private final AssinaturaPlanoRepository assinaturaPlanoRepository;
    private final UsuarioRepository usuarioRepository;

    public Assinatura criarAssinatura(Long usuarioId, Long planoId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        AssinaturaPlano plano = assinaturaPlanoRepository.findById(planoId)
                .orElseThrow(() -> new EntityNotFoundException("Plano não encontrado"));

        Assinatura assinatura = new Assinatura();
        assinatura.setUsuario(usuario);
        assinatura.setPlano(plano);
        assinatura.setAtivo(true);

        assinatura = assinaturaRepository.save(assinatura);

        log.info("Assinatura criada com sucesso: {}", assinatura);

        return assinatura;
    }

    public Optional<Assinatura> buscarAtivaPorUsuario(Long usuarioId) {
        return assinaturaRepository.findByUsuarioIdAndAtivo(usuarioId, true);
    }

    public void cancelarAssinatura(Long id) {
        Assinatura assinatura = assinaturaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Assinatura não encontrada"));
        assinatura.setAtivo(false);
        assinaturaRepository.save(assinatura);

        log.info("Assinatura cancelada com sucesso: {}", assinatura);
    }

    public List<AssinaturaPlano> listarPlanos() {
        return assinaturaPlanoRepository.findAll();
    }

    public AssinaturaPlano buscarPlanoMaisBarato() {
        return assinaturaPlanoRepository.findFirstByAtivoTrueOrderByPrecoMensalAsc()
                .orElseThrow(() -> new EntityNotFoundException("Nenhum plano ativo encontrado"));
    }
}
