<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>

<header class="bg-white shadow-sm">
    <div class="max-w-7xl mx-auto px-6 py-4 flex items-center justify-between">

        <!-- LOGO + NOME -->
        <a href="${pageContext.request.contextPath}/home" class="flex items-center gap-2">
            <img src="${pageContext.request.contextPath}/img/logo.svg" alt="Logo" class="h-10" />
            <span class="hidden md:inline text-lg font-semibold text-[#581c36]">Vinheria Agnello</span>
        </a>

        <!-- BOTÃO HAMBURGUER (MOBILE) -->
        <button id="menu-toggle" class="md:hidden text-[#581c36] focus:outline-none">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none"
                 viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                      d="M4 6h16M4 12h16M4 18h16" />
            </svg>
        </button>

        <!-- MENU (Desktop) -->
        <nav class="hidden md:flex gap-6 text-sm text-gray-700">
            <a href="${pageContext.request.contextPath}/produtos" class="hover:text-[#581c36]">Vinhos</a>
            <a href="#" class="hover:text-[#581c36]">Presentes</a>
            <a href="${pageContext.request.contextPath}/assinatura" class="hover:text-[#581c36]">Clube Agnello</a>
        </nav>

        <!-- AÇÕES (buscar + usuário + carrinho) -->
        <!-- AÇÕES (desktop) -->
        <div class="hidden md:flex items-center gap-4 relative">
            <!-- BUSCA -->
            <input type="text" placeholder="Buscar" class="border border-gray-300 rounded-md px-2 py-1 text-sm" />

            <!-- USUÁRIO LOGADO -->
            <c:if test="${not empty usuarioLogado}">
                <div class="relative group cursor-pointer">
                    <div class="flex items-center gap-2 text-sm text-[#581c36]">
                        <!-- Ícone usuário -->
                        <svg xmlns="http://www.w3.org/2000/svg"
                             fill="none" viewBox="0 0 24 24"
                             stroke-width="1.5" stroke="currentColor"
                             class="w-6 h-6">
                            <path stroke-linecap="round" stroke-linejoin="round"
                                  d="M15.75 6a3.75 3.75 0 11-7.5 0 3.75 3.75 0 017.5 0zM4.5 20.25a8.25 8.25 0 1115 0" />
                        </svg>

                        <span>${fn:split(usuarioLogado, ' ')[0]}</span>
                    </div>

                    <!-- DROPDOWN -->
                    <div class="absolute top-full right-0 w-36 bg-white shadow rounded border hidden group-hover:flex flex-col text-sm z-50">
                        <a href="${pageContext.request.contextPath}/perfil" class="px-4 py-2 hover:bg-gray-100">Meu perfil</a>
                        <a href="${pageContext.request.contextPath}/logout" class="px-4 py-2 hover:bg-gray-100">Sair</a>
                    </div>
                </div>
            </c:if>

            <!-- USUÁRIO NÃO LOGADO -->
            <c:if test="${empty usuarioLogado}">
                <a href="${pageContext.request.contextPath}/login"
                   class="text-sm bg-[#581c36] text-white px-3 py-1 rounded-md hover:bg-[#45122a]">
                    Entrar
                </a>
            </c:if>

            <!-- CARRINHO COM QUANTIDADE -->
            <a href="${pageContext.request.contextPath}/carrinho" class="relative text-[#581c36]">
                <!-- Ícone carrinho -->
                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" viewBox="0 0 24 24" fill="currentColor">
                    <path d="M7 18c-1.1 0-1.99.9-1.99 2S5.9 22 7 22s2-.9 2-2-.9-2-2-2zm10
               0c-1.1 0-1.99.9-1.99 2S15.9 22 17 22s2-.9 2-2-.9-2-2-2zM7.16
               14.26l.03.01H17c.83 0 1.54-.5 1.84-1.22l3.02-7.05a1.003
               1.003 0 00-.93-1.41H5.21L4.27 2H1v2h2l3.6
               7.59-1.35 2.44C4.52 14.37 5.48 16 7 16h12v-2H7.42c-.14
               0-.25-.11-.25-.25l.03-.09.76-1.4z"/>
                </svg>

                <!-- Quantidade -->
                <c:if test="${sessionScope.carrinho != null && sessionScope.carrinho.itens != null}">
                    <c:set var="qtd" value="${fn:length(sessionScope.carrinho.itens)}" />
                    <c:if test="${qtd > 0}">
        <span class="absolute -top-2 -right-2 bg-red-500 text-white text-xs font-bold rounded-full w-5 h-5 flex items-center justify-center">
                ${qtd}
        </span>
                    </c:if>
                </c:if>
            </a>
        </div>
    </div>

    <!-- MENU MOBILE EXPANDIDO -->
    <div id="mobile-menu" class="hidden flex-col gap-4 px-6 py-4 md:hidden bg-white border-t text-sm text-gray-700">
        <a href="#" class="hover:text-[#581c36]">Vinhos</a>
        <a href="#" class="hover:text-[#581c36]">Presentes</a>
        <a href="#" class="hover:text-[#581c36]">Clube Agnello</a>
        <input type="text" placeholder="Buscar" class="border border-gray-300 rounded-md px-2 py-1 text-sm mt-2" />
        <div class="flex gap-4 mt-2">
            <a href="#" class="text-[#581c36]"><i class="fas fa-user"></i></a>
            <a href="#" class="text-[#581c36]"><i class="fas fa-shopping-cart"></i></a>
        </div>
    </div>
</header>

<script>
    const menuToggle = document.getElementById('menu-toggle');
    const mobileMenu = document.getElementById('mobile-menu');

    // Alternar visibilidade do menu
    menuToggle.addEventListener('click', () => {
        mobileMenu.classList.toggle('hidden');
        mobileMenu.classList.toggle('flex');
    });

    // Fechar menu ao clicar em qualquer item de link
    const menuLinks = mobileMenu.querySelectorAll('a');
    menuLinks.forEach(link => {
        link.addEventListener('click', () => {
            mobileMenu.classList.add('hidden');
            mobileMenu.classList.remove('flex');
        });
    });
</script>