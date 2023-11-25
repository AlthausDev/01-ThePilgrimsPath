package dao;

import model.Estancia;
import model.Peregrino;
import model.Parada;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class EstanciaDAOImpl extends CoreDAO<Estancia> {

    private PeregrinoDAOImpl peregrinoDAO;
    private ParadaDAOImpl paradaDAO;

    public EstanciaDAOImpl() {
        super();
        this.peregrinoDAO = new PeregrinoDAOImpl();
        this.paradaDAO = new ParadaDAOImpl();
    }

    @Override
    public void create(Estancia estancia) {
        try (PreparedStatement stmt = conexion.prepareStatement(
                "INSERT INTO Estancia (id, fecha, vip, fkIdPeregrino, fkIdParada) VALUES (?, ?, ?, ?, ?)")) {
            stmt.setLong(1, estancia.getId());
            stmt.setDate(2, java.sql.Date.valueOf(estancia.getFecha()));
            stmt.setBoolean(3, estancia.isVIP());
            stmt.setLong(4, estancia.getPeregrino().getId());
            stmt.setLong(5, estancia.getParada().getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta de inserción: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Estancia read(long id) {
        Estancia estancia = null;
        String sql = "SELECT * FROM Estancia WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    estancia = getResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estancia;
    }

    @Override
    public HashMap<Long, Estancia> readAll() {
        HashMap<Long, Estancia> estancias = new HashMap<>();
        String sql = "SELECT * FROM Estancia";

        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Estancia estancia = getResultSet(rs);
                estancias.put(estancia.getId(), estancia);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return estancias;
    }

    @Override
    public void update(Estancia estancia) {
        String sql = "UPDATE Estancia SET fecha = ?, vip = ?, fkIdPeregrino = ?, fkIdParada = ? WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setDate(1, java.sql.Date.valueOf(estancia.getFecha()));
            stmt.setBoolean(2, estancia.isVIP());
            stmt.setLong(3, estancia.getPeregrino().getId());
            stmt.setLong(4, estancia.getParada().getId());
            stmt.setLong(5, estancia.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM Estancia WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Estancia getResultSet(ResultSet rs) {
        try {
            long id = rs.getLong("id");
            LocalDate fecha = rs.getDate("fecha").toLocalDate();
            boolean vip = rs.getBoolean("vip");

            long peregrinoId = rs.getLong("fkIdPeregrino");
            Peregrino peregrino = peregrinoDAO.read(peregrinoId);

            long paradaId = rs.getLong("fkIdParada");
            Parada parada = paradaDAO.read(paradaId);

            return new Estancia(id, fecha, vip, peregrino, parada);
        } catch (SQLException e) {
            System.err.println("Error al obtener la estancia: " + e.getMessage());
        }
        return null;
    }

    protected ArrayList<Estancia> getListResultSet(ResultSet rs) {
        ArrayList<Estancia> estancias = new ArrayList<>();

        try {
            while (rs.next()) {
                Estancia estancia = getResultSet(rs);
                estancias.add(estancia);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener estancias desde ResultSet: " + e.getMessage());
            e.printStackTrace();
        }

        return estancias;
    }

}
