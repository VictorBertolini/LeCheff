package com.lecheff.servlets;

import com.lecheff.dao.PedidoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/area_cheff")
public class AreaCheffServlet extends HttpServlet {

    private PedidoDAO pedidoDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        pedidoDAO = new PedidoDAO();
        System.out.println("AreaCheffServlet: init() - servlet iniciado");
    }

    // ÁREA DO CHEFF: busca os pedidos e manda para a JSP
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pedidos", pedidoDAO.listarPedidos());
        req.getRequestDispatcher("area_cheff.jsp").forward(req, resp);
    }

    // O login chega aqui por POST (o forward mantém o método), então faz o mesmo que o doGet
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
