<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% String resultado = (String) request.getAttribute("resultado"); %>

<% if ("login_cliente".equals(resultado)) { %>
    <jsp:forward page="area_cliente.jsp" />

<% } else if ("login_cheff".equals(resultado)) { %>
    <jsp:forward page="area_cheff.jsp" />

<% } else if ("email_cadastrado".equals(resultado)) { %>
    <jsp:forward page="erro/email_cadastrado.jsp" />

<% } else if ("login_invalido".equals(resultado)) { %>
    <jsp:forward page="erro/login_invalido.jsp" />

<% } else { %>
    <jsp:forward page="erro/erro_cadastro.jsp" />
<% } %>