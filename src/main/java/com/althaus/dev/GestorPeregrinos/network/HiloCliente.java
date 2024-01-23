package com.althaus.dev.GestorPeregrinos.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HiloCliente implements Runnable {

    private final Socket clienteSocket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public HiloCliente(Socket clienteSocket) {
        this.clienteSocket = clienteSocket;

        try {
            outputStream = new ObjectOutputStream(clienteSocket.getOutputStream());
            inputStream = new ObjectInputStream(clienteSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al inicializar flujos del hilo del cliente");
        }
    }

    @Override
    public void run() {
        try{
            //TODO pendiente de manejor datos
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            cerrarConnection();
        }
    }

    private void cerrarConnection(){
        try{
            outputStream.close();
            inputStream.close();
            clienteSocket.close();

            System.out.println("Cerrado hilo del cliente y conexión con el cliente");
        } catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException("Error al cerrar la conexión del hilo del cliente", e);
        }
    }
}
