<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.io.File" %>
<%@ page import="java.net.URLEncoder" %>

<%
    String contextoAtual = request.getParameter("contexto");
    if (contextoAtual == null || contextoAtual.isEmpty()) {
        contextoAtual = "cliente";
    }

    boolean isCheff = "cheff".equals(contextoAtual);

    String tituloSecao = isCheff
            ? "Nota de Confirmação e Cardápio do Cheff"
            : "Detalhamento e Referências do Pedido";

    String labelArquivo1 = isCheff
            ? "Nota de Confirmação do Pedido (PDF):"
            : "Detalhamento do Pedido / Lista de Desejos (PDF):";

    String labelArquivo2 = isCheff
            ? "Cardápio / Ficha Técnica (PDF/DOC):"
            : "Referência Visual / Inspiração do Evento (PDF/IMG):";
%>

<section class="lecheff__upload-section">
    <h2><%= tituloSecao %></h2>

    <% if (request.getAttribute("mensagemUpload") != null) { %>
        <p style="color: green; font-weight: bold;"><%= request.getAttribute("mensagemUpload") %></p>
    <% } %>

    <% if (request.getAttribute("erroUpload") != null) { %>
        <p style="color: red; font-weight: bold;"><%= request.getAttribute("erroUpload") %></p>
    <% } %>

    <fieldset class="lecheff__fieldset">
        <legend>Enviar 2 Arquivos (<%= contextoAtual.toUpperCase() %>)</legend>
        <form class="lecheff__form" method="post" action="<%= request.getContextPath() %>/arquivos" enctype="multipart/form-data">
            <input type="hidden" name="contexto" value="<%= contextoAtual %>" />

            <label for="arquivo1_<%= contextoAtual %>"><%= labelArquivo1 %></label>
            <input type="file" id="arquivo1_<%= contextoAtual %>" name="arquivo1" required />

            <label for="arquivo2_<%= contextoAtual %>"><%= labelArquivo2 %></label>
            <input type="file" id="arquivo2_<%= contextoAtual %>" name="arquivo2" required />

            <button type="submit">Enviar 2 Arquivos</button>
        </form>
    </fieldset>

    <div class="lecheff__lista-arquivos">
        <h3>Arquivos disponíveis para download (Contexto: <%= contextoAtual.toUpperCase() %>)</h3>
        <ul>
            <%
                String pathUploads = application.getRealPath("uploads" + File.separator + contextoAtual);
                File pasta = (pathUploads != null) ? new File(pathUploads) : null;
                File[] arquivos = (pasta != null && pasta.exists()) ? pasta.listFiles() : null;

                if (arquivos == null || arquivos.length == 0) {
            %>
                <li>Nenhum arquivo enviado neste contexto ainda.</li>
            <%
                } else {
                    for (File arq : arquivos) {
                        if (arq.isFile()) {
            %>
                <li>
                    <span><%= arq.getName() %></span> -
                    <a href="<%= request.getContextPath() %>/arquivos?contexto=<%= contextoAtual %>&arquivo=<%= URLEncoder.encode(arq.getName(), "UTF-8") %>">
                        Baixar PDF/Arquivo
                    </a>
                </li>
            <%
                        }
                    }
                }
            %>
        </ul>
    </div>
</section>