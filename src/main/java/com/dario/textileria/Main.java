package com.dario.textileria;

import com.dario.textileria.dao.UsuarioDAO;
import com.dario.textileria.model.Usuario;
import com.dario.textileria.util.ConexionDB;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        // Obtener la conexión a la base de datos
        try {
            // Inicializar la conexión a la base de datos
            Connection connection = ConexionDB.getConnection();

            // Crear una instancia de UsuarioDAO con la conexión
            UsuarioDAO usuarioDAO = new UsuarioDAO(connection);

            // Intentar autenticar un usuario
            String usuario = "ad";
            String contrasena = "123";
            Usuario usuarioAutenticado = usuarioDAO.obtenerUsuarioPorCredenciales(usuario, contrasena);

            // Verificar si la autenticación fue exitosa
            if (usuarioAutenticado != null) {
                System.out.println("Autenticación exitosa: " + usuarioAutenticado.getNombre());
            } else {
                System.out.println("Fallo en la autenticación");
            }

            // Cerrar la conexión a la base de datos
            ConexionDB.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

