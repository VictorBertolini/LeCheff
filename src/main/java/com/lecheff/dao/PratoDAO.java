package com.lecheff.dao;

import com.lecheff.models.Prato;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PratoDAO {

    // Configurações do Banco de Dados
    private static final String URL = "jdbc:mysql://localhost:3306/le_cheff";
    private static final String USER = "root";
    private static final String PASSWORD = "Batman@228626";

    // Carrega o driver do MySQL (no Tomcat ele não é registrado sozinho)
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean inserirPrato(Prato prato){
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!\n");

            // CADASTRAR
            System.out.println("--- INSERINDO PRATO ---");

            // Cadastrar Prato
            String insertPrato = "INSERT INTO Prato (nome, descricao, categoria) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertPrato)) {
                stmt.setString(1, prato.getNome());
                stmt.setString(2, prato.getDescricao());
                stmt.setString(3, prato.getCategoria());
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

    public boolean atualizarPrato(Prato prato){
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            // ALTERAR
            System.out.println("--- ALTERANDO PRATO ---");

            String updatePrato = "UPDATE Prato SET nome = ?, descricao = ?, categoria = ? WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(updatePrato)) {
                stmt.setString(1, prato.getNome());
                stmt.setString(2, prato.getDescricao());
                stmt.setString(3, prato.getCategoria());
                stmt.setInt(4, prato.getId());

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

    public Prato buscarPrato(int id){
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            // CONSULTAR
            System.out.println("--- CONSULTANDO PRATO ---");

            String selectPrato = "SELECT id, nome, descricao, categoria FROM Prato WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(selectPrato)) {
                stmt.setInt(1, id);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return new Prato(rs.getInt("id"), rs.getString("nome"), rs.getString("descricao"), rs.getString("categoria"));
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao conectar ou executar comandos no banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
        // não encontrou ou deu erro
        return null;
    }

    public boolean removerPrato(int id){
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            // REMOVER
            System.out.println("--- REMOVENDO PRATO ---");

            String deletePrato = "DELETE FROM Prato WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(deletePrato)) {
                stmt.setInt(1, id);

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
