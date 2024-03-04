package com.althaus.dev.GestorPeregrinos.persistance;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static com.althaus.dev.GestorPeregrinos.util.Constantes.PATH_DB4O;

/**
 * Clase Singleton para gestionar la conexión con la base de datos db4o.
 *
 * @author Althaus_Dev
 */
@Configuration
@Profile("db4o")
public class DB4OConnectionManager {

    private static ObjectContainer database;
    private static DB4OConnectionManager INSTANCE;

    // Constructor privado para garantizar que la clase no se pueda instanciar directamente
    public DB4OConnectionManager() {
        openConnection();
    }

    // Método estático para crear la instancia única de la clase
    private static synchronized void createInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DB4OConnectionManager();
        }
    }

    // Método para abrir la conexión con la base de datos
    private static void openConnection() {
        try {
            database = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), PATH_DB4O);
        } catch (Exception e) {
            System.err.println("Error al abrir la conexión con la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método estático para cerrar la conexión con la base de datos
    public static void closeConnection() {
        if (database != null) {
            database.close();
        }
    }

    // Método estático para obtener la instancia de la base de datos
    public static ObjectContainer getInstance() {
        if (INSTANCE == null) {
            createInstance();
        }
        return database;
    }
}

