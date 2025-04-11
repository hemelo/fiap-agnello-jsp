<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<jsp:include page="../layout/head.jsp" />

<!-- NAVBAR -->
<jsp:include page="../layout/header.jsp" />

<div class="bg-gray-100 flex items-center justify-center shadow-xl min-h-screen px-4 py-12">
    <div class="bg-white shadow-lg rounded-md max-w-md w-full p-8 text-center">
        <img src="${pageContext.request.contextPath}/img/error-illustration.png" alt="Erro" class="w-40 h-40 mx-auto mb-6" />

        <h1 class="text-3xl font-bold text-[#581c36] mb-2">Erro Interno do Servidor</h1>
        <p class="text-gray-600 mb-6">
            Ocorreu um problema inesperado em nossos servidores. Já estamos verificando isso!
        </p>

        <a href="${pageContext.request.contextPath}/"
           class="inline-block bg-[#581c36] text-white px-6 py-2 rounded-md hover:bg-[#45122a] transition">
            Voltar para a página inicial
        </a>
    </div>
</div>


<jsp:include page="../layout/footer.jsp" />

<jsp:include page="../layout/end.jsp" />