package dao;

import entities.Estancia;
import entities.Parada;
import entities.Peregrino;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EstanciaDAOImpl implements EstanciaDAO {
    private Connection connection;

    public EstanciaDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Estancia getById(long id) {
        Estancia estancia = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Estancia WHERE id = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                estancia = mapResultSetToEstancia(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estancia;
    }

    @Override
    public List<Estancia> getAll() {
        List<Estancia> estancias = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Estancia")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                estancias.add(mapResultSetToEstancia(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estancias;
    }

    @Override
    public void insert(Estancia estancia) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Estancia VALUES (?, ?, ?, ?, ?)")) {
            statement.setLong(1, estancia.getId());
            statement.setDate(2, java.sql.Date.valueOf(estancia.getFecha()));
            statement.setBoolean(3, estancia.isVIP());
            statement.setLong(4, estancia.getPeregrino().getId());
            statement.setLong(5, estancia.getParada().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Estancia estancia) {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE Estancia SET fecha = ?, vip = ?, idPeregrino = ?, idParada = ? WHERE id = ?")) {
            statement.setDate(1, java.sql.Date.valueOf(estancia.getFecha()));
            statement.setBoolean(2, estancia.isVIP());
            statement.setLong(3, estancia.getPeregrino().getId());
            statement.setLong(4, estancia.getParada().getId());
            statement.setLong(5, estancia.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Estancia estancia) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Estancia WHERE id = ?")) {
            statement.setLong(1, estancia.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Estancia mapResultSetToEstancia(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        LocalDate fecha = resultSet.getDate("fecha").toLocalDate();
        boolean vip = resultSet.getBoolean("vip");
        long idPeregrino = resultSet.getLong("idPeregrino");
        long idParada = resultSet.getLong("idParada");

        Peregrino peregrino = new PeregrinoDAOImpl(connection).getById(idPeregrino);
        Parada parada = new ParadaDAOImpl(connection).getById(idParada);

        return new Estancia(id, fecha, vip, peregrino, parada);
    }
}
