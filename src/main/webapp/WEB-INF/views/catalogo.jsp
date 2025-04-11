<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<jsp:include page="layout/head.jsp" />

<h2>Catálogo de Vinhos</h2>

<c:forEach var="produto" items="${produtos}">
    <div>
        <h3>${produto.nome}</h3>
        <p>${produto.descricao}</p>
        <p><strong>R$ ${produto.preco}</strong></p>
        <a href="/produtos/${produto.id}">Ver detalhes</a>
    </div>
</c:forEach>
