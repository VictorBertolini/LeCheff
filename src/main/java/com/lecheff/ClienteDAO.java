package com.lecheff;

import java.sql.*;

public class ClienteDAO {


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

    public boolean inserirCliente(Cliente cliente){
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!\n");

            // CADASTRAR
            System.out.println("--- INSERINDO CLIENTE ---");


            // Cadastrar Cliente
            int clienteId = -1;
            String insertCliente = "INSERT INTO Cliente (nome, idade, email, senha, telefone) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertCliente, Statement.RETURN_GENERATED_KEYS)) {

                stmt.setString(1, cliente.getNome());
                stmt.setInt(2, cliente.getIdade());
                stmt.setString(3, cliente.getEmail());
                stmt.setString(4, cliente.getSenha());
                stmt.setString(5, cliente.getTelefone());

                stmt.execute();

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

                    stmt.setString(2, cliente.getCidade());
                    stmt.setString(3, cliente.getCep());
                    stmt.setString(4, cliente.getRua());
                    stmt.setString(5, cliente.getNumero());
                    stmt.setString(6, cliente.getBairro());
                    stmt.setString(7, cliente.getEstado());
                    stmt.setString(8, cliente.getComplemento());


                    stmt.execute();
                }
            }
            System.out.println("Dados cadastrados com sucesso!\n");
            return true;

        } catch (SQLException e) {
            System.err.println("Erro ao conectar ou executar comandos no banco de dados: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizarCliente(Cliente cliente){
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            // ALTERAR
            System.out.println("--- ALTERANDO CLIENTE ---");

            // Alterar Cliente
            int linhas;
            String updateCliente = "UPDATE Cliente SET nome = ?, idade = ?, email = ?, senha = ?, telefone = ? WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(updateCliente)) {
                stmt.setString(1, cliente.getNome());
                stmt.setInt(2, cliente.getIdade());
                stmt.setString(3, cliente.getEmail());
                stmt.setString(4, cliente.getSenha());
                stmt.setString(5, cliente.getTelefone());
                stmt.setInt(6, cliente.getId());

                linhas = stmt.executeUpdate();
            }

            // Alterar Endereço vinculado ao Cliente
            if (linhas > 0) {
                String updateEndereco = "UPDATE Endereco SET cidade = ?, cep = ?, rua = ?, numero = ?, bairro = ?, estado = ?, complemento = ? WHERE cliente_id = ?";
                try (PreparedStatement stmt = conn.prepareStatement(updateEndereco)) {
                    stmt.setString(1, cliente.getCidade());
                    stmt.setString(2, cliente.getCep());
                    stmt.setString(3, cliente.getRua());
                    stmt.setString(4, cliente.getNumero());
                    stmt.setString(5, cliente.getBairro());
                    stmt.setString(6, cliente.getEstado());
                    stmt.setString(7, cliente.getComplemento());
                    stmt.setInt(8, cliente.getId());

                    stmt.executeUpdate();
                }
                return true;
            }
            return false;

        } catch (SQLException e) {
            System.err.println("Erro ao conectar ou executar comandos no banco de dados: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public Cliente buscarCliente(int id){
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            // CONSULTAR
            System.out.println("--- CONSULTANDO CLIENTE ---");

            String selectCliente = "SELECT c.id, c.nome, c.idade, c.email, c.senha, c.telefone, " +
                    "e.cidade, e.cep, e.rua, e.numero, e.bairro, e.estado, e.complemento " +
                    "FROM Cliente c JOIN Endereco e ON e.cliente_id = c.id WHERE c.id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(selectCliente)) {
                stmt.setInt(1, id);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getInt("idade"),
                            rs.getString("email"), rs.getString("senha"), rs.getString("telefone"),
                            rs.getString("cidade"), rs.getString("cep"), rs.getString("rua"),
                            rs.getString("numero"), rs.getString("bairro"), rs.getString("estado"),
                            rs.getString("complemento"));
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao conectar ou executar comandos no banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
        // não encontrou ou deu erro
        return null;
    }

    public boolean removerCliente(int id){
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            // REMOVER (o endereço é removido junto pelo ON DELETE CASCADE)
            System.out.println("--- REMOVENDO CLIENTE ---");

            String deleteCliente = "DELETE FROM Cliente WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(deleteCliente)) {
                stmt.setInt(1, id);

                int linhas = stmt.executeUpdate();
                System.out.println(linhas);
                if(linhas > 0){return true;}

                return false;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao conectar ou executar comandos no banco de dados: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

}
