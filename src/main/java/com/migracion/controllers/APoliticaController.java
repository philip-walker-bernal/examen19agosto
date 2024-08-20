package main.java.com.migracion.controllers;

import main.java.com.migracion.models.APolitica;
import main.java.com.migracion.dao.DatabaseConnection;
import java.sql.*;

public class APoliticaController {
    public int agregar(APolitica aPolitica) throws SQLException {
        String sql = "INSERT INTO aPolitica (Nombre) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, aPolitica.getNombre());
            pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating aPolitica failed, no ID obtained.");
                }
            }
        }
    }
}