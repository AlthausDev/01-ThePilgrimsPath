package io;

import aplicacion.Sesion;
import entities.Carnet;
import entities.Estancia;
import entities.Parada;
import entities.Peregrino;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static utilidades.Constantes.*;

public class Lector {


    public static HashMap<String, String> readCredenciales() {
        HashMap<String, String> credenciales = new HashMap<>();

        File credentialsFile = new File(PATH_CREDENTIALS);

        try (Scanner scanner = new Scanner(credentialsFile)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] userDataFields = linea.split(" ");

                if (userDataFields.length >= 4) {
                    String name = userDataFields[0];
                    String storedPass = userDataFields[1];
                    long id = Long.parseLong(userDataFields[3]);

                    if (Sesion.getLastId() < id) {
                        Sesion.setLastId(id);
                    }
                    credenciales.put(name, storedPass);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("No se encuentra el archivo de credenciales");
        } finally {
            return credenciales;
        }
    }

    public static HashMap<Long, Parada> readParadas() {
        HashMap<Long, Parada> paradas = new HashMap<>();
        Parada parada;

        try {
            File archivo = new File(PATH_STOPS);
            FileInputStream fis = new FileInputStream(archivo);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true) {
                try {
                    parada = (Parada) ois.readObject();
                } catch (EOFException e) {
                   break;
                }
                paradas.put(parada.getId(), parada);
            }
            fis.close();
            ois.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el archivo");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return paradas;
    }

    public static Peregrino readCarnet(String name) {

        int contVip = 0;
        Peregrino peregrino = new Peregrino();
        Carnet carnet;
        ArrayList<Parada> paradas = new ArrayList<>();
        ArrayList<Estancia> estancias = new ArrayList<>();

        File xmlFile = new File(PATH_EXPORTS + name + ".xml");

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();
            Element rootElement = doc.getDocumentElement();

            Element peregrinoElement = (Element) rootElement.getElementsByTagName("peregrino").item(0);

            Long id = Long.parseLong(getTagValue(peregrinoElement, "id"));
            LocalDate fechaexp = LocalDate.parse(getTagValue(peregrinoElement, "fechaexp"));
            String expedidoen = getTagValue(peregrinoElement, "expedidoen");
            String nombre = getTagValue(peregrinoElement, "nombre");
            String nacionalidad = getTagValue(peregrinoElement, "nacionalidad");
            String hoy = getTagValue(peregrinoElement, "hoy");
            double distanciaTotal = Double.parseDouble(getTagValue(peregrinoElement, "distanciaTotal"));

            NodeList paradasNodeList = rootElement.getElementsByTagName("parada");
            for (int i = 0; i < paradasNodeList.getLength(); i++) {
                Element paradaElement = (Element) paradasNodeList.item(i);

                int orden = Integer.parseInt(getTagValue(paradaElement, "orden"));
                String paradaNombre = getTagValue(paradaElement, "nombre");
                int region = Integer.parseInt(getTagValue(paradaElement, "region"));

                //Parada parada = new Parada(orden, paradaNombre, region);
                //paradas.add(parada);
            }

            NodeList estanciasNodeList = rootElement.getElementsByTagName("estancia");
            for (int i = 0; i < estanciasNodeList.getLength(); i++) {

                Element estanciaElement = (Element) estanciasNodeList.item(i);
                int estanciaId = Integer.parseInt(getTagValue(estanciaElement, "id"));
                LocalDate estanciaFecha = LocalDate.parse(getTagValue(estanciaElement, "fecha"));
                String estanciaParada = getTagValue(estanciaElement, "parada");
                boolean vip = Boolean.parseBoolean(getTagValue(estanciaElement, "vip"));
                //Estancia estancia = new Estancia(estanciaId, estanciaFecha, estanciaParada, vip);
                //estancias.add(estancia);
            }

            carnet = new Carnet(id, fechaexp, paradas.get(0), distanciaTotal, contVip);
            //peregrino = new Peregrino(id, nombre, nacionalidad, hoy, carnet, paradas, estancias);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }

        return peregrino;
    }

    private static String getTagValue(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            Node node = nodeList.item(0);
            return node.getTextContent();
        }
        return null;
    }


    public static HashMap<String, String> readPaises() {

        HashMap<String, String> paises = new HashMap<>();
        File xmlFile = new File(PATH_COUNTRIES);


        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("pais");

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    String id = element.getElementsByTagName("id").item(0).getTextContent();
                    String nombre = element.getElementsByTagName("nombre").item(0).getTextContent();
                    paises.put(id, nombre);
                }
            }

            return paises;

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
