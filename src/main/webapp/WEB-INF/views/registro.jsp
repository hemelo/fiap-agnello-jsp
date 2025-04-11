<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<jsp:include page="layout/head.jsp" />

<div class="bg-gray-50 min-h-screen flex flex-col justify-between">
    <!-- Header -->
    <header class="bg-gray-200  text-white py-2 px-6 flex items-center justify-between">
        <div class="flex items-center space-x-2">
            <jsp:include page="layout/logo.jsp" />
        </div>
    </header>

    <main class="flex-grow flex flex-col items-center py-12 px-4">
        <div class="max-w-2xl w-full bg-white p-10 rounded-md shadow">
            <h2 class="text-2xl font-semibold text-center text-[#581c36] mb-2">Cadastre-se na sua vinheria favorita!</h2>
            <p class="text-center text-sm text-gray-600 mb-6">O cadastro é gratuito. Aproveite sua nova conta para visualizar ofertas únicas e selecionadas!</p>

            <!-- Flash de erro -->
            <c:if test="${not empty erroCadastro}">
                <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded mb-4">
                        ${erroCadastro}
                </div>
            </c:if>

            <form method="post" action="${pageContext.request.contextPath}/registro" class="space-y-4">
                <div class="grid grid-cols-1 md:grid-cols-2 gap-4">

                    <!-- Nome -->
                    <div class="col-span-2">
                        <input name="nome" type="text" placeholder="Nome"
                               value="${registroDto.nome}"
                               class="w-full px-3 py-2 border rounded-md
                       <c:if test='${not empty errors["nome"]}'>border-red-500</c:if>
                       <c:if test='${empty errors["nome"]}'>border-gray-300</c:if>" />
                        <c:if test="${not empty errors['nome']}">
                            <p class="text-sm text-red-600 mt-1">${errors['nome']}</p>
                        </c:if>
                    </div>

                    <!-- Email -->
                    <div>
                        <input name="email" type="email" placeholder="Email"
                               value="${registroDto.email}"
                               class="w-full px-3 py-2 border rounded-md
                       <c:if test='${not empty errors["email"]}'>border-red-500</c:if>
                       <c:if test='${empty errors["email"]}'>border-gray-300</c:if>" />
                        <c:if test="${not empty errors['email']}">
                            <p class="text-sm text-red-600 mt-1">${errors['email']}</p>
                        </c:if>
                    </div>

                    <!-- Documento -->
                    <div>
                        <input name="documento" type="text" placeholder="CPF ou CNPJ"
                               value="${registroDto.documento}"
                               class="w-full px-3 py-2 border rounded-md
                       <c:if test='${not empty errors["documento"]}'>border-red-500</c:if>
                       <c:if test='${empty errors["documento"]}'>border-gray-300</c:if>" />
                        <c:if test="${not empty errors['documento']}">
                            <p class="text-sm text-red-600 mt-1">${errors['documento']}</p>
                        </c:if>
                    </div>

                    <!-- Senha -->
                    <div>
                        <input name="senha" type="password" placeholder="Senha"
                               class="w-full px-3 py-2 border rounded-md
                       <c:if test='${not empty errors["senha"]}'>border-red-500</c:if>
                       <c:if test='${empty errors["senha"]}'>border-gray-300</c:if>" />
                        <c:if test="${not empty errors['senha']}">
                            <p class="text-sm text-red-600 mt-1">${errors['senha']}</p>
                        </c:if>
                    </div>

                    <!-- Confirmar Senha -->
                    <div>
                        <input name="confirmarSenha" type="password" placeholder="Confirmar a senha"
                               class="w-full px-3 py-2 border rounded-md
                       <c:if test='${not empty errors["confirmarSenha"]}'>border-red-500</c:if>
                       <c:if test='${empty errors["confirmarSenha"]}'>border-gray-300</c:if>" />
                        <c:if test="${not empty errors['confirmarSenha']}">
                            <p class="text-sm text-red-600 mt-1">${errors['confirmarSenha']}</p>
                        </c:if>
                    </div>


                </div>

                <!-- Botões -->
                <div class="flex flex-col sm:flex-row items-center justify-between gap-4 mt-6">
                    <button type="submit" class="bg-[#581c36] text-white px-6 py-2 rounded-md hover:bg-[#4a162d] w-full sm:w-auto">
                        Concluir cadastro
                    </button>
                </div>
            </form>

            <!-- Link login -->
            <div class="text-center text-sm text-gray-600 mt-6">
                Já possui um cadastro? <a href="${pageContext.request.contextPath}/login" class="text-[#581c36] font-semibold">Clique aqui para entrar</a>
            </div>
        </div>
    </main>

    <!-- Footer -->
    <footer class="bg-[#581c36] text-white text-xs text-center py-4">
        <p>Vinheria Agnello est. 1990 &nbsp; | &nbsp; SE BEBER, NÃO DIRIJA. APRECIE COM MODERAÇÃO. A VENDA DE BEBIDAS ALCOÓLICAS É PROIBIDA PARA MENORES DE 18 ANOS.</p>
        <div class="mt-2 flex justify-center space-x-4">
            <span>FORMAS DE PAGAMENTO</span>
            <img src="${pageContext.request.contextPath}/img/visa.png" alt="Visa" class="h-4" />
            <img src="${pageContext.request.contextPath}/img/mastercard.png" alt="Mastercard" class="h-4" />
            <img src="${pageContext.request.contextPath}/img/elo.png" alt="Elo" class="h-4" />
            <img src="${pageContext.request.contextPath}/img/paypal.png" alt="Paypal" class="h-4" />
        </div>
    </footer>
</div>

<jsp:include page="layout/end.jsp" />
