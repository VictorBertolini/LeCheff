package com.lecheff.dao;

import com.lecheff.models.Cliente;

import java.sql.*;

public class ClienteDAO extends BaseDAO {

    public boolean inserirCliente(Cliente cliente){
        try (Connection conn = getConnection()) {

            // CADASTRAR
            System.out.println("--- INSERINDO CLIENTE ---");

            String insertCliente = "INSERT INTO Cliente (nome, idade, email, senha, telefone) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertCliente)) {
                stmt.setString(1, cliente.getNome());
                stmt.setInt(2, cliente.getIdade());
                stmt.setString(3, cliente.getEmail());
                stmt.setString(4, cliente.getSenha());
                stmt.setString(5, cliente.getTelefone());

                stmt.execute();
            }

            System.out.println("Dados cadastrados com sucesso!\n");
            return true;

        } catch (SQLException e) {
            mostrarErro(e);
            return false;
        }
    }

    public boolean atualizarCliente(Cliente cliente){
        try (Connection conn = getConnection()) {

            // ALTERAR
            System.out.println("--- ALTERANDO CLIENTE ---");

            String updateCliente = "UPDATE Cliente SET nome = ?, idade = ?, email = ?, senha = ?, telefone = ? WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(updateCliente)) {
                stmt.setString(1, cliente.getNome());
                stmt.setInt(2, cliente.getIdade());
                stmt.setString(3, cliente.getEmail());
                stmt.setString(4, cliente.getSenha());
                stmt.setString(5, cliente.getTelefone());
                stmt.setInt(6, cliente.getId());

                int linhas = stmt.executeUpdate();
                return linhas > 0;
            }

        } catch (SQLException e) {
            mostrarErro(e);
            return false;
        }
    }

    public Cliente buscarCliente(String email){
        try (Connection conn = getConnection()) {

            // CONSULTAR
            System.out.println("--- CONSULTANDO CLIENTE ---");

            String selectCliente = "SELECT id, nome, idade, email, senha, telefone FROM Cliente WHERE email = ?";
            try (PreparedStatement stmt = conn.prepareStatement(selectCliente)) {
                stmt.setString(1, email);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    // getObject devolve null quando a idade está vazia no banco (getInt devolveria 0)
                    return new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getInt("idade"),
                            rs.getString("email"), rs.getString("senha"), rs.getString("telefone"));
                }
            }

        } catch (SQLException e) {
            mostrarErro(e);
        }
        // não encontrou ou deu erro
        return null;
    }

    public boolean removerCliente(int id){
        try (Connection conn = getConnection()) {

            // REMOVER
            System.out.println("--- REMOVENDO CLIENTE ---");

            String deleteCliente = "DELETE FROM Cliente WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(deleteCliente)) {
                stmt.setInt(1, id);

                int linhas = stmt.executeUpdate();
                return linhas > 0;
            }

        } catch (SQLException e) {
            mostrarErro(e);
            return false;
        }
    }

}
