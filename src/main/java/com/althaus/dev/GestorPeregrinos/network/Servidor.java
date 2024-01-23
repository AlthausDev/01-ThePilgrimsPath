package com.althaus.dev.GestorPeregrinos.network;

import java.io.IOException;
import java.net.ServerSocket;

public class Servidor {

    private ServerSocket serverSocket;
    private static int puerto;

    public Servidor(int puerto) {
        try {
            serverSocket = new ServerSocket(puerto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Servidor servidor = new Servidor(puerto);
    }
}
