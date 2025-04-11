package com.fiap.agnello.repository;

import com.fiap.agnello.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Optional<Produto> findFirstByDestaqueTrue();

    @Query("SELECT DISTINCT p.tipo FROM Produto p")
    List<String> findTipos();

    @Query("SELECT DISTINCT p.pais FROM Produto p")
    List<String> findPaises();

    List<Produto> findTop8ByOrderByIdDesc(); // recomendados
}