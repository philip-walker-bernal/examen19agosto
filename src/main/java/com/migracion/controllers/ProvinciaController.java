package main.java.com.migracion.controllers;

import main.java.com.migracion.models.Provincia;
import main.java.com.migracion.dao.DatabaseConnection;
import java.sql.*;

public class ProvinciaController {
    public int agregar(Provincia provincia) throws SQLException {
        String sql = "INSERT INTO Provincia (Nombre) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, provincia.getNombre());
            pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating provincia failed, no ID obtained.");
                }
            }
        }
    }
}