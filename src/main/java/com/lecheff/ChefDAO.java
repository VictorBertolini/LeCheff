package com.lecheff;

import java.sql.*;

public class ChefDAO {

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

    public boolean inserirChef(Chef chef){
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!\n");

            // CADASTRAR-
            System.out.println("--- INSERINDO CHEF ---");

            // Cadastrar Cheff
            String insertCheff = "INSERT INTO Cheff (email, senha) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertCheff)) {
                stmt.setString(1, chef.getEmail());
                stmt.setString(2, chef.getSenha());
                stmt.execute();
            }

            System.out.println("Dados cadastrados com sucesso!\n");
            return true;

        } catch (SQLException e) {
            System.err.println("Erro ao conectar ou executar comandos no banco de dados: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizarChef(Chef chef){
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            // ALTERAR (o email é a chave, então só a senha muda)
            System.out.println("--- ALTERANDO CHEF ---");

            String updateCheff = "UPDATE Cheff SET senha = ? WHERE email = ?";
            try (PreparedStatement stmt = conn.prepareStatement(updateCheff)) {
                stmt.setString(1, chef.getSenha());
                stmt.setString(2, chef.getEmail());

                int linhas = stmt.executeUpdate();
                if(linhas > 0){return true;}
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao conectar ou executar comandos no banco de dados: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public Chef buscarChef(String email){
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            // CONSULTAR
            System.out.println("--- CONSULTANDO CHEF ---");

            String selectCheff = "SELECT email, senha FROM Cheff WHERE email = ?";
            try (PreparedStatement stmt = conn.prepareStatement(selectCheff)) {
                stmt.setString(1, email);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return new Chef(rs.getString("email"), rs.getString("senha"));
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao conectar ou executar comandos no banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
        // não encontrou ou deu erro
        return null;
    }

    public boolean removerChef(String email){
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            // REMOVER
            System.out.println("--- REMOVENDO CHEF ---");

            String deleteCheff = "DELETE FROM Cheff WHERE email = ?";
            try (PreparedStatement stmt = conn.prepareStatement(deleteCheff)) {
                stmt.setString(1, email);

                int linhas = stmt.executeUpdate();
                if(linhas > 0){return true;}
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao conectar ou executar comandos no banco de dados: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }



    public static void main(String[] args) {

    }


}
