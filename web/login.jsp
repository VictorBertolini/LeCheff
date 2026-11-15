<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Le Cheff</title>
</head>
<body>

    <%@ include file="components/header.jsp" %>

    <h1 id="lecheff__h1">Le Cheff</h1>

    <section id="lecheff__form-section">
        <fieldset class="lecheff__fieldset">
            <legend>Login</legend>
            <form class="lecheff__form" action="login" method="post">

                <label for="email">Email: </label>
                <input type="email" name="email" id="email" required />

                <label for="senha">Senha: </label>
                <input type="password" name="senha" id="senha" required />

                <button type="submit">Entrar</button>
            </form>
        </fieldset>
    </section>
    

</body>
</html>