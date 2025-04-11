<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<jsp:include page="layout/head.jsp" />

<div class="min-h-screen flex flex-col justify-between">
    <!-- NAVBAR -->
    <jsp:include page="layout/header.jsp" />

    <jsp:include page="layout/clube.jsp" />

    <div class="flex flex-col lg:flex-row gap-8 p-8">

        <!-- Lista de produtos -->
        <div class="flex-1">
            <h2 class="text-xl font-semibold mb-4">Carrinho de Compras</h2>

            <c:if test="${not empty sucesso}">
                <div class="mb-4 px-4 py-2 bg-green-100 text-green-800 rounded">
                    ${sucesso}
                </div>
            </c:if>

            <c:if test="${not empty erro}">
                <div class="mb-4 px-4 py-2 bg-red-100 text-red-800 rounded">
                    ${erro}
                </div>
            </c:if>

            <c:forEach var="item" items="${itens}">
                <div class="flex border-b pb-4 mb-6">
                    <img src="${item.produto.imagemUrl}" alt="${item.produto.nome}" class="w-28 h-52 object-cover"/>

                    <div class="flex-1 ml-6">
                        <h3 class="text-lg font-semibold">${item.produto.nome}</h3>
                        <p class="text-sm text-gray-700 font-medium mt-2">R$ ${item.produto.preco}</p>
                        <p class="text-sm text-gray-600 mt-3 leading-relaxed">
                            <b>Tipo:</b> ${item.produto.tipo} <br/>
                            <b>País:</b> ${item.produto.pais} <br/>
                            <b>Vinícola:</b> ${item.produto.vinicola} <br/>
                            <b>Teor Alcóolico:</b> ${item.produto.teorAlcoolico} <br/>
                        </p>

                        <div class="flex items-center mt-4 gap-3">
                            <form method="post" action="${pageContext.request.contextPath}/carrinho/alterar" class="flex items-center gap-2">
                                <input type="hidden" name="produtoId" value="${item.produto.id}" />
                                <input type="hidden" name="quantidade" value="${item.quantidade > 1 ? item.quantidade - 1 : 1}" />
                                <button type="submit" class="px-2 py-1 border rounded text-lg font-bold hover:bg-gray-200">−</button>
                            </form>

                            <span class="text-base font-medium">${item.quantidade}</span>

                            <form method="post" action="${pageContext.request.contextPath}/carrinho/alterar" class="flex items-center gap-2">
                                <input type="hidden" name="produtoId" value="${item.produto.id}" />
                                <input type="hidden" name="quantidade" value="${item.quantidade + 1}" />
                                <button type="submit" class="px-2 py-1 border rounded text-lg font-bold hover:bg-gray-200">+</button>
                            </form>
                        </div>

                    </div>

                    <form action="${pageContext.request.contextPath}/carrinho/remover" method="post" class="ml-auto">
                        <input type="hidden" name="produtoId" value="${item.produto.id}" />
                        <button type="submit" class="text-gray-500 hover:text-red-600 text-2xl">🗑️</button>
                    </form>
                </div>
            </c:forEach>
        </div>

        <!-- Resumo -->
        <div class="w-full lg:w-96 bg-gray-100 p-6 rounded">
            <h3 class="text-lg font-semibold mb-4">Resumo</h3>
            <div class="flex justify-between mb-2">
                <span>Subtotal</span>
                <span>R$ ${String.format("%.2f", subtotal)}</span>
            </div>
            <form method="get" action="${pageContext.request.contextPath}/carrinho" class="mb-2">
                <label class="block text-sm mb-1">Cupom de Desconto</label>
                <div class="flex items-center gap-2">
                    <input type="text" name="cupom" value="${cupom}" class="flex-1 border border-gray-300 rounded px-2 py-1" placeholder="Inserir Cupom"/>
                </div>
            </form>
            <c:if test="${not empty cupomAplicado}">
                <div class="flex justify-between mb-2 text-green-700 font-medium">
                    <span>Desconto (${cupomAplicado.percentual}%)</span>
                    <span>- R$ ${String.format("%.2f", desconto)}</span>
                </div>
            </c:if>
            <div class="flex justify-between mb-2">
                <span>Frete</span>
                <span class="text-sm text-gray-500">Calcular</span>
            </div>
            <div class="border-t pt-4 mt-4">
                <div class="flex justify-between font-semibold text-lg">
                    <span>Total</span>
                    <span>R$ ${String.format("%.2f", total)}</span>
                </div>
                <c:if test="${not empty total}">
                    <div class="mt-2">
                        <label for="parcelas" class="block text-sm mb-1">Parcelamento</label>
                        <select id="parcelas" name="parcelas" onchange="atualizarParcelamento()" class="w-full border rounded px-2 py-1">
                            <option value="0">Sem parcelamento</option>
                            <c:forEach var="i" begin="2" end="12">
                                <option value="${i}">${i}x sem juros</option>
                            </c:forEach>
                        </select>
                        <p id="parcelado" class="text-sm mt-1 text-center text-gray-600"></p>
                    </div>
                </c:if>
            </div>

            <form action="${pageContext.request.contextPath}/checkout" method="get" class="mt-4">
                <button type="submit" disabled="${not empty erro}" class="w-full bg-red-900 disabled:bg-gray-600 disabled:text-gray-400 hover:bg-red-800 text-white font-semibold py-2 rounded">
                    Finalizar Compra
                </button>
            </form>

            <p class="text-center mt-3 text-sm">
                <a href="${pageContext.request.contextPath}/produtos" class="text-gray-600 hover:underline">Continuar Comprando</a>
            </p>
        </div>
    </div>

    <jsp:include page="layout/footer.jsp" />
</div>

<script>
    function atualizarParcelamento() {
        const select = document.getElementById('parcelas');
        const total = parseFloat(${total}); // injetado diretamente
        const parcelas = parseInt(select.value);

        if (parcelas == 0) {
            document.getElementById('parcelado').textContent = '';
            return;
        }

        const valorParcela = total / parcelas;

        document.getElementById('parcelado').textContent = parcelas + "x de R$ " + valorParcela.toFixed(2) + " sem juros";
    }

    // Inicializar ao carregar
    document.addEventListener("DOMContentLoaded", () => {
        atualizarParcelamento();
    });
</script>

<jsp:include page="layout/end.jsp" />