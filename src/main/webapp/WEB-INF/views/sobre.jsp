<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<jsp:include page="layout/head.jsp" />

<div class="min-h-screen flex flex-col justify-between">
    <!-- NAVBAR -->
    <jsp:include page="layout/header.jsp" />

    <section class="bg-gray-50 py-16 px-4 lg:px-24">
        <div class="max-w-6xl mx-auto flex flex-col lg:flex-row items-center gap-12">

            <!-- Texto -->
            <div class="flex-1 text-justify text-gray-800">
                <h2 class="text-2xl font-semibold text-center text-gray-900 mb-6">Conheça nossa história</h2>
                <p class="mb-4">
                    Desde 1993, a Vinheria Agnello tem sido mais do que uma loja de vinhos – é um ponto de encontro para apreciadores de boas bebidas e boas conversas. Fundada pelo Sr. Giulio Agnello, um apaixonado por vinhos e pela arte de compartilhar conhecimento, a vinheria nasceu com a missão de trazer rótulos selecionados e oferecer uma experiência única a cada cliente.
                </p>
                <p class="mb-4">
                    Com o passar dos anos, a tradição continuou, hoje na filha de Giulio, Bianca Agnello, também faz parte dessa história. Com uma visão moderna e ao mesmo tempo em sintonia com os princípios da família, Bianca trouxe a vinheria para o mundo digital. O e-commerce da Vinheria Agnello foi pensado para dar acesso às delícias do atendimento presencial àqueles que o cliente não pode vir à loja, ou mesmo mora longe, mas aprendeu onde se conecta seus origens e descobre a melhor forma de apreciá-lo.
                </p>
                <p class="mb-4">
                    Aqui, acreditamos que vinho é experiência, cultura e paixão. Por isso, nossa equipe é atentamente treinada para tirar todas as dúvidas, ajudando cada cliente a encontrar o rótulo ideal para sua ocasião. A loja física nos permite criar laços reais com nossos clientes e toda a seleção feita com critério, trazendo informações relevantes para que você desfrute cada garrafa da melhor maneira possível.
                </p>
                <p>
                    Seja em nossa loja física ou online, você sempre encontrará um atendimento próximo, especializado e pronto para tornar sua jornada pelo mundo dos vinhos ainda mais especial.
                </p>
            </div>

            <!-- Imagem -->
            <div class="flex-1">
                <div class="w-64 h-64 mx-auto rounded-full overflow-hidden border-4 border-red-900 shadow-lg">
                    <img src="${pageContext.request.contextPath}/img/fundadores.png" alt="Fundadores Vinheria Agnello" class="w-full h-full object-cover"/>
                </div>
            </div>
        </div>
    </section>

    <jsp:include page="layout/footer.jsp" />
</div>

<jsp:include page="layout/end.jsp" />