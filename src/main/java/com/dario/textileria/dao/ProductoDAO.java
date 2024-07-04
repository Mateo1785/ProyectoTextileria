package com.dario.textileria.dao;

import com.dario.textileria.model.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private Connection connection;

    public ProductoDAO(Connection connection) {
        this.connection = connection;
    }

    public void agregarProducto(Producto producto) throws SQLException {
        String query = "INSERT INTO productos (nombre, descripcion, stock, precio_unitario) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getDescripcion());
            stmt.setInt(3, producto.getStock());
            stmt.setDouble(4, producto.getPrecio_unitario());
            stmt.executeUpdate();

            // Obtener el id generado automáticamente por la base de datos
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int idProductoGenerado = generatedKeys.getInt(1);
                    producto.setId_producto(idProductoGenerado);
                } else {
                    throw new SQLException("Fallo al obtener el id del producto generado.");
                }
            }
        }
    }



    public Producto obtenerProductoPorId(int idProducto) throws SQLException {
        Producto producto = null;
        String query = "SELECT * FROM productos WHERE id_producto = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idProducto);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    producto = new Producto();
                    producto.setId_producto(rs.getInt("id_producto"));
                    producto.setNombre(rs.getString("nombre"));
                    producto.setDescripcion(rs.getString("descripcion"));
                    producto.setStock(rs.getInt("stock"));
                    producto.setPrecio_unitario(rs.getDouble("precio_unitario"));
                }
            }
        }
        return producto;
    }

    public List<Producto> obtenerTodosLosProductos() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId_producto(rs.getInt("id_producto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setStock(rs.getInt("stock"));
                producto.setPrecio_unitario(rs.getDouble("precio_unitario"));
                productos.add(producto);
            }
        }
        return productos;
    }

    public void actualizarProducto(Producto producto) throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String query = "UPDATE productos SET nombre = ?, descripcion=?, stock=?, precio_unitario=? WHERE id_producto=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getDescripcion());
            stmt.setInt(3, producto.getStock());
            stmt.setDouble(4, producto.getPrecio_unitario());
            stmt.setInt(5, producto.getId_producto());
            stmt.executeUpdate();
        }
    }

    public void eliminarProducto(int id_producto) throws SQLException {
        String deletePedidosSql = "DELETE FROM pedidos WHERE id_producto = ?";
        try (PreparedStatement stmt = connection.prepareStatement(deletePedidosSql)) {
            stmt.setInt(1, id_producto);
            stmt.executeUpdate();
        }

        String deleteProductoSql = "DELETE FROM productos WHERE id_producto = ?";
        try (PreparedStatement stmt = connection.prepareStatement(deleteProductoSql)) {
            stmt.setInt(1, id_producto);
            stmt.executeUpdate();
        }
    }

    // Método para obtener la conexión actual, útil para depuración
    public Connection getConnection() {
        return connection;
    }
}
