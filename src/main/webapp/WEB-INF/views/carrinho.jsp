<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<jsp:include page="layout/head.jsp" />

<h2>Meu Carrinho</h2>

<c:choose>
    <c:when test="${not empty itens}">
        <table>
            <tr>
                <th>Produto</th>
                <th>Preço</th>
                <th>Quantidade</th>
                <th>Total</th>
                <th>Ações</th>
            </tr>
            <c:forEach var="item" items="${itens}">
                <tr>
                    <td>${item.produto.nome}</td>
                    <td>R$ ${item.produto.preco}</td>
                    <td>${item.quantidade}</td>
                    <td>R$ ${item.produto.preco * item.quantidade}</td>
                    <td>
                        <form action="/carrinho/remover" method="post">
                            <input type="hidden" name="produtoId" value="${item.produto.id}" />
                            <button type="submit">Remover</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <form action="/checkout" method="get">
            <button type="submit">Ir para Checkout</button>
        </form>

        <form action="/carrinho/limpar" method="post">
            <button type="submit">Limpar Carrinho</button>
        </form>
    </c:when>
    <c:otherwise>
        <p>Seu carrinho está vazio.</p>
    </c:otherwise>
</c:choose>
