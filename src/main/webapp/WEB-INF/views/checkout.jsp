<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<jsp:include page="layout/head.jsp" />

<div class="min-h-screen flex flex-col justify-between">
    <jsp:include page="layout/header.jsp" />

    <section class="py-12 mx-auto">
        <h2 class="text-2xl font-semibold mb-6 text-gray-800">Finalizar Pedido</h2>

        <form action="${pageContext.request.contextPath}/pedidos/checkout" method="post" class="grid grid-cols-1 lg:grid-cols-3 gap-8">
            <!-- Resumo do pedido -->
            <div class="lg:col-span-2 bg-white p-6 rounded shadow">
                <h3 class="text-lg font-semibold mb-4 text-gray-700">Seu Pedido</h3>
                <c:forEach var="item" items="${itens}">
                    <div class="flex justify-between items-center border-b py-2">
                        <div>
                            <p class="font-medium">${item.produto.nome}</p>
                            <p class="text-sm text-gray-500">Qtd: ${item.quantidade}</p>
                        </div>
                        <p class="text-red-900 font-semibold">R$ ${item.produto.preco * item.quantidade}</p>
                    </div>
                </c:forEach>
                <div class="mt-4 flex flex-col gap-2 text-right items-end">
                    <c:if test="${not empty subtotal}">
                        <p class="text-sm font-semibold text-gray-600">Subtotal: R$ ${String.format("%.2f", subtotal)}</p>
                    </c:if>

                    <c:if test="${not empty desconto}">
                        <p class="text-xs font-semibold text-gray-600">Descontos: R$ ${String.format("%.2f", desconto)}</p>
                    </c:if>

                    <p class="text-lg font-semibold text-gray-800">Total: R$ ${String.format("%.2f", total)}</p>
                </div>

                <c:if test="${not empty sucesso}">
                    <div class="mt-4 px-4 py-2 bg-green-100 text-green-800 rounded">
                        ${sucesso}
                    </div>
                </c:if>

                <c:if test="${not empty erro}">
                    <div class="mt-4 px-4 py-2 bg-red-100 text-red-800 rounded">
                        ${erro}
                    </div>
                </c:if>

            </div>

            <!-- Endereço + Pagamento -->
            <div class="bg-white p-6 rounded shadow space-y-6">
                <div>
                    <h3 class="text-lg font-semibold mb-2 text-gray-700">Endereço de Entrega</h3>
                    <select name="enderecoId" class="w-full border px-3 py-2 rounded">
                        <c:forEach var="e" items="${enderecos}">
                            <option value="${e.id}">
                                    ${e.logradouro}, ${e.numero} - ${e.cidade}/${e.estado}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div>
                    <h3 class="text-lg font-semibold mb-2 text-gray-700">Método de Pagamento</h3>
                    <select name="metodo" class="w-full border px-3 py-2 rounded">
                        <option value="PIX">PIX</option>
                        <option value="CARTAO">Cartão de Crédito</option>
                        <option value="BOLETO">Boleto Bancário</option>
                    </select>
                </div>

                <button type="submit" class="w-full bg-red-900 text-white py-2 rounded hover:bg-red-800 transition">
                    Confirmar Pedido
                </button>
            </div>
        </form>
    </section>

    <jsp:include page="layout/footer.jsp" />
</div>

<jsp:include page="layout/end.jsp" />