<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<jsp:include page="layout/head.jsp"/>

<div class="min-h-screen flex flex-col">
    <!-- NAVBAR -->
    <jsp:include page="layout/header.jsp"/>

    <section class="py-12 px-6 grow container mx-auto bg-gray-50 rounded-lg shadow my-24">
        <div class="grid grid-cols-1 md:grid-cols-2 gap-12 items-start">

            <!-- Imagem do produto -->
            <div>
                <img src="/img/${produto.imagemUrl}" alt="${produto.nome}" class="w-full h-auto max-h-[500px] object-contain rounded"/>
            </div>

            <!-- Detalhes -->
            <div class="space-y-4 text-gray-800">
                <h1 class="text-2xl font-semibold text-gray-900">${produto.nome}</h1>

                <ul class="text-sm space-y-1">
                    <li><strong>Tipo:</strong> ${produto.tipo}</li>
                    <li><strong>País e Região:</strong> ${produto.pais}</li>
                    <li><strong>Classificação:</strong> ${produto.classificacao}</li>
                    <li><strong>Vinícola:</strong> ${produto.vinicola}</li>
                    <li><strong>Safra:</strong> ${produto.safra}</li>
                    <li><strong>Teor Alcoólico:</strong> ${produto.teorAlcoolico}% ABV</li>
                </ul>

                <p class="text-xl font-bold text-red-900 pt-4">R$ ${produto.preco}</p>

                <form method="post" action="/produtos/adicionar" class="flex items-center gap-4 mt-4">
                    <input type="hidden" name="produtoId" value="${produto.id}" />

                    <div class="flex items-center border rounded overflow-hidden text-sm">
                        <button type="button" onclick="alterarQtd(-1)" class="px-3 py-1 border-r">−</button>
                        <input id="quantidade" type="number" name="quantidade" value="1" min="1"
                               class="w-12 text-center border-0"/>
                        <button type="button" onclick="alterarQtd(1)" class="px-3 py-1 border-l">+</button>
                    </div>

                    <button type="submit" class="bg-red-900 text-white px-5 py-2 rounded hover:bg-red-800 transition">
                        Adicionar à sacola
                    </button>
                </form>

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

            </div>
        </div>
    </section>

    <jsp:include page="layout/footer.jsp"/>
</div>

<script>
    function alterarQtd(dif) {
        const input = document.getElementById('quantidade');
        let valor = parseInt(input.value || '1');
        valor = Math.max(1, valor + dif);
        input.value = valor;
    }
</script>

<jsp:include page="layout/end.jsp"/>