<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<div class="h-64 bg-cover bg-center flex items-center" style="background-image: url('${pageContext.request.contextPath}/img/banner-vinhos.png');">
    <div class="ml-16 text-white">
        <h2 class="text-2xl font-bold">
            ASSINE O MELHOR <span class="text-red-600">CLUBE DE VINHOS</span> DO BRASIL!
        </h2>
        <p class="text-xl">
            PLANOS <span class="text-red-600">A PARTIR DE R$

            <c:if test="${not empty planoMaisBarato}">${String.format("%.2f", planoMaisBarato.precoMensal)}</c:if>
            <c:if test="${empty planoMaisBarato}">70,00</c:if>

            POR MÊS</span> E BENEFÍCIOS EXCLUSIVOS.
        </p>
    </div>
</div>
