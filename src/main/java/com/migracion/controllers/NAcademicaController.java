package main.java.com.migracion.controllers;

import main.java.com.migracion.models.NAcademica;
import main.java.com.migracion.dao.DatabaseConnection;
import java.sql.*;

public class NAcademicaController {
    public int agregar(NAcademica nAcademica) throws SQLException {
        String sql = "INSERT INTO nAcademica (Descripcion) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, nAcademica.getDescripcion());
            pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating nAcademica failed, no ID obtained.");
                }
            }
        }
    }
}