package dao;

import model.Perfil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import static dao.CoreDAO.conexion;

public class CredencialDAOImpl {

     public Long create(String nombre, String password, Perfil tipoUsuario) {
         try (PreparedStatement stmt = conexion.prepareStatement(
                 "INSERT INTO Tcredenciales (cNombre, cPassword, cPerfil) VALUES (?, ?, ?)",
                 Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, nombre);
            stmt.setString(2, password);
            stmt.setString(3, String.valueOf(tipoUsuario));
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getLong(1);
            } else {
                throw new SQLException("No se pudo obtener el ID generado automáticamente.");
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar credencial para el nuevo usuario: " + e);
        }
        return null;
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
        String sql = "SELECT * FROM Tcredenciales";

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

    public void update(long id, String nombre, String password, String tipoUsuario) {
        String sql = "UPDATE Tcredenciales SET cNombre = ?, cPassword = ?, cPerfil = ? WHERE pkId = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, password);
            stmt.setString(3, tipoUsuario);
            stmt.setLong(4, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar credencial con ID: " + id, e);
        }
    }

    public void delete(long id) {
        String sql = "DELETE FROM Tcredenciales WHERE id = ?";

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
            credencial.put("Id", String.valueOf(rs.getLong("pkId")));
            credencial.put("Nombre", rs.getString("cNombre"));
            credencial.put("password", rs.getString("cPassword"));
            credencial.put("Perfil", rs.getString("cPerfil"));
            return credencial;
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener credencial desde ResultSet", e);
        }
    }

    public long readByUserAndPassword(String username, String password) {
        String sql = "SELECT pkId FROM Tcredenciales WHERE cNombre = ? AND cPassword = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong("pkId");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener credenciales por nombre de usuario y contraseña: " + e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }
}
