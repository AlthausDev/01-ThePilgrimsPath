package controllers;

import aplicacion.Sesion;
import entities.Carnet;
import entities.Parada;
import entities.Peregrino;

import java.util.ArrayList;
import java.util.Scanner;

import static entities.Perfil.PEREGRINO;
import static io.Escritor.writeCarnet;
import static io.Escritor.writeCredencial;

public class Registro {
    private Scanner sc = new Scanner(System.in);

    private String nombre;
    private String pass;
    private String nacionalidad;
    private Parada paradaInicial;

    private boolean valid  =  false;


    public void registrar() {

    }

    public void nuevoPeregrino() {
        System.out.println("Registrar nuevo usuario - Peregrino");
        System.out.println("Indique su nombre\n");
        nombre = sc.nextLine();
        System.out.println("Indique una contraseña\n");
        pass = sc.nextLine();

        System.out.println("CODIGO - PAIS");
        Sesion.getNacionalidades().forEach((k, v) -> System.out.println(k + " - " + v));

        System.out.println("Inserte el código de su país\n");
        String codNacionalidad = sc.nextLine();
        nacionalidad = Sesion.getNacionalidades().get(codNacionalidad).toUpperCase();

        ArrayList<Parada> paradaActual = new ArrayList<Parada>();
        paradaActual.add(Sesion.getParadaActual());

        Carnet carnet = new Carnet(Sesion.getLastId()+1L, Sesion.getParadaActual());
        Peregrino nuevoPeregrino = new Peregrino(Sesion.getLastId(), nombre, nacionalidad, carnet, paradaActual);

        writeCarnet(nuevoPeregrino);
        writeCredencial(nombre, pass, PEREGRINO, Sesion.getLastId());
    }

    private void nuevaParada() {

    }

}

