<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>

<jsp:include page="layout/head.jsp" />

<div class="min-h-screen flex flex-col">

    <jsp:include page="layout/header.jsp" />

    <section class="container mx-auto px-4 py-12 grow">
        <h2 class="text-2xl font-semibold mb-8 text-gray-800">Meus Pedidos</h2>

        <c:forEach var="pedido" items="${pedidos}">
            <div class="bg-white p-6 rounded shadow mb-6">
                <div class="flex justify-between items-center mb-4">
                    <div>
                        <p class="font-medium text-gray-600">Pedido #${pedido.id}</p>
                        <fmt:parseDate value="${ pedido.dataPedido }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />

                        <p class="text-sm text-gray-600">Data: <fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${ parsedDateTime }" /></p>
                    </div>
                    <span class="px-3 py-1 text-sm rounded bg-gray-200 text-gray-800">${pedido.status}</span>
                </div>

                <div class="space-y-3 mb-4">
                    <c:forEach var="item" items="${pedido.itens}">
                        <div class="flex justify-between items-center border-b pb-2">
                            <p class="text-sm text-gray-700">
                                    ${item.produto.nome} <br/>
                                <span class="text-xs text-gray-500">Qtd: ${item.quantidade}</span>
                            </p>
                            <p class="text-sm font-semibold text-red-900">
                                R$ ${item.precoUnitario * item.quantidade}
                            </p>
                        </div>
                    </c:forEach>
                </div>

                <c:if test="${not empty pedido.pagamento}">
                    <div class="text-sm text-gray-700 mb-1">
                        <strong>Pagamento:</strong> ${pedido.pagamento.metodo} - ${pedido.pagamento.status}
                    </div>
                </c:if>

                <c:if test="${pedido.valorTotal < (pedido.itens[0].precoUnitario * pedido.itens[0].quantidade * pedido.itens.size())}">
                    <div class="text-sm text-green-700 mb-1">
                        <strong>Desconto aplicado:</strong> R$ ${pedido.itens[0].precoUnitario * pedido.itens[0].quantidade * pedido.itens.size() - pedido.valorTotal}
                    </div>
                </c:if>

                <div class="text-right mt-4">
                    <p class="text-lg font-semibold text-gray-800">Total: R$ ${pedido.valorTotal}</p>
                </div>
            </div>
        </c:forEach>

        <c:if test="${empty pedidos}">
            <p class="text-center text-gray-600">Você ainda não realizou nenhum pedido.</p>
        </c:if>
    </section>

    <jsp:include page="layout/footer.jsp" />
</div>

<jsp:include page="layout/end.jsp" />