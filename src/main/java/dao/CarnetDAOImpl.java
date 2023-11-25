package dao;

import model.Carnet;
import model.Parada;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;

public class CarnetDAOImpl extends CoreDAO<Carnet> {

    private ParadaDAOImpl paradaDAO;

    public CarnetDAOImpl(){
        super();
        this.paradaDAO = new ParadaDAOImpl();
    }

    @Override
    public void create(Carnet carnet) {
        String sql = "INSERT INTO Tcarnet (pkfkIdPeregrino, dFechaExp, nDistancia, nVips, fkIdParada) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setLong(1, carnet.getIdPeregrino());
            stmt.setDate(2, java.sql.Date.valueOf(carnet.getFechaExp()));
            stmt.setDouble(3, carnet.getDistancia());
            stmt.setInt(4, carnet.getNvips());
            stmt.setLong(5, carnet.getParadaInicial().getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Carnet read(long id) {
        Carnet carnet = null;
        String sql = "SELECT * FROM Tcarnet WHERE pkfkIdPeregrino = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    carnet = getResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carnet;
    }

    @Override
    public HashMap<Long, Carnet> readAll() {

        HashMap <Long, Carnet> carnets = new HashMap<>();
        String sql = "Select * FROM Tcarnet";

        try {
            PreparedStatement stmt = conexion.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Carnet carnet = getResultSet(rs);
                carnets.put(carnet.getIdPeregrino(), carnet);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return carnets;
    }

    @Override
    public void update(Carnet carnet) {
        String sql = "UPDATE Tcarnet SET dFechaExp = ?, nDistancia = ?, nVips = ?, fkIdParada = ? WHERE pkfkIdPeregrino = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setDate(1, java.sql.Date.valueOf(carnet.getFechaExp()));
            stmt.setDouble(2, carnet.getDistancia());
            stmt.setInt(3, carnet.getNvips());
            stmt.setLong(4, carnet.getParadaInicial().getId());
            stmt.setLong(5, carnet.getIdPeregrino());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void delete(long id) {
        String sql = "DELETE FROM Tcarnet WHERE pkfkIdPeregrino = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Carnet getResultSet(ResultSet rs) {
        try {
            long idPeregrino = rs.getLong("pkfkIdPeregrino");
            LocalDate fechaExp = rs.getDate("dFechaExp").toLocalDate();
            double distancia = rs.getDouble("nDistancia");
            int nvips = rs.getInt("nVips");
            long idParada = rs.getLong("fkIdParada");

            Parada paradaInicial = paradaDAO.read(idParada);
            return new Carnet(idPeregrino, fechaExp, paradaInicial, distancia, nvips);

        } catch (SQLException e) {
            System.err.println("Error al obtener la parada inicial: " + e.getMessage());
        }
        return null;
    }
}
