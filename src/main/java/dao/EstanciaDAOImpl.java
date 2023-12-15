package dao;

import model.Estancia;
import model.Peregrino;
import model.Parada;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class EstanciaDAOImpl extends CoreDAO<Estancia> {

    private final PeregrinoDAOImpl peregrinoDAO;
    private final ParadaDAOImpl paradaDAO;

    public EstanciaDAOImpl() {
        super();
        this.peregrinoDAO = new PeregrinoDAOImpl();
        this.paradaDAO = new ParadaDAOImpl();
    }

    @Override
    public void create(Estancia estancia) {
        String sql = "INSERT INTO Testancia (dFecha, bVip, fkIdPeregrino, fkIdParada) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(estancia.getFecha()));
            stmt.setBoolean(2, estancia.isVIP());
            stmt.setLong(3, estancia.getPeregrino().getId());
            stmt.setLong(4, estancia.getParada().getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta de inserción: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Estancia read(long id) {
        Estancia estancia = null;
        String sql = "SELECT * FROM Testancia WHERE pkIdEstancia = ?";

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
        String sql = "SELECT * FROM Testancia";

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
        String sql = "UPDATE Testancia SET dFecha = ?, bVip = ?, fkIdPeregrino = ?, fkIdParada = ? WHERE pkIdEstancia = ?";

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
        String sql = "DELETE FROM Testancia WHERE pkIdEstancia = ?";

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
            long id = rs.getLong("pkIdEstancia");
            LocalDate fecha = rs.getDate("dFecha").toLocalDate();
            boolean vip = rs.getBoolean("bVip");

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

    public ArrayList<Estancia> getEstanciasByParadaAndFecha(long idParada, LocalDate fechaInicio, LocalDate fechaFin) {
        ArrayList<Estancia> estancias = new ArrayList<>();

        String sql = "SELECT * FROM Testancia " +
                "WHERE fkIdParada = ? AND fecha BETWEEN ? AND ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setLong(1, idParada);
            stmt.setDate(2, Date.valueOf(fechaInicio));
            stmt.setDate(3, Date.valueOf(fechaFin));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Estancia estancia = getResultSet(rs);
                    estancias.add(estancia);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener estancias por parada y rango de fechas: " + e.getMessage());
            e.printStackTrace();
        }
        return estancias;
    }
}
