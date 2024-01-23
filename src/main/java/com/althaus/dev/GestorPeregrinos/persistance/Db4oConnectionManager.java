package com.althaus.dev.GestorPeregrinos.persistance;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

import static com.althaus.dev.GestorPeregrinos.util.Constantes.PATH_DB4O;

/**
 * Clase Singleton para gestionar la conexión con la base de datos db4o.
 * @author Althaus_Dev
 */
public class Db4oConnectionManager {

    private static ObjectContainer dataBase;
    private static Db4oConnectionManager INSTANCE;


    private Db4oConnectionManager() {
    }

    /**
     * Obtiene la instancia única de la clase y abre la conexión si aún no está abierta.
     *
     * @return Instancia única de Db4oConnectionManager.
     */
    private static synchronized void createInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Db4oConnectionManager();
            openConnection();
        }
    }

    /**
     * Abre la conexión con la base de datos.
     */
    private static void openConnection() {
        dataBase = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), PATH_DB4O);
    }

    /**
     * Cierra la conexión con la base de datos.
     */
    public void closeConnection() {
        if (dataBase != null) {
            dataBase.close();
        }
    }

    /**
     * Obtiene la instancia de la base de datos para realizar operaciones.
     *
     * @return Instancia de la base de datos.
     */
    public static ObjectContainer getInstance() {
        if (INSTANCE == null)
            createInstance();
        return dataBase;
    }
}
