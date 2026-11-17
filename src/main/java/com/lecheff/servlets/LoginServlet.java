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

        // começa como inválido: só muda se encontrar cheff ou cliente com a senha certa
        String resultado = "login_invalido";

        Cheff cheff = cheffDAO.buscarChef(email);
        Cliente cliente = clienteDAO.buscarCliente(email);

        // 1º - tenta como cheff
        if (cheff != null && cheff.getSenha().equals(senha))
            resultado = "login_cheff";

        // 2º - tenta como cliente
        else if (cliente != null && cliente.getSenha().equals(senha))
            resultado = "login_cliente";

        // guarda quem está logado
        if (!resultado.equals("login_invalido")) {
            req.getSession().setAttribute("email", email);
        }

        // a página de destino é escolhida no fluxo_login_cadastro.jsp com <jsp:forward>
        req.setAttribute("resultado", resultado);
        req.getRequestDispatcher("fluxo_login_cadastro.jsp").forward(req, resp);
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
