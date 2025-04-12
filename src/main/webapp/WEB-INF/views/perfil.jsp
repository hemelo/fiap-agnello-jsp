<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<jsp:include page="layout/head.jsp"/>

<div class="min-h-screen flex flex-col justify-between">
    <!-- NAVBAR -->
    <jsp:include page="layout/header.jsp"/>

    <div class="mx-auto container">

        <section class="py-12 px-4 space-y-12">

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

            <!-- Dados do Usuário -->
            <div class="bg-white p-6 rounded shadow">
                <h2 class="text-lg font-semibold mb-4">Seus Dados</h2>
                <form action="${pageContext.request.contextPath}/perfil/atualizar" method="post" class="space-y-4">
                    <div>
                        <label class="block text-sm text-gray-700">Nome</label>
                        <input type="text" name="nome" value="${usuario.nome}" class="w-full border rounded px-3 py-2"/>
                    </div>
                    <p class="text-sm text-gray-500">Email: <strong>${usuario.email}</strong> (não editável)</p>
                    <button type="submit" class="mt-3 bg-red-900 text-white px-4 py-2 rounded hover:bg-red-800">
                        Atualizar Nome
                    </button>
                </form>
            </div>

            <!-- Endereços -->
            <div class="bg-white p-6 rounded shadow">
                <h2 class="text-lg font-semibold mb-4">Seus Endereços</h2>
                <ul class="space-y-2">
                    <c:forEach var="e" items="${enderecos}">
                        <li class="border bg-gray-200 px-4 py-2 rounded text-sm">
                                ${e.logradouro}, ${e.numero} - ${e.cidade}/${e.estado} (${e.cep})
                        </li>
                    </c:forEach>
                </ul>

                <form action="${pageContext.request.contextPath}/perfil/endereco/novo" method="post"
                      class="mt-6 grid grid-cols-1 md:grid-cols-2 gap-4">
                    <input name="logradouro" placeholder="Logradouro" class="border px-3 py-2 rounded" required/>
                    <input name="numero" type="number" placeholder="Número" class="border px-3 py-2 rounded"/>
                    <input name="complemento" placeholder="Complemento" class="border px-3 py-2 rounded"/>
                    <input name="bairro" placeholder="Bairro" class="border px-3 py-2 rounded" required/>
                    <input name="cidade" placeholder="Cidade" class="border px-3 py-2 rounded" required/>
                    <input name="estado" placeholder="Estado" maxlength="2" class="border px-3 py-2 rounded" required/>
                    <input name="cep" type="number" placeholder="CEP" class="border px-3 py-2 rounded" required/>
                    <div class="md:col-span-2 text-right">
                        <button type="submit" class="bg-red-900 text-white px-4 py-2 rounded hover:bg-red-800">
                            Adicionar Endereço
                        </button>
                    </div>
                </form>
            </div>

            <!-- Assinatura -->
            <div class="bg-white p-6 rounded shadow">
                <h2 class="text-lg font-semibold mb-4">Assinatura</h2>
                <c:choose>
                    <c:when test="${not empty assinatura}">
                        <p class="text-sm mb-2">
                            <strong>Plano:</strong> ${assinatura.plano} <br/>
                            <strong>Início:</strong> ${assinatura.dataInicio}
                        </p>
                        <form action="${pageContext.request.contextPath}/assinatura/cancelar" method="post">
                            <input type="hidden" name="id" value="${assinatura.id}"/>
                            <button type="submit" class="bg-gray-700 text-white px-4 py-2 rounded hover:bg-gray-600">
                                Cancelar Assinatura
                            </button>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <p class="text-sm text-gray-600">Você ainda não possui uma assinatura ativa.</p>
                        <a href="${pageContext.request.contextPath}/assinatura"
                           class="text-red-900 font-medium underline text-sm">Assinar agora</a>
                    </c:otherwise>
                </c:choose>
            </div>

        </section>

    </div>
    <jsp:include page="layout/footer.jsp"/>
</div>

<jsp:include page="layout/end.jsp"/>