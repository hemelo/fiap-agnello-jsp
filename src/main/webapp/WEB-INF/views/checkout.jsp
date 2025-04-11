<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<jsp:include page="layout/head.jsp" />

<h2>Checkout</h2>

<form action="/checkout" method="post">
    <label>Endereço de entrega:</label>
    <select name="enderecoId">
        <c:forEach var="e" items="${enderecos}">
            <option value="${e.id}">
                    ${e.logradouro}, ${e.numero} - ${e.cidade}/${e.estado}
            </option>
        </c:forEach>
    </select>

    <label>Cupom de desconto:</label>
    <input type="text" name="cupom" />

    <button type="submit">Finalizar Pedido</button>
</form>
