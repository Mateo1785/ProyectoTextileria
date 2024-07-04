package com.dario.textileria.dao;

import com.dario.textileria.model.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {
    private Connection connection;

    public PedidoDAO(Connection connection) {
        this.connection = connection;
    }

    public void agregarPedido(Pedido pedido) throws SQLException {
        String sql = "INSERT INTO pedidos (id_usuario, id_producto, cantidad, fecha_pedido) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pedido.getId_usuario());
            stmt.setInt(2, pedido.getId_producto());
            stmt.setInt(3, pedido.getCantidad());
            stmt.setDate(4, new Date(System.currentTimeMillis())); // Set current date
            stmt.executeUpdate();
        }
    }

    public List<Pedido> obtenerTodosLosPedidos() throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();
        String query = "SELECT * FROM pedidos";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId_pedido(rs.getInt("id_pedido"));
                pedido.setId_usuario(rs.getInt("id_usuario"));
                pedido.setId_producto(rs.getInt("id_producto"));
                pedido.setCantidad(rs.getInt("cantidad"));
                pedido.setFecha_pedido(rs.getDate("fecha_pedido"));
                pedidos.add(pedido);
            }
        }
        return pedidos;
    }

    // Otras operaciones como obtenerTodosLosPedidos, actualizar, eliminar
    public Pedido obtenerPedidoPorId(int id) throws SQLException {
        Pedido pedido = null;
        String query = "SELECT * FROM pedidos WHERE id_pedido = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    pedido = new Pedido(
                            rs.getInt("id_usuario"),
                            rs.getInt("id_producto"),
                            rs.getInt("cantidad"),
                            rs.getTimestamp("fecha_pedido")
                    );
                    pedido.setId_pedido(rs.getInt("id_pedido"));
                }
            }
        }
        return pedido;
    }

    public void actualizarPedido(Pedido pedido) throws SQLException {
        String query = "UPDATE pedidos SET id_usuario = ?, id_producto = ?, cantidad = ?, fecha_pedido = ? WHERE id_pedido = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, pedido.getId_usuario());
            stmt.setInt(2, pedido.getId_producto());
            stmt.setInt(3, pedido.getCantidad());
            stmt.setTimestamp(4, new java.sql.Timestamp(pedido.getFecha_pedido().getTime()));
            stmt.setInt(5, pedido.getId_pedido());
            stmt.executeUpdate();
        }
    }

    public void eliminarPedido(int id) throws SQLException {
        String query = "DELETE FROM pedidos WHERE id_pedido = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}

