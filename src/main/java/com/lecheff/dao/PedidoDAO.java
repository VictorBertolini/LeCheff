package com.lecheff.dao;

import com.lecheff.models.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO extends BaseDAO {

    // LISTAR TODOS (usado na área do cheff)
    public List<Pedido> listarPedidos(){
        List<Pedido> pedidos = new ArrayList<>();

        try (Connection conn = getConnection()) {
            System.out.println("--- LISTANDO PEDIDOS ---");

            // junta Pedido com Cliente (para o nome) e Endereco (para o endereço)
            String selectPedidos =
                    "SELECT p.id, c.nome, " +
                            "CONCAT(e.rua, ', ', e.numero, ' - ', e.bairro, ', ', e.cidade) AS endereco, " +
                            "p.qnt_pessoas, p.observacoes, " +
                            "DATE_FORMAT(p.data_hora, '%d/%m/%Y %H:%i') AS data_hora, p.status " +
                            "FROM Pedido p " +
                            "JOIN Cliente c ON c.id = p.cliente_id " +
                            "JOIN Endereco e ON e.id = p.endereco_id " +
                            "ORDER BY p.data_hora";

            try (PreparedStatement stmt = conn.prepareStatement(selectPedidos)) {
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    pedidos.add(new Pedido(rs.getInt("id"), rs.getString("nome"), rs.getString("endereco"),
                            rs.getInt("qnt_pessoas"), rs.getString("observacoes"),
                            rs.getString("data_hora"), rs.getString("status")));
                }
            }

        } catch (SQLException e) {
            mostrarErro(e);
        }
        // se der erro, volta a lista vazia
        return pedidos;
    }
}
