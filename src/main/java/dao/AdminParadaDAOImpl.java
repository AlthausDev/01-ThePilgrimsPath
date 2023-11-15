package dao;

import entities.AdminParada;
import entities.Perfil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * La clase `AdminParadaDAO` proporciona métodos para acceder y manipular datos de administradores de parada en la base de datos.
 *
 * @author S.Althaus
 */
public class AdminParadaDAOImpl {

    /**
     * Guarda un nuevo administrador de parada en la base de datos.
     *
     * @param adminParada El administrador de parada a guardar.
     * @return true si se guarda correctamente, false en caso contrario.
     */
    public boolean saveAdminParada(AdminParada adminParada) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Establecer conexión a la base de datos

            // Preparar la consulta SQL para insertar el administrador de parada
            String sql = "INSERT INTO admin_parada (id, nombre) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, adminParada.getId());
            preparedStatement.setString(2, adminParada.getName());

            // Ejecutar la consulta
            int rowsAffected = preparedStatement.executeUpdate();

            // Verificar si se guardó correctamente
            return rowsAffected > 0;
        } catch (SQLException e) {
            // Manejar errores de la base de datos
            e.printStackTrace();
            return false;
        } finally {
            // Cerrar recursos (Connection, PreparedStatement, ResultSet, etc.)
        }
    }

    /**
     * Obtiene un administrador de parada por su identificador único.
     *
     * @param id El identificador único del administrador de parada.
     * @return El administrador de parada encontrado, o null si no existe.
     */
    public AdminParada getAdminParadaById(long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Establecer conexión a la base de datos

            // Preparar la consulta SQL para obtener el administrador de parada por ID
            String sql = "SELECT id, nombre FROM admin_parada WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            // Ejecutar la consulta y obtener el resultado
            resultSet = preparedStatement.executeQuery();

            // Verificar si se encontró el administrador de parada
            if (resultSet.next()) {
                // Crear y devolver el objeto AdminParada
                return new AdminParada(resultSet.getLong("id"), resultSet.getString("nombre"));
            } else {
                // No se encontró el administrador de parada
                return null;
            }
        } catch (SQLException e) {
            // Manejar errores de la base de datos
            e.printStackTrace();
            return null;
        } finally {
            // Cerrar recursos (Connection, PreparedStatement, ResultSet, etc.)
        }
    }
}
