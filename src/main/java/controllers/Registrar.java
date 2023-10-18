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
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Registrar {

    private Scanner sc = new Scanner(System.in);

    public void registrar() {
        if (Sesion.perfil == null) {
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

        String nacionalidad = obtenerNacionalidad(); 

        ArrayList<Parada> paradas = new ArrayList<>(); 
        ArrayList<Estancia> estancias = new ArrayList<>(); 

        Carnet carnet = new Carnet();

        Peregrino nuevoPeregrino = new Peregrino(nombre, pass, Sesion.perfil, 0, nacionalidad, carnet, paradas, estancias);
        Controller.mapaUsuarios.put(Controller.count++, nuevoPeregrino);
    }



    private String seleccionarNacionalidad() {
        
        return "España";
    }

    private String seleccionarParadaInicial() {
        
        return "ParadaA"; 
    }

    private void nuevaParada() {

    }

    public String obtenerNacionalidad() {
        String nacionalidad = "Desconocido"; 

        try {
            File xmlFile = new File("src/main/resources/paises.xml"); 

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("pais");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return nacionalidad;
    }

}

