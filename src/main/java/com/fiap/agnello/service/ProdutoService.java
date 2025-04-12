package com.fiap.agnello.service;

import com.fiap.agnello.dto.CategoriaDto;
import com.fiap.agnello.dto.PaisDto;
import com.fiap.agnello.model.Produto;
import com.fiap.agnello.repository.ProdutoRepository;
import com.fiap.agnello.utils.PaisUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public Produto getProdutoDestaque() {
        return produtoRepository.findFirstByDestaqueTrue()
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public List<CategoriaDto> getCategoriasPorTipo() {

        List<String> tipos = produtoRepository.findTipos();

        if (tipos.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }

        return tipos.stream()
                .map(tipo -> new CategoriaDto(tipo, "/img/tipos/" + tipo.toLowerCase() + ".png"))
                .toList();
    }

    public List<PaisDto> buscarPaisesDtoComProduto() {

        List<String> paises = produtoRepository.findPaises();

        if (paises.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }

        List<PaisDto> paisesDto =  paises.stream()
                .map(pais -> new PaisDto(pais, null))
                .collect(Collectors.toList());

        String paisCode = null;

        for (PaisDto paisDto : paisesDto) {
            paisCode = PaisUtils.getCountryCode(paisDto.getNome());

            if (paisCode == null) {
                paisesDto.remove(paisDto);
            }

            paisDto.setBandeiraUrl("/flags/" + paisCode  + ".svg");
        }

        return paisesDto;
    }

    public List<Produto> getProdutosRecomendados() {
        return produtoRepository.findTop8ByOrderByIdDesc();
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
    }

    public List<String> buscarClassificacoes() {
        List<String> classificacoes = produtoRepository.findDistinctClassificacoes();

        if (classificacoes.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }

        return classificacoes;
    }

    public List<String> buscarTeores() {
        List<String> teores =  produtoRepository.findDistinctTeores();

        if (teores.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }

        return teores;
    }

    public List<String> buscarPaises() {
        List<String> paises =  produtoRepository.findDistinctPaises();

        if (paises.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }

        return paises;
    }

    public List<String> buscarVinicolas() {
        List<String> vinicolas =  produtoRepository.findDistinctVinicolas();

        if (vinicolas.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }

        return vinicolas;
    }

    public Object buscarTipos() {
        List<String> tipos =  produtoRepository.findDistinctTipos();

        if (tipos.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }

        return tipos;
    }

    public Page<Produto> filtrarProdutos(Specification<Produto> and, Pageable pageable) {
        if (and == null) {
            return produtoRepository.findAll(pageable);
        } else {
            return produtoRepository.findAll(and, pageable);
        }
    }


}
