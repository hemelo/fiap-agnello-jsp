package com.fiap.agnello.service;

import com.fiap.agnello.dto.CategoriaDto;
import com.fiap.agnello.dto.PaisDto;
import com.fiap.agnello.model.Produto;
import com.fiap.agnello.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto getProdutoDestaque() {
        return produtoRepository.findFirstByDestaqueTrue()
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public List<CategoriaDto> getCategoriasPorTipo() {
        return produtoRepository.findTipos().stream()
                .map(tipo -> new CategoriaDto(tipo, "/img/tipos/" + tipo.toLowerCase() + ".png"))
                .toList();
    }

    public List<PaisDto> getPaisesComProdutos() {
        return produtoRepository.findPaises().stream()
                .map(pais -> new PaisDto(pais, "/img/paises/" + pais.toLowerCase() + ".png"))
                .toList();
    }

    public List<Produto> getProdutosRecomendados() {
        return produtoRepository.findTop8ByOrderByIdDesc();
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }
}
