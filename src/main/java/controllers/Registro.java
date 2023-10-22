//package controllers;
//
//import entities.Parada;
//import entities.Peregrino;
//import aplicacion.Sesion;
//
//import java.util.Scanner;
//
//public class Registrar {
//
//    private Scanner sc = new Scanner(System.in);
//    public void registrar() {
//        if (Sesion.perfil == null) {
//            nuevoPeregrino();
//        } else {
//            nuevaParada();
//        }
//    }
//
//
//    public void nuevoPeregrino() {
////        System.out.println("Registrar nuevo usuario");
////        System.out.println("Indique su nombre");
////        String nombre = sc.nextLine();
////
////        System.out.println("Indique una contraseña");
////        String pass = sc.nextLine();
////        do {
////            System.out.println("Repita su contraseña");
////        } while (!sc.nextLine().equals(pass));
//        Controller.mapaUsuarios.put(Controller.count++, new Peregrino());
//    }
//
//    private void nuevaParada() {
//    	FileImputStream file = new FileImputStream();
//        Controller.mapaUsuarios.put(Controller.count++, new Parada());
//
//    }
//}

package controllers;

import entities.Carnet;
import entities.Estancia;
import entities.Parada;
import entities.Peregrino;
import aplicacion.Sesion;

import java.util.ArrayList;
import java.util.Scanner;

public class Registro {

    private Scanner sc = new Scanner(System.in);

    public void registrar() {
        if (Sesion.getPerfil() == null) {
            nuevoPeregrino();
        } else {
            nuevaParada();
        }
    }

    public void nuevoPeregrino() {
        System.out.println("Registrar nuevo usuario - Peregrino");
        System.out.println("Indique su nombre");
        String nombre = sc.nextLine();
        System.out.println("Indique una contraseña");
        String pass = sc.nextLine();

        String nacionalidad = null;

        ArrayList<Parada> paradas = new ArrayList<>(); 
        ArrayList<Estancia> estancias = new ArrayList<>(); 

        Carnet carnet = new Carnet();

        Peregrino nuevoPeregrino = new Peregrino(nombre, nacionalidad);

    }


    private void nuevaParada() {

    }



}

