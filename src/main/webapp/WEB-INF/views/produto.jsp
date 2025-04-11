<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<jsp:include page="layout/head.jsp" />

<h2>${produto.nome}</h2>

<img src="${produto.imagemUrl}" alt="${produto.nome}" />
<p>${produto.descricao}</p>
<p><strong>Preço: R$ ${produto.preco}</strong></p>

<form action="/carrinho/adicionar" method="post">
    <input type="hidden" name="produtoId" value="${produto.id}" />
    <label>Quantidade:</label>
    <input type="number" name="quantidade" value="1" min="1" />
    <button type="submit">Adicionar ao carrinho</button>
</form>