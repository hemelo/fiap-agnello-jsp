<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<jsp:include page="layout/head.jsp" />

<h2>Meus Pedidos</h2>

<c:forEach var="pedido" items="${pedidos}">
    <div>
        <p><strong>ID:</strong> ${pedido.id}</p>
        <p><strong>Status:</strong> ${pedido.status}</p>
        <p><strong>Total:</strong> R$ ${pedido.valorTotal}</p>
        <a href="/pedido/confirmacao/${pedido.id}">Ver detalhes</a>
    </div>
</c:forEach>
