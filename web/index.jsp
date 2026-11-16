<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Le Cheff</title>
</head>
<body>

    <jsp:include page="/components/header.jsp" />

    <h1 id="lecheff__h1">Le Cheff</h1>

    <section class="lecheff__section">
        <a href="login.jsp" class="lecheff__button">Login</a>
        <a href="cadastro.jsp" class="lecheff__button">Cadastrar</a>
    </section>

    <jsp:include page="/components/footer.jsp" />

</body>
</html>