package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionJDBC {

    static final String URL = "jdbc:mysql://localhost:3306/bdperegrinos_samuelAlthaus";
    static final String USUARIO = "user";
    static final String CONTRASENA = "password";

    public static void main(String[] args) {
        Connection conexion = null;      

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);

            System.out.println("Conexión exitosa.");
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el controlador JDBC: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error en la conexión a la base de datos: " + e.getMessage());
        } finally {
            
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
