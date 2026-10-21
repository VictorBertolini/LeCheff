package com.lecheff.servlets;

import com.lecheff.dao.PratoDAO;
import com.lecheff.models.Prato;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/pratos")
public class PratoServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {super.init();}

    // CADASTRAR
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nomePrato = req.getParameter("nomePrato");
        String descricaoPrato  = req.getParameter("descricaoPrato");
        String categoriaPrato  = req.getParameter("categoriaPrato");

        Prato prato = new Prato(nomePrato, descricaoPrato, categoriaPrato);
        PratoDAO pratoDAO = new PratoDAO();

        String mensagem;
        if(pratoDAO.inserirPrato(prato)){mensagem = "Prato Cadastrado!";}
        else{
            mensagem = "Erro ao cadastrar o prato.";
        }

        String html =
                "<html>\n" +
                        "<head>\n" +
                        "  <meta charset=\"UTF-8\">\n" +
                        "  <title>Cadastro de Prato</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "  <h1>" + mensagem + "</h1>\n" +
                        "  <a href=\"prato.html\">Voltar</a>\n" +
                        "</body>\n" +
                        "</html>";

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(html);
        out.close();

    }

    // CONSULTAR
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String corpo;
        try {
            int id = Integer.parseInt(req.getParameter("id"));

            PratoDAO pratoDAO = new PratoDAO();
            Prato prato = pratoDAO.buscarPrato(id);

            if (prato != null) {
                corpo = "  <h1>Prato encontrado</h1>\n" +
                        "  <p>Id: " + prato.getId() + "</p>\n" +
                        "  <p>Nome: " + prato.getNome() + "</p>\n" +
                        "  <p>Descrição: " + prato.getDescricao() + "</p>\n" +
                        "  <p>Categoria: " + prato.getCategoria() + "</p>\n";
            } else {
                corpo = "  <h1>Prato não encontrado.</h1>\n";
            }
        } catch (NumberFormatException e) {
            corpo = "  <h1>Id inválido.</h1>\n";
        }

        String html =
                "<html>\n" +
                        "<head>\n" +
                        "  <meta charset=\"UTF-8\">\n" +
                        "  <title>Consulta de Prato</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        corpo +
                        "  <a href=\"prato.html\">Voltar</a>\n" +
                        "</body>\n" +
                        "</html>";

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(html);
        out.close();
    }

    // ALTERAR
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mensagem;
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String nomePrato = req.getParameter("nomePrato");
            String descricaoPrato  = req.getParameter("descricaoPrato");
            String categoriaPrato  = req.getParameter("categoriaPrato");

            Prato prato = new Prato(id, nomePrato, descricaoPrato, categoriaPrato);
            PratoDAO pratoDAO = new PratoDAO();

            if(pratoDAO.atualizarPrato(prato)){mensagem = "Prato Alterado!";}
            else{
                mensagem = "Prato não encontrado.";
            }
        } catch (NumberFormatException e) {
            mensagem = "Id inválido.";
        }

        String html =
                "<html>\n" +
                        "<head>\n" +
                        "  <meta charset=\"UTF-8\">\n" +
                        "  <title>Alteração de Prato</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "  <h1>" + mensagem + "</h1>\n" +
                        "  <a href=\"prato.html\">Voltar</a>\n" +
                        "</body>\n" +
                        "</html>";

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(html);
        out.close();
    }

    // REMOVER
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mensagem;
        try {
            int id = Integer.parseInt(req.getParameter("id"));

            PratoDAO pratoDAO = new PratoDAO();

            if(pratoDAO.removerPrato(id)){mensagem= "Prato Removido!";}
            else{
                mensagem = "Prato não encontrado.";
            }
        } catch (NumberFormatException e) {
            mensagem = "Id inválido.";
        }

        String html =
                "<html>\n" +
                        "<head>\n" +
                        "  <meta charset=\"UTF-8\">\n" +
                        "  <title>Remoção de Prato</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "  <h1>" + mensagem + "</h1>\n" +
                        "  <a href=\"prato.html\">Voltar</a>\n" +
                        "</body>\n" +
                        "</html>";

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(html);
        out.close();
    }

    @Override
    public void destroy() {
        super.destroy();
    }

}
