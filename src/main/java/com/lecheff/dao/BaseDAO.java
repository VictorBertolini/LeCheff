package com.lecheff.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseDAO {

    // Configurações do Banco de Dados
    private static final String URL = "jdbc:mysql://localhost:3306/le_cheff";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    // Carrega o driver do MySQL (no Tomcat ele não é registrado sozinho)
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Abre uma conexão com o banco
    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Mostra no console o erro que aconteceu no banco
    protected void mostrarErro(SQLException e) {
        System.err.println("Erro ao conectar ou executar comandos no banco de dados: " + e.getMessage());
        e.printStackTrace();
    }
}
