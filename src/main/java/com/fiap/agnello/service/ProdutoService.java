package com.fiap.agnello.service;

import com.fiap.agnello.dto.CategoriaDto;
import com.fiap.agnello.dto.PaisDto;
import com.fiap.agnello.model.Produto;
import com.fiap.agnello.repository.ProdutoRepository;
import com.fiap.agnello.utils.PaisesUtils;
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

        List<String> tipos = produtoRepository.findTipos();

        if (tipos.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }

        return tipos.stream()
                .map(tipo -> new CategoriaDto(tipo, "/img/tipos/" + tipo.toLowerCase() + ".png"))
                .toList();
    }

    public List<PaisDto> getPaisesComProdutos() {

        List<String> paises = produtoRepository.findPaises();

        if (paises.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }

        List<PaisDto> paisesDto =  paises.stream()
                .map(pais -> new PaisDto(pais, null))
                .toList();

        for (PaisDto paisDto : paisesDto) {
            paisDto.setBandeiraUrl("/flags/" + PaisesUtils.getCountryCode(paisDto.getNome()) + ".svg");
        }

        return paisesDto;
    }

    public List<Produto> getProdutosRecomendados() {
        return produtoRepository.findTop8ByOrderByIdDesc();
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }
}
