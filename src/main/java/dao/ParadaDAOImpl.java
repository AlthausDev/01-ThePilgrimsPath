package dao;

import model.AdminParada;
import model.Parada;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class ParadaDAOImpl extends CoreDAO<Parada> {


    @Override
    public void create(Parada parada) {
        String sql = "INSERT INTO Tparada (cNombrePar, cRegion, fkIdAdminParada) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, parada.getNombre());
            stmt.setString(2, String.valueOf(parada.getRegion()));
            stmt.setLong(3, parada.getAdminParada().getId());

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas == 0) {
                System.err.println("Error al crear la Parada, no hay filas afectadas");
            }

            try (ResultSet clavesGeneradas = stmt.getGeneratedKeys()) {
                if (clavesGeneradas.next()) {
                    parada.setId(clavesGeneradas.getLong(1));
                } else {
                    System.err.println("Error al crear la Parada, no se obtuvo el ID");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta de inserción: " + e.getMessage());
            e.printStackTrace();
        }
    }



    @Override
    public Parada read(long id) {
        Parada parada = null;
        String sql = "SELECT * FROM Tparada WHERE pkfkIdParada = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    parada = getResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parada;
    }

    @Override
    public HashMap<Long, Parada> readAll() {

        HashMap<Long, Parada> paradas = new HashMap<>();
        String sql = "Select * FROM Tparada";

        try {
            PreparedStatement stmt = conexion.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Parada parada = getResultSet(rs);
                paradas.put(parada.getId(), parada);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return paradas;
    }

    @Override
    public void update(Parada parada) {
        String sql = "UPDATE Tparada SET cNombre = ?, cRegion = ?, fkIdAdminParada = ? WHERE pkfkIdParada = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, parada.getNombre());
            stmt.setString(2, String.valueOf(parada.getRegion()));
            stmt.setLong(3, parada.getAdminParada().getId());
            stmt.setLong(4, parada.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM Tparada WHERE pkfkIdParada = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected Parada getResultSet(ResultSet rs) {

        try {
            long id = rs.getLong("pkfkIdParada");
            String nombre = rs.getString("cNombre");
            char region = rs.getString("cRegion").charAt(0);

            long adminParadaId = rs.getLong("fkIdAdminParada");

            AdminParadaDAOImpl adminParadaDAO = new AdminParadaDAOImpl();
            AdminParada adminParada = adminParadaDAO.read(adminParadaId);

            return new Parada(id, nombre, region, adminParada);

        } catch (SQLException e) {
            System.err.println("Error al obtener la parada: " + e.getMessage());
        }
        return null;
    }


    protected ArrayList<Parada> getListResultSet(ResultSet rs) {
        ArrayList<Parada> paradas = new ArrayList<>();

        try {
            while (rs.next()) {
                Parada parada =getResultSet(rs);
                paradas.add(parada);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener paradas desde ResultSet: " + e.getMessage());
            e.printStackTrace();
        }
        return paradas;
    }

    public int count() {
        String sql = "SELECT COUNT(*) FROM TParada";

        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al contar las paradas", e);
        }
        return 0;
    }

    public Parada readByAdminId(long adminParadaId) {
        Parada parada = null;
        String sql = "SELECT * FROM Tparada WHERE fkIdAdminParada = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setLong(1, adminParadaId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    parada = getResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parada;
    }
}