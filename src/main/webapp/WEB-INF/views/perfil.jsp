<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<jsp:include page="layout/head.jsp" />

<h2>Meu Perfil</h2>

<form action="/perfil/atualizar" method="post">
    <label>Nome:</label>
    <input type="text" name="nome" value="${usuario.nome}" required />

    <label>Email:</label>
    <input type="email" name="email" value="${usuario.email}" readonly />

    <label>Nova Senha:</label>
    <input type="password" name="senha" placeholder="Deixe em branco para manter a mesma" />

    <button type="submit">Atualizar Dados</button>
</form>

<hr/>

<h3>Endereços</h3>
<c:forEach var="e" items="${enderecos}">
    <form action="/perfil/endereco/atualizar" method="post" style="border:1px solid #ccc; padding:10px; margin-bottom:10px;">
        <input type="hidden" name="id" value="${e.id}" />
        <label>Logradouro:</label><input type="text" name="logradouro" value="${e.logradouro}" />
        <label>Número:</label><input type="text" name="numero" value="${e.numero}" />
        <label>Complemento:</label><input type="text" name="complemento" value="${e.complemento}" />
        <label>Bairro:</label><input type="text" name="bairro" value="${e.bairro}" />
        <label>Cidade:</label><input type="text" name="cidade" value="${e.cidade}" />
        <label>Estado:</label><input type="text" name="estado" value="${e.estado}" maxlength="2" />
        <label>CEP:</label><input type="text" name="cep" value="${e.cep}" />

        <button type="submit">Salvar</button>
    </form>
</c:forEach>

<h4>Novo Endereço</h4>

<form action="/perfil/endereco/novo" method="post">
    <label>Logradouro:</label><input type="text" name="logradouro" required />
    <label>Número:</label><input type="text" name="numero" />
    <label>Complemento:</label><input type="text" name="complemento" />
    <label>Bairro:</label><input type="text" name="bairro" required />
    <label>Cidade:</label><input type="text" name="cidade" required />
    <label>Estado:</label><input type="text" name="estado" maxlength="2" required />
    <label>CEP:</label><input type="text" name="cep" required />

    <button type="submit">Adicionar Endereço</button>
</form>
