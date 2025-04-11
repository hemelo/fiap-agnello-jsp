<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<jsp:include page="layout/head.jsp" />

<div>
    <jsp:include page="layout/header.jsp" />

    <section class="bg-gray-50 py-16 px-4">
        <div class="max-w-6xl mx-auto text-center">
            <h2 class="text-2xl font-semibold text-gray-800 mb-6">Escolha o melhor plano para você</h2>


            <div class="flex flex-col md:grid md:grid-cols-2 lg:grid-cols-3 gap-6  md:w-full px-2 md:px-0">
                <c:forEach var="plano" items="${planos}">
                    <div class="relative p-6 bg-white rounded shadow hover:shadow-md transition duration-300 flex flex-col justify-between hover:scale-105
                        ${plano.destaque ? 'border-2 border-red-900 shadow-lg' : ''}">

                        <c:if test="${plano.destaque}">
                            <div class="absolute top-0 right-0 -mt-3 -mr-3 bg-red-900 text-white text-xs font-semibold px-3 py-1 rounded-full shadow">
                                Mais Popular
                            </div>
                        </c:if>

                        <h3 class="text-lg font-semibold text-gray-800 mb-2">${plano.nome}</h3>
                        <p class="text-sm text-gray-600 mb-4">${plano.descricaoCurta}</p>

                        <p class="text-left text-sm text-gray-700 whitespace-pre-line mb-4">${plano.beneficios}</p>

                        <c:if test="${plano.id != assinatura.plano.id}">
                            <p class="text-xl font-semibold text-gray-800 mb-3 mt-4">R$ ${plano.precoMensal} <span class="text-sm text-gray-500">/mês</span></p>

                            <form action="${pageContext.request.contextPath}/assinatura" method="post">
                                <input type="hidden" name="planoSlug" value="${plano.slug}" />
                                <button type="submit" class="bg-red-900 text-white py-2 px-4 rounded hover:bg-red-800 w-full">
                                    Assinar o plano
                                </button>
                            </form>
                        </c:if>
                    </div>
                </c:forEach>

            </div>
        </div>
    </section>

    <section class="bg-gray-50 py-16 px-4">
        <div class="max-w-6xl mx-auto text-center">
            <h2 class="text-xl font-semibold text-gray-800 mb-10">Entenda como funciona</h2>

            <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-4 gap-10 text-center">

                <!-- Etapa 1 -->
                <div class="flex flex-col items-center">
                    <svg class="w-10 h-10 text-red-900 mb-4" fill="none" stroke="currentColor" stroke-width="2"
                         viewBox="0 0 24 24">
                        <path stroke-linecap="round" stroke-linejoin="round" d="M5 13l4 4L19 7"/>
                    </svg>
                    <h3 class="text-sm font-semibold text-gray-800 mb-1">Escolha seu plano</h3>
                    <p class="text-sm text-gray-600">Escolha o plano ideal para você e seu estilo de vida.</p>
                </div>

                <!-- Etapa 2 -->
                <div class="flex flex-col items-center">
                    <svg class="w-10 h-10 text-red-900 mb-4" fill="none" stroke="currentColor" stroke-width="2"
                         viewBox="0 0 24 24">
                        <path stroke-linecap="round" stroke-linejoin="round" d="M16 5v2a4 4 0 11-8 0V5"/>
                        <path stroke-linecap="round" stroke-linejoin="round" d="M8 5h8l-1 12H9L8 5z"/>
                    </svg>
                    <h3 class="text-sm font-semibold text-gray-800 mb-1">Curadoria</h3>
                    <p class="text-sm text-gray-600">Nossos sommeliers analisam diversos vinhos e selecionam os melhores.</p>
                </div>

                <!-- Etapa 3 -->
                <div class="flex flex-col items-center">
                    <svg class="w-10 h-10 text-red-900 mb-4" fill="none" stroke="currentColor" stroke-width="2"
                         viewBox="0 0 24 24">
                        <path stroke-linecap="round" stroke-linejoin="round" d="M3 7l9 6 9-6-9-6-9 6z"/>
                        <path stroke-linecap="round" stroke-linejoin="round" d="M3 17l9 6 9-6"/>
                        <path stroke-linecap="round" stroke-linejoin="round" d="M3 12l9 6 9-6"/>
                    </svg>
                    <h3 class="text-sm font-semibold text-gray-800 mb-1">Receba em casa</h3>
                    <p class="text-sm text-gray-600">Todo mês, você recebe seus vinhos onde quiser, com conforto e segurança.</p>
                </div>

                <!-- Etapa 4 - Brinde! -->
                <div class="flex flex-col items-center">
                    <svg class="w-10 h-10 text-red-900 mb-4" viewBox="0 0 24 24" fill="none" stroke="currentColor"
                         stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round">
                        <path d="M15 3l3.5 8.5M9 3L5.5 11.5M5.5 11.5L11 13M3 21l3.5-9M14.5 11.5L9 13M21 21l-3.5-9M11 13l2 7m0 0h-4m4 0a1 1 0 002 0" />
                    </svg>
                    <h3 class="text-sm font-semibold text-gray-800 mb-1">Brinde!</h3>
                    <p class="text-sm text-gray-600">Viva experiências incríveis com a degustação de nossos vinhos.</p>
                </div>

            </div>
        </div>
    </section>


    <jsp:include page="layout/footer.jsp" />
</div>

<jsp:include page="layout/end.jsp" />

