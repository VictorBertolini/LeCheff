package com.lecheff.servlets;

import com.lecheff.dao.ClienteDAO;
import com.lecheff.models.Cliente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/clientes")
public class ClienteServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {super.init();}

    // CADASTRAR
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mensagem;
        try {
            String nome = req.getParameter("nome");
            int idade = Integer.parseInt(req.getParameter("idade"));
            String email = req.getParameter("email");
            String senha = req.getParameter("senha");
            String telefone = req.getParameter("telefone");
            String cidade = req.getParameter("cidade");
            String cep = req.getParameter("cep");
            String rua = req.getParameter("rua");
            String numero = req.getParameter("numero");
            String bairro = req.getParameter("bairro");
            String estado = req.getParameter("estado");
            String complemento = req.getParameter("complemento");

            Cliente cliente = new Cliente(nome, idade, email, senha, telefone, cidade, cep, rua, numero, bairro, estado, complemento);
            ClienteDAO clienteDAO = new ClienteDAO();

            if(clienteDAO.inserirCliente(cliente)) {mensagem = "Cliente Cadastrado!";}
            else{mensagem = "Erro ao cadastrar o cliente.";}
        } catch (NumberFormatException e) {
            mensagem = "Idade inválida.";
        }

        String html =
                "<html>\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <title>Cadastro de Cliente</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <h1>" + mensagem + "</h1>\n" +
                "  <a href=\"cliente.html\">Voltar</a>\n" +
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

            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente cliente = clienteDAO.buscarCliente(id);

            if (cliente != null) {
                corpo = "  <h1>Cliente encontrado</h1>\n" +
                        "  <p>Id: " + cliente.getId() + "</p>\n" +
                        "  <p>Nome: " + cliente.getNome() + "</p>\n" +
                        "  <p>Idade: " + cliente.getIdade() + "</p>\n" +
                        "  <p>Email: " + cliente.getEmail() + "</p>\n" +
                        "  <p>Senha: " + cliente.getSenha() + "</p>\n" +
                        "  <p>Telefone: " + cliente.getTelefone() + "</p>\n" +
                        "  <p>Cidade: " + cliente.getCidade() + "</p>\n" +
                        "  <p>Cep: " + cliente.getCep() + "</p>\n" +
                        "  <p>Rua: " + cliente.getRua() + "</p>\n" +
                        "  <p>Número: " + cliente.getNumero() + "</p>\n" +
                        "  <p>Bairro: " + cliente.getBairro() + "</p>\n" +
                        "  <p>Estado: " + cliente.getEstado() + "</p>\n" +
                        "  <p>Complemento: " + cliente.getComplemento() + "</p>\n";
            } else {
                corpo = "  <h1>Cliente não encontrado.</h1>\n";
            }
        } catch (NumberFormatException e) {
            corpo = "  <h1>Id inválido.</h1>\n";
        }

        String html =
                "<html>\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <title>Consulta de Cliente</title>\n" +
                "</head>\n" +
                "<body>\n" +
                corpo +
                "  <a href=\"cliente.html\">Voltar</a>\n" +
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
        String mensagem;
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String nome = req.getParameter("nome");
            int idade = Integer.parseInt(req.getParameter("idade"));
            String email = req.getParameter("email");
            String senha = req.getParameter("senha");
            String telefone = req.getParameter("telefone");
            String cidade = req.getParameter("cidade");
            String cep = req.getParameter("cep");
            String rua = req.getParameter("rua");
            String numero = req.getParameter("numero");
            String bairro = req.getParameter("bairro");
            String estado = req.getParameter("estado");
            String complemento = req.getParameter("complemento");

            Cliente cliente = new Cliente(id, nome, idade, email, senha, telefone, cidade, cep, rua, numero, bairro, estado, complemento);
            ClienteDAO clienteDAO = new ClienteDAO();

            if(clienteDAO.atualizarCliente(cliente)){mensagem = "Cliente Alterado!";}
            else{mensagem = "Cliente não encontrado.";}
        } catch (NumberFormatException e) {
            mensagem = "Id ou idade inválidos.";
        }

        String html =
                "<html>\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <title>Alteração de Cliente</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <h1>" + mensagem + "</h1>\n" +
                "  <a href=\"cliente.html\">Voltar</a>\n" +
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

            ClienteDAO clienteDAO = new ClienteDAO();

            if(clienteDAO.removerCliente(id)){mensagem = "Cliente Removido!";}
            else{mensagem = "Cliente não encontrado.";}
        } catch (NumberFormatException e) {
            mensagem = "Id inválido.";
        }

        String html =
                "<html>\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <title>Remoção de Cliente</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <h1>" + mensagem + "</h1>\n" +
                "  <a href=\"cliente.html\">Voltar</a>\n" +
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
