package dao;

import model.Carnet;
import model.Parada;
import model.Peregrino;
import model.Estancia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeregrinoDAOImpl implements PeregrinoDAO {
    private final Connection connection;
    private final CarnetDAO carnetDAO;    
    

    public PeregrinoDAOImpl(Connection connection) {
        this.connection = connection;
        this.carnetDAO = new CarnetDAOImpl(connection);
    }

    @Override
    public Peregrino getById(long id) {
        Peregrino peregrino = null;
        String sql = "SELECT * FROM Tperegrino WHERE pkIdPeregrino = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    peregrino = mapResultSetToPeregrino(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return peregrino;
    }

    @Override
    public List<Peregrino> getAll() {
        List<Peregrino> peregrinos = new ArrayList<>();
        String sql = "SELECT * FROM Tperegrino";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    peregrinos.add(mapResultSetToPeregrino(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return peregrinos;
    }

    @Override
    public void insert(Peregrino peregrino) {
        String sql = "INSERT INTO Tperegrino (cNombrePer, cNacionalidad) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, peregrino.getNombre());
            statement.setString(2, peregrino.getNacionalidad());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long id = generatedKeys.getLong(1);
                    peregrino.setId(id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Peregrino peregrino) {
        String sql = "UPDATE Tperegrino SET cNombrePer = ?, cNacionalidad = ? WHERE pkIdPeregrino = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, peregrino.getNombre());
            statement.setString(2, peregrino.getNacionalidad());
            statement.setLong(3, peregrino.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Peregrino peregrino) {
        String sql = "DELETE FROM Tperegrino WHERE pkIdPeregrino = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, peregrino.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Peregrino mapResultSetToPeregrino(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("pkIdPeregrino");
        String nombre = resultSet.getString("cNombrePer");
        String nacionalidad = resultSet.getString("cNacionalidad");

        Carnet carnet = carnetDAO.getByPeregrinoId(id);
        ArrayList<Parada> paradas = new ArrayList<>();
        ArrayList<Estancia> estancias = new ArrayList<>();

        return new Peregrino(id, nombre, nacionalidad, carnet, paradas, estancias);
    }
}
