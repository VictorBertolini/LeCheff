package com.lecheff.servlets;


import com.lecheff.beans.UploadBeanSeguro;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;


public class ArquivoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession(false);
        String email = (session != null) ? (String) session.getAttribute("email") : null;
        String contexto = req.getParameter("contexto");

        if (email == null || (!"cliente".equals(contexto) && !"cheff".equals(contexto))) {
            resp.sendRedirect("login.jsp");
            return;
        }

        UploadBeanSeguro upl = new UploadBeanSeguro();
        upl.setDiretorio("uploads");
        upl.setContextoUsuario(contexto);
        upl.setExtensoesPermitidas("pdf, jpg, jpeg, png, doc, docx, txt");
        upl.setSize(5); // 5 MB por arquivo

        boolean sucesso = upl.doFilePost(req, getServletContext());

        if (sucesso) {
            req.setAttribute("mensagemUpload", "Arquivos enviados com sucesso: " + String.join(" e ", upl.getFilenames()));
        } else {
            req.setAttribute("erroUpload", upl.getErro());
        }

        String destino = "cheff".equals(contexto) ? "area_cheff.jsp" : "area_cliente.jsp";
        req.getRequestDispatcher(destino).forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String email = (session != null) ? (String) session.getAttribute("email") : null;
        String contexto = req.getParameter("contexto");
        String nomeArquivo = req.getParameter("arquivo");

        if (email == null || (!"cliente".equals(contexto) && !"cheff".equals(contexto))) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Acesso negado ao contexto.");
            return;
        }

        if (nomeArquivo == null || nomeArquivo.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Arquivo não informado.");
            return;
        }

        String arquivoSeguro = Paths.get(nomeArquivo).getFileName().toString();
        String caminhoReal = getServletContext().getRealPath("uploads" + File.separator + contexto + File.separator + arquivoSeguro);
        File file = new File(caminhoReal);

        if (!file.exists() || !file.isFile()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Arquivo não encontrado neste contexto.");
            return;
        }

        String mimeType = getServletContext().getMimeType(file.getAbsolutePath());
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }

        resp.setContentType(mimeType);
        resp.setContentLengthLong(file.length());
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + arquivoSeguro + "\"");

        try (FileInputStream in = new FileInputStream(file);
             OutputStream out = resp.getOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }
}