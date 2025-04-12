package com.fiap.agnello.repository;

import com.fiap.agnello.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long>, JpaSpecificationExecutor<Produto> {
    Optional<Produto> findFirstByDestaqueTrue();

    @Query("SELECT DISTINCT p.tipo FROM Produto p")
    List<String> findTipos();

    @Query("SELECT DISTINCT p.pais FROM Produto p")
    List<String> findPaises();

    List<Produto> findTop8ByOrderByIdDesc(); // recomendados

    @Query("SELECT DISTINCT p.classificacao FROM Produto p WHERE p.classificacao IS NOT NULL")
    List<String> findDistinctClassificacoes();

    @Query("SELECT DISTINCT p.teorAlcoolico FROM Produto p WHERE p.teorAlcoolico IS NOT NULL")
    List<String> findDistinctTeores();

    @Query("SELECT DISTINCT p.pais FROM Produto p WHERE p.pais IS NOT NULL")
    List<String> findDistinctPaises();

    @Query("SELECT DISTINCT p.vinicola FROM Produto p WHERE p.vinicola IS NOT NULL")
    List<String> findDistinctVinicolas();

    @Query("SELECT DISTINCT p.tipo FROM Produto p WHERE p.tipo IS NOT NULL")
    List<String> findDistinctTipos();

    Page<Produto> findAll(Specification<Produto> and, Pageable pageable);


}