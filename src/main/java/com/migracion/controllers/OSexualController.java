package main.java.com.migracion.controllers;

import main.java.com.migracion.models.OSexual;
import main.java.com.migracion.dao.DatabaseConnection;
import java.sql.*;

public class OSexualController {
    public int agregar(OSexual oSexual) throws SQLException {
        String sql = "INSERT INTO oSexual (Descripcion) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, oSexual.getDescripcion());
            pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating oSexual failed, no ID obtained.");
                }
            }
        }
    }
}