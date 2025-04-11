<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<jsp:include page="layout/head.jsp" />

<h2>Clube de Assinatura</h2>
<c:choose>
    <c:when test="${not empty assinatura}">
        <p>Plano atual: ${assinatura.plano}</p>
        <form action="/assinatura/cancelar" method="post">
            <input type="hidden" name="id" value="${assinatura.id}" />
            <button type="submit">Cancelar Assinatura</button>
        </form>
    </c:when>
    <c:otherwise>
        <form action="/assinatura" method="post">
            <label>Escolha seu plano:</label>
            <select name="plano">
                <option value="BÁSICO">Básico</option>
                <option value="PREMIUM">Premium</option>
            </select>
            <button type="submit">Assinar</button>
        </form>
    </c:otherwise>
</c:choose>
