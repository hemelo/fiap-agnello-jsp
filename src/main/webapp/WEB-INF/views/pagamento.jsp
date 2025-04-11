<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<jsp:include page="layout/head.jsp" />

<h2>Pagamento do Pedido #${pedidoId}</h2>

<form action="/pagamento/${pedidoId}" method="post">
    <label>Método de pagamento:</label>
    <select name="metodo">
        <option value="PIX">PIX</option>
        <option value="CARTAO">Cartão de Crédito</option>
        <option value="BOLETO">Boleto</option>
    </select>

    <label>Valor:</label>
    <input type="number" name="valor" step="0.01" required />

    <button type="submit">Pagar</button>
</form>
