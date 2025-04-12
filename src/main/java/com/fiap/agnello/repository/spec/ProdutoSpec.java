package com.fiap.agnello.repository.spec;

import com.fiap.agnello.model.Produto;
import org.springframework.data.jpa.domain.Specification;

public class ProdutoSpec {

    public static Specification<Produto> nomeContem(String nome) {
        return (root, query, cb) ->
                nome == null || nome.isBlank() ? null : cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
    }

    public static Specification<Produto> classificacaoIgual(String valor) {
        return (root, query, cb) ->
                valor == null || valor.isBlank() ? null : cb.equal(root.get("classificacao"), valor);
    }

    public static Specification<Produto> paisIgual(String valor) {
        return (root, query, cb) ->
                valor == null || valor.isBlank() ? null : cb.equal(root.get("pais"), valor);
    }

    public static Specification<Produto> teorIgual(Double valor) {
        return (root, query, cb) ->
                valor == null ? null : cb.equal(root.get("teorAlcoolico"), valor);
    }

    public static Specification<Produto> vinicolaIgual(String valor) {
        return (root, query, cb) ->
                valor == null || valor.isBlank() ? null : cb.equal(root.get("vinicola"), valor);
    }

    public static Specification<Produto> tipoIgual(String valor) {
        return (root, query, cb) ->
                valor == null || valor.isBlank() ? null : cb.equal(root.get("tipo"), valor);
    }
}

