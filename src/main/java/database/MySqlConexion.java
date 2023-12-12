package database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySqlConexion {

    private static MySqlConexion instancia;
    private Connection conexion;

    private MySqlConexion() {
        Properties properties = new Properties();
        InputStream is = MySqlConexion.class.getClassLoader().getResourceAsStream("database.properties");

        try {
            properties.load(is);

            String url = properties.getProperty("database.URL");
            String user = properties.getProperty("database.USUARIO");
            String pass = properties.getProperty("database.PASSWORD");

            conexion = DriverManager.getConnection(url, user, pass);

        } catch (IOException e) {
            System.err.println("Error al cargar el fichero de propiedades: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("No se puede establecer la conexión: " + e.getMessage());
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar el InputStream: " + e.getMessage());
            }
        }
    }

    protected static MySqlConexion getInstance() {
        if (instancia == null) {
            instancia = new MySqlConexion();
        }
        return instancia;
    }

    protected Connection getConexion(){
        return conexion;
    }
}
