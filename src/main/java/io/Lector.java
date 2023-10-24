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
import java.time.format.DateTimeFormatter;
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
        Peregrino peregrino = new Peregrino();
        Carnet carnet = new Carnet();
        ArrayList<Parada> paradas = new ArrayList<>();
        ArrayList<Estancia> estancias = new ArrayList<>();

        try {
            File xmlFile = new File(PATH_EXPORTS + name + ".xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            Element root = doc.getDocumentElement();

            // Elementos de <carnet>
            String id = getTagValue(root, "id");
            String fechaExp = getTagValue(root, "fechaexp");
            String expedidoen = getTagValue(root, "expedidoen");

            // Elemento <peregrino>
            Element peregrinoElement = (Element) root.getElementsByTagName("peregrino").item(0);
            String nombre = getTagValue(peregrinoElement, "nombre");
            String nacionalidad = getTagValue(peregrinoElement, "nacionalidad");

            // Elementos restantes
            String hoy = getTagValue(root, "hoy");
            String distanciaTotal = getTagValue(root, "distanciatotal");

            // Recorrer las paradas
            NodeList paradasNodeList = root.getElementsByTagName("parada");
            for (int i = 0; i < paradasNodeList.getLength(); i++) {
                Element paradaElement = (Element) paradasNodeList.item(i);
                String orden = getTagValue(paradaElement, "orden");
                String paradaNombre = getTagValue(paradaElement, "nombre");
                String region = getTagValue(paradaElement, "region");

                // Crea el objeto Parada y agrégalo a la lista de paradas
                Parada parada = new Parada(Long.parseLong(orden), paradaNombre, region.charAt(0), null);
                paradas.add(parada);
            }

            // Elementos de <estancias>
            if (root.getElementsByTagName("estancia").getLength() > 0) {
                NodeList estanciasNodeList = root.getElementsByTagName("estancia");
                for (int i = 0; i < estanciasNodeList.getLength(); i++) {
                    Element estanciaElement = (Element) estanciasNodeList.item(i);
                    String estanciaId = getTagValue(estanciaElement, "id");
                    String estanciaFecha = getTagValue(estanciaElement, "fecha");
                    String estanciaParada = getTagValue(estanciaElement, "parada");
                    String vip = getTagValue(estanciaElement, "vip");
                    // Crea el objeto Estancia y agrégalo a la lista de estancias
                    Estancia estancia = new Estancia(Long.parseLong(estanciaId), LocalDate.parse(estanciaFecha, DateTimeFormatter.ofPattern("dd-MM-yyyy")), Boolean.parseBoolean(vip), paradas.get(i));
                    estancias.add(estancia);
                }
            }

            // Configura el objeto Carnet
            carnet.setIdPeregrino(Long.parseLong(id));
            carnet.setFechaExp(LocalDate.parse(fechaExp, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            carnet.setParadaInicial(paradas.get(0));
            /**
             * carnet.setDistancia(Double.parseDouble(distanciaTotal));
             * Intentar parsear un String en Double, genera un NumberFormatException
             * Pendiente de revisión futura para ver cómo se puede abordar el problema.
             */
            carnet.setNvips(estancias.size());

            // Configura el objeto Peregrino
            peregrino.setId(carnet.getIdPeregrino());
            peregrino.setNombre(nombre);
            peregrino.setNacionalidad(nacionalidad);
            peregrino.setCarnet(carnet);
            peregrino.setParadas(paradas);
            peregrino.setEstancias(estancias);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return peregrino;
    }

    private static String getTagValue(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return "";
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
