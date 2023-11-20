package dao;

import entities.Carnet;
import entities.Estancia;
import entities.Parada;
import entities.Peregrino;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeregrinoDAOImpl implements PeregrinoDAO {
    private Connection connection;

    public PeregrinoDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Peregrino getById(long id) {
        Peregrino peregrino = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Peregrino WHERE id = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                peregrino = mapResultSetToPeregrino(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return peregrino;
    }

    @Override
    public List<Peregrino> getAll() {
        List<Peregrino> peregrinos = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Peregrino")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                peregrinos.add(mapResultSetToPeregrino(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return peregrinos;
    }

    @Override
    public void insert(Peregrino peregrino) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Peregrino VALUES (?, ?, ?, ?)")) {
            statement.setLong(1, peregrino.getId());
            statement.setString(2, peregrino.getNombre());
            statement.setString(3, peregrino.getNacionalidad());
            statement.setLong(4, peregrino.getCarnet().getIdPeregrino());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Peregrino peregrino) {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE Peregrino SET nombre = ?, nacionalidad = ?, idCarnet = ? WHERE id = ?")) {
            statement.setString(1, peregrino.getNombre());
            statement.setString(2, peregrino.getNacionalidad());
            statement.setLong(3, peregrino.getCarnet().getIdPeregrino());
            statement.setLong(4, peregrino.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Peregrino peregrino) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Peregrino WHERE id = ?")) {
            statement.setLong(1, peregrino.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Peregrino mapResultSetToPeregrino(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String nombre = resultSet.getString("nombre");
        String nacionalidad = resultSet.getString("nacionalidad");
        long idCarnet = resultSet.getLong("idCarnet");

        Carnet carnet = new CarnetDAOImpl(connection).getById(idCarnet);

        ArrayList<Parada> paradas = new ArrayList<>();
        ArrayList<Estancia> estancias = new ArrayList<>();
        return new Peregrino(id, nombre, nacionalidad, carnet, paradas, estancias);
    }
}
