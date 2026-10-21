package com.lecheff;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/chefs")
public class ChefServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {super.init();}

    // CADASTRAR
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        Chef chef = new Chef(email, senha);
        ChefDAO chefDAO = new ChefDAO();

        String mensagem;
        if(chefDAO.inserirChef(chef)){mensagem = "Chef Cadastrado!";}
        else{mensagem = "Erro ao cadastrar o chef.";}

        String html =
                "<html>\n" +
                        "<head>\n" +
                        "  <meta charset=\"UTF-8\">\n" +
                        "  <title>Cadastro de Chef</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "  <h1>" + mensagem + "</h1>\n" +
                        "  <a href=\"chef.html\">Voltar</a>\n" +
                        "</body>\n" +
                        "</html>";

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.print(html);
        out.close();

    }

    // CONSULTAR
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");

        ChefDAO chefDAO = new ChefDAO();
        Chef chef = chefDAO.buscarChef(email);

        String corpo;
        if (chef != null) {
            corpo = "  <h1>Chef encontrado</h1>\n" +
                    "  <p>Email: " + chef.getEmail() + "</p>\n" +
                    "  <p>Senha: " + chef.getSenha() + "</p>\n";
        } else {
            corpo = "  <h1>Chef não encontrado.</h1>\n";
        }

        String html =
                "<html>\n" +
                        "<head>\n" +
                        "  <meta charset=\"UTF-8\">\n" +
                        "  <title>Consulta de Chef</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        corpo +
                        "  <a href=\"chef.html\">Voltar</a>\n" +
                        "</body>\n" +
                        "</html>";

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.print(html);
        out.close();
    }

    // ALTERAR
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        Chef chef = new Chef(email, senha);
        ChefDAO chefDAO = new ChefDAO();

        String mensagem;
        if(chefDAO.atualizarChef(chef)){mensagem = "Chef Alterado!";}
        else{mensagem = "Chef não encontrado.";}

        String html =
                "<html>\n" +
                        "<head>\n" +
                        "  <meta charset=\"UTF-8\">\n" +
                        "  <title>Alteração de Chef</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "  <h1>" + mensagem + "</h1>\n" +
                        "  <a href=\"chef.html\">Voltar</a>\n" +
                        "</body>\n" +
                        "</html>";

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.print(html);
        out.close();
    }

    // REMOVER
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");

        ChefDAO chefDAO = new ChefDAO();

        String mensagem;
        if(chefDAO.removerChef(email)){mensagem = "Chef Removido!";}
        else{mensagem = "Chef não encontrado.";}

        String html =
                "<html>\n" +
                        "<head>\n" +
                        "  <meta charset=\"UTF-8\">\n" +
                        "  <title>Remoção de Chef</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "  <h1>" + mensagem + "</h1>\n" +
                        "  <a href=\"chef.html\">Voltar</a>\n" +
                        "</body>\n" +
                        "</html>";

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.print(html);
        out.close();
    }

    @Override
    public void destroy() {
        super.destroy();
    }

}
