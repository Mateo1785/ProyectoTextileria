package com.dario.textileria.dao;

import com.dario.textileria.model.Factura;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAO {
    private Connection connection;

    public FacturaDAO(Connection connection) {
        this.connection = connection;
    }

    public void agregarFactura(Factura factura) throws SQLException {
        String query = "INSERT INTO facturas (id_pedido, fecha_factura, total) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, factura.getIdPedido());
            stmt.setDate(2, new java.sql.Date(factura.getFechaFactura().getTime()));
            stmt.setDouble(3, factura.getTotal());
            stmt.executeUpdate();

            // Obtener el ID generado para la factura
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    factura.setIdFactura(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Error al obtener el ID generado para la factura.");
                }
            }
        }
    }

    public Factura obtenerFactura(int idFactura) throws SQLException {
        Factura factura = null;
        String query = "SELECT * FROM facturas WHERE id_factura = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idFactura);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    factura = new Factura(
                            rs.getInt("id_factura"),
                            rs.getInt("id_pedido"),
                            rs.getDate("fecha_factura"),
                            rs.getDouble("total")
                    );
                }
            }
        }
        return factura;
    }

    public List<Factura> obtenerTodasLasFacturas() throws SQLException {
        List<Factura> facturas = new ArrayList<>();
        String query = "SELECT * FROM facturas";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Factura factura = new Factura(
                        rs.getInt("id_factura"),
                        rs.getInt("id_pedido"),
                        rs.getDate("fecha_factura"),
                        rs.getDouble("total")
                );
                facturas.add(factura);
            }
        }
        return facturas;
    }

    public void actualizarFactura(Factura factura) throws SQLException {
        String query = "UPDATE facturas SET id_pedido = ?, fecha_factura = ?, total = ? WHERE id_factura = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, factura.getIdPedido());
            stmt.setDate(2, new java.sql.Date(factura.getFechaFactura().getTime()));
            stmt.setDouble(3, factura.getTotal());
            stmt.setInt(4, factura.getIdFactura());
            int filasActualizadas = stmt.executeUpdate();
            if (filasActualizadas == 0) {
                throw new SQLException("No se actualizó ninguna fila para la factura con ID: " + factura.getIdFactura());
            }
        }
    }

    public void eliminarFactura(int idFactura) throws SQLException {
        String query = "DELETE FROM facturas WHERE id_factura = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idFactura);
            int filasEliminadas = stmt.executeUpdate();
            if (filasEliminadas == 0) {
                throw new SQLException("No se eliminó ninguna fila para la factura con ID: " + idFactura);
            }
        }
    }

    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
