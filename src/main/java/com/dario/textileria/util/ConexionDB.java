package com.dario.textileria.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/textileria";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection connection;

    // Método privado para evitar instancias múltiples mediante Singleton
    private ConexionDB() {}

    // Obtener la conexión a la base de datos
    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error al conectar con la base de datos");
            }
        }
        return connection;
    }

    // Cerrar la conexión a la base de datos
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null; // Liberar la conexión
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para comprobar la conexión
    public static boolean comprobarConexion() {
        try (Connection conn = getConnection();
             java.sql.Statement stmt = conn.createStatement()) {
            stmt.executeQuery("SELECT 1");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        if (ConexionDB.comprobarConexion()) {
            System.out.println("Conexión establecida correctamente.");
        } else {
            System.out.println("Error al establecer la conexión.");
        }

        // No olvides cerrar la conexión al final
        ConexionDB.closeConnection();
    }
}
