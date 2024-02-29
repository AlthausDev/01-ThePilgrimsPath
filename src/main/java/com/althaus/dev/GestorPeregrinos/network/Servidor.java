package com.althaus.dev.GestorPeregrinos.network;

import com.althaus.dev.GestorPeregrinos.persistance.Db4oConnectionManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static com.althaus.dev.GestorPeregrinos.util.Constantes.PUERTO;

public class Servidor {

    private ServerSocket serverSocket;

    private Servidor() {
        try {
            serverSocket = new ServerSocket(PUERTO);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al iniciar el servidor");
        }
    }

    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        servidor.esperarCliente();
    }

    public void esperarCliente() {
        while (true) {
            try {
                Socket socketCliente = serverSocket.accept();

                HiloCliente hiloCliente = new HiloCliente(socketCliente);
                new Thread(hiloCliente).start();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error al aceptar al cliente");
            }
        }
    }

    private void closeConnection() {
        try {
            serverSocket.close();
            Db4oConnectionManager.closeConnection();
        } catch (IOException e) {
            throw new RuntimeException("Error al cerrar la conexión", e);
        }
    }
}
