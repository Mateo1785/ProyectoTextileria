package com.dario.textileria.dao;

import com.dario.textileria.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    public Usuario obtenerUsuarioPorCredenciales(String usuario, String contrasena) throws SQLException {
        Usuario usuarioEncontrado = null;
        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND contrasena = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            stmt.setString(2, contrasena);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuarioEncontrado = new Usuario();
                    usuarioEncontrado.setIdUsuario(rs.getInt("id_usuario"));
                    usuarioEncontrado.setNombre(rs.getString("nombre"));
                    usuarioEncontrado.setTipo(rs.getString("tipo"));
                    usuarioEncontrado.setUsuario(rs.getString("usuario"));
                    usuarioEncontrado.setContrasena(rs.getString("contrasena"));
                }
            }
        }
        return usuarioEncontrado;
    }

    public boolean agregarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (nombre, tipo, usuario, contrasena) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getTipo());
            stmt.setString(3, usuario.getUsuario());
            stmt.setString(4, usuario.getContrasena());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public List<Usuario> obtenerTodosLosUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT id_usuario, nombre, tipo, usuario, contrasena FROM usuarios";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setTipo(rs.getString("tipo"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }

    public Usuario obtenerUsuarioPorId(int idUsuario) throws SQLException {
        Usuario usuarioEncontrado = null;
        String sql = "SELECT id_usuario, nombre, tipo, usuario, contrasena FROM usuarios WHERE id_usuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuarioEncontrado = new Usuario();
                    usuarioEncontrado.setIdUsuario(rs.getInt("id_usuario"));
                    usuarioEncontrado.setNombre(rs.getString("nombre"));
                    usuarioEncontrado.setTipo(rs.getString("tipo"));
                    usuarioEncontrado.setUsuario(rs.getString("usuario"));
                    usuarioEncontrado.setContrasena(rs.getString("contrasena"));
                }
            }
        }
        return usuarioEncontrado;
    }

    public void actualizarUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuarios SET nombre = ?, tipo = ?, usuario = ?, contrasena = ? WHERE id_usuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getTipo());
            stmt.setString(3, usuario.getUsuario());
            stmt.setString(4, usuario.getContrasena());
            stmt.setInt(5, usuario.getIdUsuario());
            stmt.executeUpdate();
        }
    }

    public void eliminarUsuario(int idUsuario) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.executeUpdate();
        }
    }
}
