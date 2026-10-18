package com.lecheff;

import java.sql.*;

public class SiteChefApp {

    // Configurações do Banco de Dados
    private static final String URL = "jdbc:mysql://localhost:3306/le_cheff";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!\n");

            // CADASTRAR
            System.out.println("--- INSERINDO DADOS ---");

            // Cadastrar Cheff
            String insertCheff = "INSERT INTO Cheff (email, senha) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertCheff)) {
                stmt.setString(1, "chef.master@sitechef.com");
                stmt.setString(2, "senhaSecreta123");
                stmt.executeUpdate();
            }

            // Cadastrar Prato
            String insertPrato = "INSERT INTO Prato (nome, descricao, categoria) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertPrato)) {
                stmt.setString(1, "Risoto de Funghi");
                stmt.setString(2, "Risoto cremoso com cogumelos frescos e queijo parmesão.");
                stmt.setString(3, "Prato Principal");
                stmt.executeUpdate();
            }

            // Cadastrar Cliente
            int clienteId = -1;
            String insertCliente = "INSERT INTO Cliente (nome, idade, email, senha, telefone) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertCliente, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, "João Silva");
                stmt.setInt(2, 30);
                stmt.setString(3, "joao.silva@email.com");
                stmt.setString(4, "senha123");
                stmt.setString(5, "34999999999");
                stmt.executeUpdate();

                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    clienteId = rs.getInt(1);
                }
            }

            // Cadastrar Endereço vinculado ao Cliente
            if (clienteId != -1) {
                String insertEndereco = "INSERT INTO Endereco (cliente_id, cidade, cep, rua, numero, bairro, estado, complemento) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(insertEndereco)) {
                    stmt.setInt(1, clienteId);
                    stmt.setString(2, "Uberlândia");
                    stmt.setString(3, "38400-000");
                    stmt.setString(4, "Avenida João Naves");
                    stmt.setString(5, "1000");
                    stmt.setString(6, "Santa Mônica");
                    stmt.setString(7, "MG");
                    stmt.setString(8, "Apto 101");
                    stmt.executeUpdate();
                }
            }
            System.out.println("Dados cadastrados com sucesso!\n");

            //CONSULTAR
            System.out.println("--- CONSULTANDO CLIENTES ---");

            // Consultar Clientes e seus Endereços (Usando JOIN)
            String selectCliente = "SELECT c.nome, c.email, e.rua, e.numero, e.cidade FROM Cliente c JOIN Endereco e ON c.id = e.cliente_id";
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(selectCliente)) {
                while (rs.next()) {
                    System.out.println("Cliente: " + rs.getString("nome") + " | Email: " + rs.getString("email"));
                    System.out.println("Endereço: " + rs.getString("rua") + ", " + rs.getString("numero") + " - " + rs.getString("cidade"));
                }
            }

            System.out.println("--- CONSULTANDO PRATOS ---");
            // Consultar Pratos
            String selectPrato = "SELECT id, nome, categoria FROM Prato";
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(selectPrato)) {
                while (rs.next()) {
                    System.out.println("Prato ID " + rs.getInt("id") + ": " + rs.getString("nome") + " (" + rs.getString("categoria") + ")");
                }
            }
            System.out.println();


            //ALTERAR
            System.out.println("--- ATUALIZANDO DADOS ---");

            String updatePrato = "UPDATE Prato SET categoria = ? WHERE nome = ?";
            try (PreparedStatement stmt = conn.prepareStatement(updatePrato)) {
                stmt.setString(1, "Especial da Casa");
                stmt.setString(2, "Risoto de Funghi");
                int rows = stmt.executeUpdate();
                System.out.println(rows + " prato(s) atualizado(s).");
            }

            String updateCliente = "UPDATE Cliente SET telefone = ? WHERE email = ?";
            try (PreparedStatement stmt = conn.prepareStatement(updateCliente)) {
                stmt.setString(1, "34988888888");
                stmt.setString(2, "joao.silva@email.com");
                int rows = stmt.executeUpdate();
                System.out.println(rows + " cliente(s) atualizado(s).\n");
            }

            // DELETE
            System.out.println("--- REMOVENDO DADOS ---");

            // Removendo o Cheff
            String deleteCheff = "DELETE FROM Cheff WHERE email = ?";
            try (PreparedStatement stmt = conn.prepareStatement(deleteCheff)) {
                stmt.setString(1, "chef.master@sitechef.com");
                int rows = stmt.executeUpdate();
                System.out.println(rows + " cheff(s) removido(s).");
            }

            // Removendo o Cliente
            String deleteCliente = "DELETE FROM Cliente WHERE email = ?";
            try (PreparedStatement stmt = conn.prepareStatement(deleteCliente)) {
                stmt.setString(1, "joao.silva@email.com");
                int rows = stmt.executeUpdate();
                System.out.println(rows + " cliente(s) removido(s).");
            }

            String deletePrato = "DELETE FROM Prato WHERE nome = ?";
            try (PreparedStatement stmt = conn.prepareStatement(deletePrato)) {
                stmt.setString(1, "Risoto de Funghi");
                int rows = stmt.executeUpdate();
                System.out.println(rows + " prato(s) removido(s).");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao conectar ou executar comandos no banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }
}