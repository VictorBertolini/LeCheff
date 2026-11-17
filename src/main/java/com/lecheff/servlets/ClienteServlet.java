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

    private ClienteDAO clienteDAO;

    // CICLO DE VIDA 1 - init(): roda uma única vez, quando o Tomcat cria o servlet
    @Override
    public void init() throws ServletException {
        super.init();
        clienteDAO = new ClienteDAO();
        System.out.println("ClienteServlet: init() - servlet iniciado");
    }

    // CICLO DE VIDA 2 - service(): roda a cada requisição e encaminha para doGet, doPost, doPut ou doDelete
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ClienteServlet: service() - requisição " + req.getMethod());
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        super.service(req, resp);
    }

    // CADASTRAR
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String nome = req.getParameter("nome");
        int idade = Integer.parseInt(req.getParameter("idade"));
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        String telefone = req.getParameter("telefone");

        String resultado;

        // email já cadastrado: avisa que o usuário deve fazer login
        if (clienteDAO.buscarCliente(email) != null) {
            resultado = "email_cadastrado";
        } else {
            Cliente cliente = new Cliente(nome, idade, email, senha, telefone);

            if (clienteDAO.inserirCliente(cliente))
                resultado = "login_cliente";
            else
                resultado = "erro";
        }

        // guarda quem está logado
        if (resultado.equals("login_cliente")) {
            req.getSession().setAttribute("email", email);
        }

        // a página de destino é escolhida no fluxo_login_cadastro.jsp com <jsp:forward>
        req.setAttribute("resultado", resultado);
        req.getRequestDispatcher("fluxo_login_cadastro.jsp").forward(req, resp);
    }

    // CONSULTAR
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String email = req.getParameter("email");

        Cliente cliente = clienteDAO.buscarCliente(email);

        if (cliente != null) {
            // manda o cliente encontrado para a JSP mostrar
            req.setAttribute("cliente", cliente);
//            req.getRequestDispatcher("consulta_cliente.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("erro/cliente_nao_encontrado.jsp").forward(req, resp);
        }
    }

    // ALTERAR
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String mensagem;
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String nome = req.getParameter("nome");
            int idade = Integer.parseInt(req.getParameter("idade"));
            String email = req.getParameter("email");
            String senha = req.getParameter("senha");
            String telefone = req.getParameter("telefone");

            Cliente cliente = new Cliente(id, nome, idade, email, senha, telefone);

            if(clienteDAO.atualizarCliente(cliente))
                mensagem = "Cliente Alterado!";
            else
                mensagem = "Cliente não encontrado.";

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
        PrintWriter out = resp.getWriter();
        out.print(html);
        out.close();
    }

    // REMOVER
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String mensagem;
        try {
            int id = Integer.parseInt(req.getParameter("id"));

            if(clienteDAO.removerCliente(id))
                mensagem = "Cliente Removido!";
            else
                mensagem = "Cliente não encontrado.";

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
        PrintWriter out = resp.getWriter();
        out.print(html);
        out.close();
    }

    // CICLO DE VIDA 3 - destroy(): roda uma única vez, quando o Tomcat desliga ou recarrega a aplicação
    @Override
    public void destroy() {
        clienteDAO = null;
        System.out.println("ClienteServlet: destroy() - servlet finalizado");
        super.destroy();
    }
}
