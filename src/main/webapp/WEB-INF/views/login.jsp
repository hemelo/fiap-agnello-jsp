<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:include page="layout/head.jsp" />

<div class="bg-gray-50 min-h-screen flex flex-col justify-between">
    <!-- Header -->
    <header class="bg-gray-200  text-white py-2 px-6 flex items-center">
        <jsp:include page="layout/logo.jsp" />
    </header>

    <main class="grow flex flex-col items-center py-12 px-4">
        <div class="max-w-md w-full bg-white p-10 rounded-md shadow">
            <h2 class="text-2xl font-semibold text-center text-[#581c36] mb-4">Acesse sua conta</h2>
            <p class="text-center text-sm text-gray-600 mb-6">Faça login para acompanhar pedidos e aproveitar ofertas exclusivas.</p>

            <!-- Flash de sucesso de Registro -->
            <c:if test="${not empty sucessoCadastro}">
                <div class="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded mb-4">
                    ${sucessoCadastro}
                </div>
            </c:if>

            <!-- Flash de erro -->
            <c:if test="${not empty erroLogin}">
                <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded mb-4">
                    ${erroLogin}
                </div>
            </c:if>

            <!-- Erro Spring Security -->
            <c:if test="${not empty param.error}">
                <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded mb-4">
                    Email ou senha inválidos. Tente novamente.
                </div>
            </c:if>

            <form method="post" action="${pageContext.request.contextPath}/login" class="space-y-4">

                <!-- Email -->
                <div>
                    <input name="username" type="email" placeholder="Email"
                           class="w-full px-3 py-2 border rounded-md
                     <c:if test='${not empty errors["username"]}'>border-red-500</c:if>
                     <c:if test='${empty errors["username"]}'>border-gray-300</c:if>" />
                    <c:if test="${not empty errors['username']}">
                        <p class="text-sm text-red-600 mt-1">${errors['username']}</p>
                    </c:if>
                </div>

                <!-- Senha -->
                <div>
                    <input name="password" type="password" placeholder="Senha"
                           class="w-full px-3 py-2 border rounded-md
                     <c:if test='${not empty errors["password"]}'>border-red-500</c:if>
                     <c:if test='${empty errors["password"]}'>border-gray-300</c:if>" />
                    <c:if test="${not empty errors['password']}">
                        <p class="text-sm text-red-600 mt-1">${errors['password']}</p>
                    </c:if>
                </div>

                <!-- Botões -->
                <div class="flex flex-col gap-4 mt-6">
                    <button type="submit" class="bg-[#581c36] text-white px-6 py-2 rounded-md hover:bg-[#4a162d]">
                        Entrar
                    </button>
                </div>
            </form>

            <div class="my-6 p-4 rounded bg-yellow-100 border border-yellow-300 text-yellow-900 text-sm max-w-md mx-auto shadow">
                <strong>Usuário de Teste</strong><br/>
                <span>Email:</span> <code class="font-mono bg-white px-1 py-0.5 rounded border">teste@vinheria.com</code><br/>
                <span>Senha:</span> <code class="font-mono bg-white px-1 py-0.5 rounded border">123456</code>
            </div>

            <!-- Link cadastro -->
            <div class="text-center text-sm text-gray-600 mt-6">
                Ainda não possui uma conta? <a href="${pageContext.request.contextPath}/registro" class="text-[#581c36] font-semibold">Cadastre-se aqui</a>
            </div>
        </div>
    </main>

    <!-- Footer -->
    <footer class="bg-[#581c36] text-white text-xs text-center py-4">
        <p>Vinheria Agnello est. 1990 &nbsp; | &nbsp; SE BEBER, NÃO DIRIJA. APRECIE COM MODERAÇÃO. A VENDA DE BEBIDAS ALCOÓLICAS É PROIBIDA PARA MENORES DE 18 ANOS.</p>
        <jsp:include page="layout/bandeiras.jsp" />
    </footer>
</div>

<jsp:include page="layout/end.jsp" />
