package database;

import java.sql.Connection;

public class FactoryConexion {
    private final MySqlConexion mySqlConexion;
    private static final FactoryConexion INSTANCIA = new FactoryConexion();


    public FactoryConexion() {
        mySqlConexion = MySqlConexion.getInstance();
    }

    public static FactoryConexion getInstance(){
        return INSTANCIA;
    }

    public Connection getConexion() {
        try {
            return mySqlConexion.getConexion();
        } catch (Exception e) {
            System.err.println("Error al obtener la conexión: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
