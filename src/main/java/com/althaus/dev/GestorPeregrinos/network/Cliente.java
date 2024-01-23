package com.althaus.dev.GestorPeregrinos.network;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import static com.althaus.dev.GestorPeregrinos.util.Constantes.PUERTO;


/**
 * Clase del cliente para conectarse al servidor.
 * @author Althaus_Dev
 */
public class Cliente {

    private Socket socket;
    private ObjectInputStream inputStream;
    private DataOutputStream outputStream;


    private Cliente(String servidor) {
        try {
            socket = new Socket(servidor, PUERTO);

            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al conectar con el servidor: " + e.getMessage());

        }
    }

    public static void main(String[] args) {
        Cliente cliente = new Cliente("localhost");
        cliente.cerrarConexion();
    }


    public void cerrarConexion(){
        try {
            outputStream.close();
            inputStream.close();
            socket.close();

            System.out.println("Se ha cerrado la conexión");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}