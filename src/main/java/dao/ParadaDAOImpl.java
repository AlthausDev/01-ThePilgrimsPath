package dao;

import entities.AdminParada;
import entities.Parada;
import entities.Peregrino;
import entities.Perfil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ParadaDAOImpl implements ParadaDAO {
    private Connection connection;

    public ParadaDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Parada getById(long id) {
        Parada parada = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Parada WHERE id = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                parada = mapResultSetToParada(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parada;
    }

    @Override
    public void insert(Parada parada) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Parada VALUES (?, ?, ?, ?)")) {
            statement.setLong(1, parada.getId());
            statement.setString(2, parada.getNombre());
            statement.setString(3, String.valueOf(parada.getRegion()));
            statement.setLong(4, parada.getAdminParada().getId()); 
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Parada parada) {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE Parada SET nombre = ?, region = ?, adminParada = ? WHERE id = ?")) {
            statement.setString(1, parada.getNombre());
            statement.setString(2, String.valueOf(parada.getRegion()));
            statement.setLong(3, parada.getAdminParada().getId()); 
            statement.setLong(4, parada.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Parada parada) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Parada WHERE id = ?")) {
            statement.setLong(1, parada.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Parada mapResultSetToParada(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String nombre = resultSet.getString("nombre");
        char region = resultSet.getString("region").charAt(0);
        long adminParadaId = resultSet.getLong("adminParada");
        AdminParada adminParada = getAdminParadaById(adminParadaId);
        Parada parada = new Parada(id, nombre, region, adminParada);

        return parada;
    }

    private AdminParada getAdminParadaById(long id) throws SQLException {
        AdminParada adminParada = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM AdminParada WHERE id = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                adminParada = mapResultSetToAdminParada(resultSet);
            }
        }
        return adminParada;
    }

    private AdminParada mapResultSetToAdminParada(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String nombre = resultSet.getString("nombre");

        return new AdminParada(id, nombre);
    }

}
