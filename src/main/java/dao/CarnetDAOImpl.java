package dao;

import model.Carnet;
import model.Parada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CarnetDAOImpl implements CarnetDAO {
    private final Connection connection;
    private final ParadaDAO paradaDAO;

    public CarnetDAOImpl(Connection connection) {
        this.connection = connection;
        this.paradaDAO = new ParadaDAOImpl(connection);
    }

    @Override
    public Carnet getByPeregrinoId(long id) {
        Carnet carnet = null;
        String sql = "SELECT * FROM Tcarnet WHERE pkfkIdPeregrino = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    carnet = mapResultSetToCarnet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carnet;
    }

    @Override
    public void insert(Carnet carnet) {
        String sql = "INSERT INTO Tcarnet (pkfkIdPeregrino, dFechaExp, nDistancia, nVips, fkIdParada) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, carnet.getIdPeregrino());
            statement.setDate(2, java.sql.Date.valueOf(carnet.getFechaExp()));
            statement.setDouble(3, carnet.getDistancia());
            statement.setInt(4, carnet.getNvips());
            statement.setLong(5, carnet.getParadaInicial().getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Carnet carnet) {
        String sql = "UPDATE Tcarnet SET dFechaExp = ?, nDistancia = ?, nVips = ?, fkIdParada = ? WHERE pkfkIdPeregrino = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, java.sql.Date.valueOf(carnet.getFechaExp()));
            statement.setDouble(2, carnet.getDistancia());
            statement.setInt(3, carnet.getNvips());
            statement.setLong(4, carnet.getParadaInicial().getId());
            statement.setLong(5, carnet.getIdPeregrino());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Carnet carnet) {
        String sql = "DELETE FROM Tcarnet WHERE pkfkIdPeregrino = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, carnet.getIdPeregrino());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Carnet mapResultSetToCarnet(ResultSet resultSet) throws SQLException {
        long idPeregrino = resultSet.getLong("pkfkIdPeregrino");
        LocalDate fechaExp = resultSet.getDate("dFechaExp").toLocalDate();
        double distancia = resultSet.getDouble("nDistancia");
        int nvips = resultSet.getInt("nVips");
        long idParada = resultSet.getLong("fkIdParada");

        Parada paradaInicial = paradaDAO.getById(idParada);

        return new Carnet(idPeregrino, fechaExp, paradaInicial, distancia, nvips);
    }
}
