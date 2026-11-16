package com.lecheff.servlets;

import com.lecheff.dao.CheffDAO;
import com.lecheff.dao.ClienteDAO;
import com.lecheff.models.Cheff;
import com.lecheff.models.Cliente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private ClienteDAO clienteDAO;
    private CheffDAO cheffDAO;

    // CICLO DE VIDA 1 - init(): roda uma única vez, quando o Tomcat cria o servlet
    @Override
    public void init() throws ServletException {
        super.init();
        clienteDAO = new ClienteDAO();
        cheffDAO = new CheffDAO();
        System.out.println("LoginServlet: init() - servlet iniciado");
    }

    // CICLO DE VIDA 2 - service(): roda a cada requisição e encaminha para doGet, doPost, doPut ou doDelete
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LoginServlet: service() - requisição " + req.getMethod());
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        super.service(req, resp);
    }

    // LOGIN
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        // 1º - tenta como cheff
        Cheff cheff = cheffDAO.buscarChef(email);
        if (cheff != null && cheff.getSenha().equals(senha)) {
            req.getRequestDispatcher("area_cheff.jsp").forward(req, resp);
            return;
        }

        // 2º - tenta como cliente
        Cliente cliente = clienteDAO.buscarCliente(email);
        if (cliente != null && cliente.getSenha().equals(senha)) {
            req.getRequestDispatcher("area_cliente.jsp").forward(req, resp);
            return;
        }

        System.out.println("Cliente: " + cliente.getNome() + " " + cliente.getEmail() + " " + cliente.getSenha());

        // 3º - não achou ninguém: volta para o login com mensagem de erro
        req.setAttribute("erro", "Email ou senha inválidos.");
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    // CICLO DE VIDA 3 - destroy(): roda uma única vez, quando o Tomcat desliga ou recarrega a aplicação
    @Override
    public void destroy() {
        clienteDAO = null;
        cheffDAO = null;
        System.out.println("LoginServlet: destroy() - servlet finalizado");
        super.destroy();
    }
}
