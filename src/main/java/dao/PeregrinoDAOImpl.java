package dao;

import model.Carnet;
import model.Estancia;
import model.Parada;
import model.Peregrino;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class PeregrinoDAOImpl extends CoreDAO<Peregrino> {

    private CarnetDAOImpl carnetDAO;
    private ParadaDAOImpl paradaDAO;
    private EstanciaDAOImpl estanciaDAO;

    public PeregrinoDAOImpl() {
        super();
        this.carnetDAO = new CarnetDAOImpl();
        this.paradaDAO = new ParadaDAOImpl();
        this.estanciaDAO = new EstanciaDAOImpl();
    }
    @Override
    public void create(Peregrino peregrino) {
        String sql = "INSERT INTO Peregrino (id, nombre, nacionalidad, idCarnet) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, peregrino.getId());
            stmt.setString(2, peregrino.getNombre());
            stmt.setString(3, peregrino.getNacionalidad());
            stmt.setLong(4, peregrino.getCarnet().getIdPeregrino());

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas == 0) {
                System.err.println("Error al crear Peregrino, no hay filas afectadas");
            }

            try (ResultSet clavesGeneradas = stmt.getGeneratedKeys()) {
                if (clavesGeneradas.next()) {
                    peregrino.setId(clavesGeneradas.getLong(1));
                } else {
                    System.err.println("Error al crear Peregrino, no se obtuvo el ID");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta de inserción: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Peregrino read(long id) {
        Peregrino peregrino = null;
        String sql = "SELECT * FROM Peregrino WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    peregrino = getResultSet(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener Peregrino por ID: " + e.getMessage());
            e.printStackTrace();
        }
        return peregrino;
    }

    @Override
    public HashMap<Long, Peregrino> readAll() {
        HashMap<Long, Peregrino> peregrinos = new HashMap<>();
        String sql = "SELECT * FROM Peregrino";

        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Peregrino peregrino = getResultSet(rs);
                peregrinos.put(peregrino.getId(), peregrino);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener todos los Peregrinos: " + e.getMessage());
            e.printStackTrace();
        }

        return peregrinos;
    }


    @Override
    public void update(Peregrino peregrino) {
        String sql = "UPDATE Peregrino SET nombre = ?, nacionalidad = ?, idCarnet = ? WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, peregrino.getNombre());
            stmt.setString(2, peregrino.getNacionalidad());
            stmt.setLong(3, peregrino.getCarnet().getIdPeregrino());
            stmt.setLong(4, peregrino.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar Peregrino: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM Peregrino WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar Peregrino: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected Peregrino getResultSet(ResultSet rs) {
        try {
            long id = rs.getLong("id");
            String nombre = rs.getString("nombre");
            String nacionalidad = rs.getString("nacionalidad");

            Carnet carnet = carnetDAO.getResultSet(rs);
            ArrayList<Parada> paradas = paradaDAO.getListResultSet(rs);
            ArrayList<Estancia> estancias = estanciaDAO.getListResultSet(rs);


            return new Peregrino(id, nombre, nacionalidad, carnet, paradas, estancias);

        } catch (SQLException e) {
            System.err.println("Error al mapear ResultSet a Peregrino: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

}
