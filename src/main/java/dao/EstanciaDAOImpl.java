package dao;

import model.Estancia;
import model.Parada;
import model.Peregrino;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EstanciaDAOImpl implements EstanciaDAO {
    private final Connection connection;
    private final PeregrinoDAO peregrinoDAO;
    private final ParadaDAO paradaDAO;

    public EstanciaDAOImpl(Connection connection) {
        this.connection = connection;
        this.peregrinoDAO = new PeregrinoDAOImpl(connection);
        this.paradaDAO = new ParadaDAOImpl(connection);
    }

    @Override
    public Estancia getById(long id) {
        Estancia estancia = null;
        String sql = "SELECT * FROM Testancia WHERE pkIdEstancia = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    estancia = mapResultSetToEstancia(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estancia;
    }

    @Override
    public List<Estancia> getAll() {
        List<Estancia> estancias = new ArrayList<>();
        String sql = "SELECT * FROM Testancia";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    estancias.add(mapResultSetToEstancia(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estancias;
    }

    @Override
    public void insert(Estancia estancia) {
        String sql = "INSERT INTO Testancia (dFecha, bVip, fkIdPeregrino, fkIdParada) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            setEstanciaStatementValues(statement, estancia);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Estancia estancia) {
        String sql = "UPDATE Testancia SET dFecha = ?, bVip = ?, fkIdPeregrino = ?, fkIdParada = ? WHERE pkIdEstancia = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            setEstanciaStatementValues(statement, estancia);
            statement.setLong(5, estancia.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Estancia estancia) {
        String sql = "DELETE FROM Testancia WHERE pkIdEstancia = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, estancia.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Estancia mapResultSetToEstancia(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("pkIdEstancia");
        LocalDate fecha = resultSet.getDate("dFecha").toLocalDate();
        boolean vip = resultSet.getBoolean("bVip");
        long idPeregrino = resultSet.getLong("fkIdPeregrino");
        long idParada = resultSet.getLong("fkIdParada");

        Peregrino peregrino = peregrinoDAO.getById(idPeregrino);
        Parada parada = paradaDAO.getById(idParada);

        return new Estancia(id, fecha, vip, peregrino, parada);
    }

    private void setEstanciaStatementValues(PreparedStatement statement, Estancia estancia) throws SQLException {
        statement.setDate(1, java.sql.Date.valueOf(estancia.getFecha()));
        statement.setBoolean(2, estancia.isVIP());
        statement.setLong(3, estancia.getPeregrino().getId());
        statement.setLong(4, estancia.getParada().getId());
    }
}
