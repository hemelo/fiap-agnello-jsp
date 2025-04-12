package com.fiap.agnello.service;

import com.fiap.agnello.model.Assinatura;
import com.fiap.agnello.model.AssinaturaPlano;
import com.fiap.agnello.model.Usuario;
import com.fiap.agnello.repository.AssinaturaPlanoRepository;
import com.fiap.agnello.repository.AssinaturaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssinaturaService {

    private final AssinaturaRepository assinaturaRepository;
    private final AssinaturaPlanoRepository assinaturaPlanoRepository;
    private final UsuarioService usuarioService;

    public Assinatura criarAssinatura(Long usuarioId, Long planoId) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(usuarioId);

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

    public Assinatura buscarAssinaturaAtivaPorUsuario(Long usuarioId) {
        return assinaturaRepository.findByUsuarioIdAndAtivo(usuarioId, true).orElseThrow(
                () -> new EntityNotFoundException("Assinatura ativa não encontrada para o usuário com ID: " + usuarioId));
    }

    public void cancelarAssinatura(Long id) {
        Assinatura assinatura = assinaturaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Assinatura não encontrada"));
        assinatura.setAtivo(false);
        assinaturaRepository.save(assinatura);

        log.info("Assinatura cancelada com sucesso: {}", assinatura);
    }

    public List<AssinaturaPlano> listarPlanosDeAssinatura() {
        return assinaturaPlanoRepository.findAll();
    }

    public AssinaturaPlano buscarPlanoDeAssinaturaMaisBarato() {
        return assinaturaPlanoRepository.findFirstByAtivoTrueOrderByPrecoMensalAsc()
                .orElseThrow(() -> new EntityNotFoundException("Nenhum plano ativo encontrado"));
    }
}
