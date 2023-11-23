package dao;

import entities.AdminParada;
import entities.Parada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParadaDAOImpl implements ParadaDAO {
    private final Connection connection;

    public ParadaDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Parada getById(long id) {
        Parada parada = null;
        String sql = "SELECT * FROM Tparadas WHERE pkIdParada = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    parada = mapResultSetToParada(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parada;
    }

    @Override
    public void insert(Parada parada) {
        String sql = "INSERT INTO Tparadas (cNombrePar, cRegion, fkIdAdminParada) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            setParadaStatementValues(statement, parada);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Parada parada) {
        String sql = "UPDATE Tparadas SET cNombrePar = ?, cRegion = ?, fkIdAdminParada = ? WHERE pkIdParada = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            setParadaStatementValues(statement, parada);
            statement.setLong(4, parada.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Parada parada) {
        String sql = "DELETE FROM Tparadas WHERE pkIdParada = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, parada.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Parada mapResultSetToParada(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("pkIdParada");
        String nombre = resultSet.getString("cNombrePar");
        char region = resultSet.getString("cRegion").charAt(0);
        long adminParadaId = resultSet.getLong("fkIdAdminParada");
        AdminParada adminParada = mapResultSetToAdminParada(adminParadaId);
        Parada parada = new Parada(id, nombre, region, adminParada);

        return parada;
    }

    private AdminParada mapResultSetToAdminParada(long adminParadaId) throws SQLException {
        String sql = "SELECT * FROM Tadmin_parada WHERE pkIdAdminParada = ?";
        AdminParada adminParada = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, adminParadaId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    adminParada = mapResultSetToAdminParada(resultSet);
                }
            }
        }
        return adminParada;
    }

    private AdminParada mapResultSetToAdminParada(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("pkIdAdminParada");
        String nombre = resultSet.getString("cNombreAdminParada");

        return new AdminParada(id, nombre);
    }

    private void setParadaStatementValues(PreparedStatement statement, Parada parada) throws SQLException {
        statement.setString(1, parada.getNombre());
        statement.setString(2, String.valueOf(parada.getRegion()));
        statement.setLong(3, parada.getAdminParada().getId());
    }
}
