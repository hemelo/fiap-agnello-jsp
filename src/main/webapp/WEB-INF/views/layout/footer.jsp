<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<footer class="bg-[#581c36] text-white py-10">
    <div class="max-w-6xl mx-auto grid grid-cols-2 md:grid-cols-4 gap-6 text-sm px-6">
        <div>
            <h5 class="font-semibold mb-2">Vinhos</h5>
            <ul>
                <li><a href="#">Tintos</a></li>
                <li><a href="#">Brancos</a></li>
                <li><a href="#">Rosé</a></li>
                <li><a href="#">Espumantes</a></li>
            </ul>
        </div>
        <div>
            <h5 class="font-semibold mb-2">Presentes</h5>
            <ul>
                <li><a href="#">Kits</a></li>
                <li><a href="#">Acessórios</a></li>
                <li><a href="#">Embalagens</a></li>
            </ul>
        </div>
        <div>
            <h5 class="font-semibold mb-2">Clube Agnello</h5>
            <ul>
                <li><a href="#">Como funciona</a></li>
                <li><a href="${pageContext.request.contextPath}/assinatura">Seja um membro</a></li>
            </ul>
        </div>
        <div>
            <h5 class="font-semibold mb-2">Sobre nós</h5>
            <ul>
                <li><a href="${pageContext.request.contextPath}/sobre">Nossa História</a></li>
                <li><a href="#">Instagram</a></li>
                <li><a href="#">YouTube</a></li>
                <li><a href="#">TikTok</a></li>
            </ul>
        </div>
    </div>
    <div class="text-center text-xs mt-6 px-6">
        Vinheria Agnello est. 1990 • SE BEBER, NÃO DIRIJA. A VENDA DE BEBIDAS ALCOÓLICAS É PROIBIDA PARA MENORES DE 18 ANOS.
    </div>
</footer>