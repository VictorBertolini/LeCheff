package com.lecheff.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    // SAIR
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // pega a sessão se ela não existir
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();   // apaga a sessão
        }

        resp.sendRedirect("index.jsp");
    }
}