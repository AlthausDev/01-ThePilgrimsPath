package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import static dao.CoreDAO.conexion;

public class CredencialDAOImpl {

    public void create(String nombre, String password, String tipoUsuario, long idUsuario) {
        try (PreparedStatement stmt = conexion.prepareStatement(
                "INSERT INTO Credenciales (nombre, password, tipo_usuario) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, nombre);
            stmt.setString(2, password);
            stmt.setString(3, tipoUsuario);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar credencial para el usuario con ID: " + idUsuario, e);
        }
    }

    public HashMap<String, String> read(long id) {
        String sql = "SELECT * FROM Tcredenciales WHERE pkId = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return getResultSet(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al leer la credencial con ID: " + id, e);
        }
        return null;
    }


    public HashMap<Long, HashMap<String, String>> readAll() {
        HashMap<Long, HashMap<String, String>> credenciales = new HashMap<>();
        String sql = "SELECT * FROM Credenciales";

        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                HashMap<String, String> credencial = getResultSet(rs);
                credenciales.put(rs.getLong("pkId"), credencial);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al leer todas las credenciales", e);
        }
        return credenciales;
    }

    public void update(long id, String nombre, String password, String tipoUsuario, long idUsuario) {
        String sql = "UPDATE Credenciales SET nombre = ?, password = ?, tipo_usuario = ? WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, password);
            stmt.setString(3, tipoUsuario);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar credencial con ID: " + id, e);
        }
    }

    public void delete(long id) {
        String sql = "DELETE FROM Credenciales WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al borrar credencial con ID: " + id, e);
        }
    }

    private HashMap<String, String> getResultSet(ResultSet rs) {
        try {
            HashMap<String, String> credencial = new HashMap<>();
            credencial.put("idUsuario", String.valueOf(rs.getLong("pkId")));
            credencial.put("nombre", rs.getString("nombre"));
            credencial.put("password", rs.getString("password"));
            credencial.put("tipoUsuario", rs.getString("tipo_usuario"));
            return credencial;
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener credencial desde ResultSet", e);
        }
    }
}
