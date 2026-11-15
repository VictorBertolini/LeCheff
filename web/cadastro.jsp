<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="css/style.css">
    <title>Le Cheff</title>
</head>
<body>
    <%@ include file="components/header.jsp" %>

    <h1 id="lecheff__h1">Le Cheff</h1>

    <section id="lecheff__form-section">
        <fieldset id="lecheff__fieldset">
            <legend>Cadastro do Cliente</legend>
            <form class="lecheff__form lecheff__cadastro-cliente" action="clientes" method="post">
                <label for="nome">Nome:</label>
                <input type="text" name="nome" id="nome" placeholder="Informe o nome" required autofocus>

                <label for="idade">Idade:</label>
                <input type="number" name="idade" id="idade" placeholder="Informe a idade" min="0">

                <label for="email">Email:</label>
                <input type="email" name="email" id="email" placeholder="Informe o email" required>

                <label for="senha">Senha:</label>
                <input type="password" name="senha" id="senha" placeholder="Informe a senha" required>

                <label for="telefone">Telefone:</label>
                <input type="tel" name="telefone" id="telefone" placeholder="Informe o telefone" required>

                <button type="submit">Cadastrar</button>
            </form>
        </fieldset>
    </section>
</body>
</html>