package com.althaus.dev.GestorPeregrinos.network;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Cliente {

    private static Socket socket;
    private ObjectInputStream inputStream;
    private DataOutputStream outputStream;
    private static int puerto;

    public Cliente(String servidor, int puerto) {
        try {
            socket = new Socket(servidor, puerto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Cliente cliente = new Cliente("localhost", puerto);
    }
}
