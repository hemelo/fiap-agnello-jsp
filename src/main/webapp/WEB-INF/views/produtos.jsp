<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<jsp:include page="layout/head.jsp" />

<div class="min-h-screen flex flex-col justify-between">
    <!-- NAVBAR -->
    <jsp:include page="layout/header.jsp" />

    <div class="flex px-6 py-8 gap-8">

        <!-- Filtros laterais -->
        <form class="w-64 bg-white p-4 border rounded space-y-4" method="get" action="${pageContext.request.contextPath}/produtos">

            <input type="text" name="nome" placeholder="Buscar por nome"
                   value="${param.nome}" class="w-full border px-3 py-2 rounded"/>

            <select name="classificacao" class="w-full border px-3 py-2 rounded">
                <option value="">Classificação</option>
                <c:forEach var="c" items="${classificacoes}">
                    <option value="${c}" ${param.classificacao == c ? 'selected' : ''}>${c}</option>
                </c:forEach>
            </select>

            <select name="pais" class="w-full border px-3 py-2 rounded">
                <option value="">País</option>
                <c:forEach var="p" items="${paises}">
                    <option value="${p}" ${param.pais == p ? 'selected' : ''}>${p}</option>
                </c:forEach>
            </select>

            <select name="teor" class="w-full border px-3 py-2 rounded">
                <option value="">Teor Alcoólico</option>
                <c:forEach var="t" items="${teores}">
                    <option value="${t}" ${param.teor == t ? 'selected' : ''}>${t}</option>
                </c:forEach>
            </select>

            <select name="vinicola" class="w-full border px-3 py-2 rounded">
                <option value="">Vinicola</option>
                <c:forEach var="v" items="${vinicolas}">
                    <option value="${v}" ${param.vinicola == v ? 'selected' : ''}>${v}</option>
                </c:forEach>
            </select>

            <button type="submit" class="bg-red-900 text-white w-full py-2 rounded">Filtrar</button>
        </form>

        <div class="w-full">
            <c:if test="${not empty erro}">
                <div class="mb-4 px-4 py-2 bg-red-100 text-red-800 rounded">
                    ${erro}
                </div>
            </c:if>

            <c:if test="${not empty sucesso}">
                <div class="mb-4 px-4 py-2 bg-green-100 text-green-800 rounded">
                        ${sucesso}
                </div>
            </c:if>

            <!-- Catálogo de produtos -->
            <div class="flex-1 grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-6">
                <c:forEach var="produto" items="${produtos}">
                    <div class="border rounded p-4 bg-white shadow hover:shadow-lg hover:scale-105 transition">
                        <img src="${produto.imagemUrl}" class="w-full h-48 object-cover mb-4"/>
                        <h3 class="font-semibold text-lg">${produto.nome}</h3>
                        <p class="text-sm text-gray-600">${produto.classificacao} - ${produto.pais} - ${produto.teorAlcoolico}</p>
                        <p class="mt-2 font-bold text-red-900">R$ ${produto.preco}</p>

                        <form method="post" action="${pageContext.request.contextPath}/produtos/adicionar" class="mt-4">
                            <input type="hidden" name="produtoId" value="${produto.id}" />
                            <input type="hidden" name="quantidade" value="1" />
                            <button type="submit"
                                    class="w-full text-sm bg-red-900 text-white py-2 px-4 rounded hover:bg-red-800 transition">
                                Adicionar ao Carrinho
                            </button>
                        </form>
                    </div>
                </c:forEach>
            </div>

            <c:if test="${pagina.totalPages > 1}">
                <div class="mt-8 flex justify-center gap-2">
                    <c:forEach begin="0" end="${pagina.totalPages - 1}" var="i">
                        <a href="?page=${i}&size=${pagina.size}
                      <c:if test='${not empty nome}'> &nome=${nome}</c:if>
                      <c:if test='${not empty classificacao}'> &classificacao=${classificacao}</c:if>
                      <c:if test='${not empty pais}'> &pais=${pais}</c:if>
                      <c:if test='${not empty teor}'> &teor=${teor}</c:if>
                      <c:if test='${not empty vinicola}'> &vinicola=${vinicola}</c:if>"
                           class="px-3 py-1 border rounded
                      ${pagina.number == i ? 'bg-red-900 text-white' : 'bg-white text-gray-700 hover:bg-gray-100'}">
                                ${i + 1}
                        </a>
                    </c:forEach>
                </div>
            </c:if>

        </div>

    </div>

    <jsp:include page="layout/footer.jsp" />
</div>

<jsp:include page="layout/end.jsp" />