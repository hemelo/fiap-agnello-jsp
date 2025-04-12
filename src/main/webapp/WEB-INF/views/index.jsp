<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<jsp:include page="layout/head.jsp" />

<div class="min-h-screen flex flex-col justify-between">
    <!-- NAVBAR -->
    <jsp:include page="layout/header.jsp" />

    <div class="grow">
        <!-- BANNER PRINCIPAL -->
        <section class="bg-[#f3e7de] py-16 px-6">
            <div class="max-w-7xl mx-auto flex flex-col md:flex-row items-center justify-between gap-10">
                <div class="md:w-1/2 text-center md:text-left">
                    <h2 class="text-3xl font-semibold text-[#581c36] mb-4">A melhor seleção de vinhos do Brasil</h2>
                    <p class="text-gray-600 mb-6">A Vinheria Agnello funciona desde 1990. Aproveite nossa curadoria de vinhos selecionados e surpreenda seu paladar.</p>
                    <a href="${pageContext.request.contextPath}/produtos" class="bg-[#581c36] text-white px-6 py-2 rounded-md text-sm hover:bg-[#45122a]">Veja nossos vinhos</a>
                </div>
                <div class="md:w-1/2 text-center">
                    <img src="${pageContext.request.contextPath}/img/banner-vinhos.png" alt="Vinhos" class="max-h-72 mx-auto" />
                </div>
            </div>
        </section>

        <!-- VINHOS POR TIPO -->
        <c:if test="${not empty categorias}">
            <section class="py-12 text-center bg-white">
                <h3 class="text-xl font-semibold mb-8">Vinhos por tipo</h3>
                <div class="flex flex-wrap justify-center gap-10">
                    <c:forEach var="cat" items="${categorias}">
                        <div class="flex flex-col items-center text-sm">
                            <a href="${pageContext.request.contextPath}/produtos?tipo=${cat.nome}">
                                <img src="${cat.imagemUrl}" alt="${cat.nome}" class="h-16 w-16 rounded-full shadow border" />
                            </a>

                            <a href="${pageContext.request.contextPath}/produtos?tipo=${cat.nome}" class="mt-2">
                               ${cat.nome}
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </section>
        </c:if>

        <c:if test="${not empty destaque}">
            <!-- PRODUTO EM DESTAQUE -->
            <section class="py-16 bg-[#fafafa]">
                <div class="max-w-6xl mx-auto flex flex-col md:flex-row gap-10 items-center px-6">
                    <div class="md:w-1/2">
                        <h4 class="text-[#581c36] text-lg font-bold mb-2">Sugestão do dia pelo Sommelier</h4>
                        <h2 class="text-2xl font-semibold mb-2">${destaque.nome}</h2>
                        <p class="text-gray-600 mb-4">${destaque.descricao}</p>
                        <div class="text-sm text-gray-700 space-y-1">
                            <p><strong>Tipo:</strong> ${destaque.tipo}</p>
                            <p><strong>País & Região:</strong> ${destaque.pais}</p>
                            <p><strong>Classificação:</strong> ${destaque.classificacao}</p>
                            <p><strong>Vinícola:</strong> ${destaque.vinicola}</p>
                            <p><strong>Safra:</strong> ${destaque.safra}</p>
                            <p><strong>Teor alcoólico:</strong> ${destaque.teorAlcoolico}</p>
                        </div>
                        <div class="mt-6 flex items-center justify-between">
                            <p class="text-2xl font-bold">R$${destaque.preco}</p>
                            <a href="${pageContext.request.contextPath}/produtos/${destaque.id}" class="bg-[#581c36] text-white px-6 py-2 rounded-md text-sm hover:bg-[#45122a]">Comprar agora</a>
                        </div>
                    </div>
                    <div class="md:w-1/2 text-center">
                        <img src="${destaque.imagemUrl}" alt="${destaque.nome}" class="max-h-72 mx-auto" />
                        <p class="mt-2 text-sm">Sommelier Rafael Salamandriel</p>
                    </div>
                </div>
            </section>
        </c:if>

        <c:if test="${not empty paises}">
            <!-- VINHOS POR PAÍS -->
            <section class="py-12 text-center bg-white">
                <h3 class="text-xl font-semibold mb-6">Vinhos por país</h3>
                <div class="flex flex-wrap justify-center gap-8">
                    <c:forEach var="pais" items="${paises}">
                        <div class="flex flex-col items-center text-sm">
                            <a href="${pageContext.request.contextPath}/produtos?pais=${pais.nome}">
                                <img src="${pageContext.request.contextPath}/img${pais.bandeiraUrl}" alt="${pais.nome}" class="w-14 h-14 rounded-full border" />
                            </a>
                            <a class="mt-2" href="${pageContext.request.contextPath}/produtos?pais=${pais.nome}">
                                ${pais.nome}
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </section>
        </c:if>

        <c:if test="${not empty recomendados}">
            <!-- RECOMENDADOS -->
            <section class="py-16 bg-[#f5f5f5]">
                <h3 class="text-xl font-semibold text-center mb-10">Recomendados especialmente para você</h3>
                <div class="max-w-6xl mx-auto grid grid-cols-1 sm:grid-cols-2 md:grid-cols-4 gap-8 px-6">
                    <c:forEach var="p" items="${recomendados}">
                        <div class="bg-white rounded shadow-sm p-4 flex flex-col items-center text-center">
                            <img src="${p.imagemUrl}" alt="${p.nome}" class="h-48 object-contain mb-4" />
                            <h4 class="text-sm font-semibold mb-1">${p.nome}</h4>
                            <p class="text-sm text-gray-500 mb-1">${p.pais} • ${p.tipo}</p>
                            <p class="text-lg font-bold mb-2">R$${p.preco}</p>
                            <a href="${pageContext.request.contextPath}/produtos/${p.id}" class="w-full text-sm bg-red-900 text-white py-2 px-4 rounded hover:bg-red-800 transition">Comprar agora</a>
                        </div>
                    </c:forEach>
                </div>
            </section>
        </c:if>
    </div>

    <jsp:include page="layout/footer.jsp" />
</div>

<jsp:include page="layout/end.jsp" />