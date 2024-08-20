package main.java.com.migracion.controllers;

import main.java.com.migracion.models.Persona;
import main.java.com.migracion.dao.DatabaseConnection;
import java.sql.*;


import java.util.ArrayList;
import java.util.List;





public class PersonaController {

    public void agregar(Persona persona) throws SQLException {
        String sql = "INSERT INTO Persona (Nombre, Apell1, Apell2, Sexo, eCivil, Nacido, Id_prov, Id_sexual, Id_poli, Id_acad, Salario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, persona.getNombre());
            pstmt.setString(2, persona.getApell1());
            pstmt.setString(3, persona.getApell2());
            pstmt.setString(4, String.valueOf(persona.getSexo()));
            pstmt.setString(5, String.valueOf(persona.geteCivil()));
            pstmt.setDate(6, new java.sql.Date(persona.getNacido().getTime()));
            pstmt.setInt(7, persona.getId_prov());
            pstmt.setInt(8, persona.getId_sexual());
            pstmt.setInt(9, persona.getId_poli());
            pstmt.setInt(10, persona.getId_acad());
            pstmt.setInt(11, persona.getSalario());
            pstmt.executeUpdate();
        }
    }

    public Persona obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Persona WHERE Id_perso = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapearPersona(rs);
                }
            }
        }
        return null;
    }

    public List<Persona> obtenerTodos() throws SQLException {
        List<Persona> personas = new ArrayList<>();
        String sql = "SELECT * FROM Persona";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                personas.add(mapearPersona(rs));
            }
        }
        return personas;
    }

    public void actualizar(Persona persona) throws SQLException {
        String sql = "UPDATE Persona SET Nombre = ?, Apell1 = ?, Apell2 = ?, Sexo = ?, eCivil = ?, Nacido = ?, Id_prov = ?, Id_sexual = ?, Id_poli = ?, Id_acad = ?, Salario = ? WHERE Id_perso = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, persona.getNombre());
            pstmt.setString(2, persona.getApell1());
            pstmt.setString(3, persona.getApell2());
            pstmt.setString(4, String.valueOf(persona.getSexo()));
            pstmt.setString(5, String.valueOf(persona.geteCivil()));
            pstmt.setDate(6, new java.sql.Date(persona.getNacido().getTime()));
            pstmt.setInt(7, persona.getId_prov());
            pstmt.setInt(8, persona.getId_sexual());
            pstmt.setInt(9, persona.getId_poli());
            pstmt.setInt(10, persona.getId_acad());
            pstmt.setInt(11, persona.getSalario());
            pstmt.setInt(12, persona.getId_perso());
            pstmt.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM Persona WHERE Id_perso = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    private Persona mapearPersona(ResultSet rs) throws SQLException {
        Persona persona = new Persona();
        persona.setId_perso(rs.getInt("Id_perso"));
        persona.setNombre(rs.getString("Nombre"));
        persona.setApell1(rs.getString("Apell1"));
        persona.setApell2(rs.getString("Apell2"));
        persona.setSexo(rs.getString("Sexo").charAt(0));
        persona.seteCivil(rs.getString("eCivil").charAt(0));
        persona.setNacido(rs.getDate("Nacido"));
        persona.setId_prov(rs.getInt("Id_prov"));
        persona.setId_sexual(rs.getInt("Id_sexual"));
        persona.setId_poli(rs.getInt("Id_poli"));
        persona.setId_acad(rs.getInt("Id_acad"));
        persona.setSalario(rs.getInt("Salario"));
        return persona;
    }
}