<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<jsp:include page="layout/head.jsp" />

<jsp:include page="layout/header.jsp" />

<div class="bg-gray-100 min-h-screen flex items-center justify-center px-4">

    <div class="text-center max-w-xl">

        <h1 class="text-3xl md:text-4xl font-bold text-gray-900 mb-6">Obrigado pela sua compra!</h1>

        <p class="text-gray-700 text-base md:text-lg mb-2">
            O seu pedido foi aceito e está sendo processado.
        </p>
        <p class="text-gray-700 text-base md:text-lg mb-6">
            Você irá receber uma notificação com os detalhes do pedido no seu e-mail.
        </p>

        <p class="text-sm text-gray-500 mb-8">
            Você será redirecionado para seus pedidos em <span id="timer" class="font-semibold">8</span> segundos...
        </p>

        <div class="flex flex-col sm:flex-row gap-4 justify-center">
            <a href="${pageContext.request.contextPath}/"
               class="text-sm text-gray-700 border-b border-gray-500 hover:text-[#581c36] transition">
                Voltar para a página inicial
            </a>

            <a href="${pageContext.request.contextPath}/pedidos/meus"
               class="text-sm bg-[#581c36] text-white px-4 py-2 rounded-md hover:bg-[#45122a] transition">
                Ver meus pedidos
            </a>
        </div>
    </div>
</div>

<jsp:include page="layout/footer.jsp" />

<jsp:include page="layout/end.jsp" />

<script>
    let countdown = 8;
    const timerSpan = document.getElementById("timer");

    const interval = setInterval(() => {
        countdown--;
        timerSpan.textContent = countdown;
        if (countdown <= 0) {
            clearInterval(interval);
            window.location.href = "${pageContext.request.contextPath}/pedidos/meus";
        }
    }, 1000);
</script>


