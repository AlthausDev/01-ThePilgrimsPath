package dao;

import entities.Carnet;
import entities.Parada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CarnetDAOImpl implements CarnetDAO {
    private Connection connection;

    // Constructor que recibe la conexión a la base de datos
    public CarnetDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Carnet getById(long id) {
        Carnet carnet = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Carnet WHERE idPeregrino = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                carnet = mapResultSetToCarnet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carnet;
    }

    @Override
    public void save(Carnet carnet) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Carnet VALUES (?, ?, ?, ?, ?)")) {
            statement.setLong(1, carnet.getIdPeregrino());
            statement.setDate(2, java.sql.Date.valueOf(carnet.getFechaExp()));
            statement.setLong(3, carnet.getParadaInicial().getId()); // Asumiendo que Parada tiene un id
            statement.setDouble(4, carnet.getDistancia());
            statement.setInt(5, carnet.getNvips());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Carnet carnet) {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE Carnet SET fechaExp = ?, paradaInicial = ?, distancia = ?, nvips = ? WHERE idPeregrino = ?")) {
            statement.setDate(1, java.sql.Date.valueOf(carnet.getFechaExp()));
            statement.setLong(2, carnet.getParadaInicial().getId()); // Asumiendo que Parada tiene un id
            statement.setDouble(3, carnet.getDistancia());
            statement.setInt(4, carnet.getNvips());
            statement.setLong(5, carnet.getIdPeregrino());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Carnet carnet) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Carnet WHERE idPeregrino = ?")) {
            statement.setLong(1, carnet.getIdPeregrino());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Carnet mapResultSetToCarnet(ResultSet resultSet) throws SQLException {
        long idPeregrino = resultSet.getLong("idPeregrino");
        LocalDate fechaExp = resultSet.getDate("fechaExp").toLocalDate();
        long idParada = resultSet.getLong("paradaInicial");
        double distancia = resultSet.getDouble("distancia");
        int nvips = resultSet.getInt("nvips");

        ParadaDAO paradaDAO = new ParadaDAOImpl(connection);
        Parada paradaInicial = paradaDAO.getById(idParada);

        return new Carnet(idPeregrino, fechaExp, paradaInicial, distancia, nvips);
    }

}
