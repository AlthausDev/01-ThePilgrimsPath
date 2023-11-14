package persistencia;

import java.sql.*;

public class MySqlConnector {
    Connection con;

    public void conectar() {

        try {
            // Realiza la conexión
            con = DriverManager.getConnection("jdbc:mysql://localhost/usuariosDB", "root", "root");
            System.out.println("Conexion exitosa");

        } catch (SQLException e) {
            System.out.println("Hay un error en los datos de conexion, usuario o password");
        }
    }
}
