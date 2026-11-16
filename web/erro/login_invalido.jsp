<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Le Cheff</title>
</head>
<body>

    <jsp:include page="/components/header.jsp" />

    <h1 id="lecheff__h1">Email ou senha inválidos</h1>

    <section class="lecheff__section">
        <p>Confira os dados informados e tente novamente.</p>
    </section>

    <section class="lecheff__section">
        <a href="index.jsp" class="lecheff__button">Voltar para o início</a>
    </section>

    <jsp:include page="/components/footer.jsp" />

</body>
</html>