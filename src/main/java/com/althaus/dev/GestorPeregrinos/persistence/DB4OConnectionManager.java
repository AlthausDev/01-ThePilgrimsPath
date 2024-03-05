package com.althaus.dev.GestorPeregrinos.persistence;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Clase Singleton para gestionar la conexión con la base de datos db4o.
 */
@Component
public class DB4OConnectionManager {

    private static ObjectContainer database;
    private static DB4OConnectionManager INSTANCE;

    @Value("${db4o.datasource.filepath}")
    private String db4oFilePath;

    // Constructor privado para garantizar que la clase no se pueda instanciar directamente
    private DB4OConnectionManager() {
        openConnection();
    }

    // Método estático para crear la instancia única de la clase
    private static synchronized void createInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DB4OConnectionManager();
        }
    }

    // Método para abrir la conexión con la base de datos
    private void openConnection() {
        try {
            database = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), db4oFilePath);
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
