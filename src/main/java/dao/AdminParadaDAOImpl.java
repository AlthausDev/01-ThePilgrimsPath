package dao;

import model.AdminParada;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class AdminParadaDAOImpl extends CoreDAO<AdminParada> {

	@Override
	public void create(AdminParada adminParada) {
		String sql = "INSERT INTO TadminParada (idAdminParadas, nombre) VALUES (?, ?)";

		try (PreparedStatement stmt = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setLong(1, adminParada.getId());
			stmt.setString(2, adminParada.getName());

			int filasAfectadas = stmt.executeUpdate();

			if (filasAfectadas == 0) {
				System.err.println("Error al crear AdminParada, no hay filas afectadas");
			}

			try (ResultSet clavesGeneradas = stmt.getGeneratedKeys()) {
				if (clavesGeneradas.next()) {
					adminParada.setId(clavesGeneradas.getLong(1));
				} else {
					System.err.println("Error al crear AdminParada, no se obtuvo el ID");
				}
			}
		} catch (SQLException e) {
			System.err.println("Error al ejecutar la consulta de inserción: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public AdminParada read(long id) {
		AdminParada adminParada = null;
		String sql = "SELECT * FROM TadminParada WHERE idAdminParadas = ?";

		try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
			stmt.setLong(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					adminParada = getResultSet(rs);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error al obtener AdminParada por ID: " + e.getMessage());
			e.printStackTrace();
		}
		return adminParada;
	}

	@Override
	public HashMap<Long, AdminParada> readAll() {
		HashMap<Long, AdminParada> adminParadas = new HashMap<>();
		String sql = "SELECT * FROM TadminParada";

		try (PreparedStatement stmt = conexion.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				AdminParada adminParada = getResultSet(rs);
				adminParadas.put(adminParada.getId(), adminParada);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return adminParadas;
	}


	@Override
	public void update(AdminParada adminParada) {
		String sql = "UPDATE TadminParada SET nombre = ? WHERE idAdminParadas = ?";

		try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
			stmt.setString(1, adminParada.getName());
			stmt.setLong(2, adminParada.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error al actualizar AdminParada: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM TadminParada WHERE idAdminParadas = ?";

		try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
			stmt.setLong(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error al eliminar AdminParada: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	protected AdminParada getResultSet(ResultSet rs) {
		try {
			long id = rs.getLong("id");
			String nombre = rs.getString("nombre");
			AdminParada adminParada = new AdminParada(id, nombre);
			return adminParada;

		} catch (SQLException e) {
			System.err.println("Error al mapear ResultSet a AdminParada: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}
