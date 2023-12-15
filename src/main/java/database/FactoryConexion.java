package database;

import java.sql.Connection;
import java.sql.SQLException;

public class FactoryConexion {
    private final MySqlConexion mySqlConexion;
    private static final FactoryConexion INSTANCIA = new FactoryConexion();


    private FactoryConexion() {
        mySqlConexion = MySqlConexion.getInstance();
    }

    public static FactoryConexion getInstance() {
        return INSTANCIA;
    }

    public Connection getConexion() throws SQLException {
        return mySqlConexion.getConexion();
    }
}