package model.io;

import service.Sesion;
import model.*;
import org.javatuples.Pair;
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

import static util.Constantes.*;

/**
 * Esta clase proporciona métodos para leer información desde archivos y XML, y cargarla en la aplicación.
 * Se utiliza principalmente para cargar credenciales de usuarios, datos de paradas, países y carnets de peregrinos.
 *
 * @author S.Althaus
 */
public class Lector {

    /**
     * Lee las credenciales de los usuarios desde un archivo y las almacena en un HashMap.
     *
     * @return Un HashMap que contiene las credenciales de los usuarios (nombre de usuario, contraseña y perfil).
     */
//    public static HashMap<String, Pair<String, Perfil>> readCredenciales() {
//        // Inicializa un HashMap para almacenar las credenciales.
//        HashMap<String, Pair<String, Perfil>> credenciales = new HashMap<>();
//
//        // Crea un objeto File para el archivo de credenciales.
//        File credentialsFile = new File(PATH_CREDENTIALS);
//
//        try (Scanner scanner = new Scanner(credentialsFile)) {
//            // Lee el archivo línea por línea.
//            while (scanner.hasNextLine()) {
//                String linea = scanner.nextLine();
//                String[] userDataFields = linea.split(" ");
//
//                if (userDataFields.length >= 4) {
//                    // Extrae los campos de datos de la línea.
//                    String name = userDataFields[0];
//                    String storedPass = userDataFields[1];
//                    Perfil perfil = Perfil.valueOf(userDataFields[2]);
//                    long id = Long.parseLong(userDataFields[3]);
//
//                    if (Sesion.getLastId() < id) {
//                        Sesion.setLastId(id);
//                    }
//
//                    // Agrega las credenciales al HashMap.
//                    credenciales.put(name, new Pair<>(storedPass, perfil));
//                }
//            }
//        } catch (FileNotFoundException e) {
//            System.err.println("No se encuentra el archivo de credenciales");
//        }
//        return credenciales;
//    }

    /**
     * Lee datos de paradas desde un archivo binario y los almacena en un HashMap.
     *
     * @return Un HashMap que contiene las paradas identificadas por su ID.
     */
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

    /**
     * Este método lee datos de un archivo XML que contiene información de un carnet de peregrino, como el ID, la fecha de expedición,
     * el lugar de expedición, el nombre del peregrino, la nacionalidad, la fecha actual, la distancia total, paradas y estancias.
     * Luego, crea un objeto Peregrino y configura un carnet, paradas y estancias basado en la información del archivo XML.
     *
     * @param name El nombre del peregrino cuyo carnet se va a cargar desde el archivo XML.
     * @return Un objeto Peregrino que contiene información del carnet, paradas y estancias del peregrino.
     */
    public static Peregrino readCarnet(String name) {
        // Crea objetos para almacenar la información del carnet, paradas y estancias.
        Peregrino peregrino = new Peregrino();
        Carnet carnet = new Carnet();
        ArrayList<Parada> paradas = new ArrayList<>();
        ArrayList<Estancia> estancias = new ArrayList<>();

        try {
            // Crea un archivo XML para leer los datos del carnet.
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
            NodeList paradasNodeList = root.getElementsByTagName("paradas");
            for (int i = 0; i < paradasNodeList.getLength(); i++) {
                Element paradaElement = (Element) paradasNodeList.item(i);
                long orden = Long.parseLong(getTagValue(paradaElement, "orden"));
                String paradaNombre = getTagValue(paradaElement, "nombre");
                char region = getTagValue(paradaElement, "region").charAt(0);

                // Crea el objeto Parada y agrégalo a la lista de paradas
                Parada parada = new Parada(orden, paradaNombre, region, null);
                paradas.add(parada);
            }

//            // Elementos de <estancias>
//            if (root.getElementsByTagName("estancias").getLength() > 0) {
//                NodeList estanciasNodeList = root.getElementsByTagName("estancias");
//                for (int i = 0; i < estanciasNodeList.getLength(); i++) {
//                    Element estanciaElement = (Element) estanciasNodeList.item(i);
//                    String estanciaId = getTagValue(estanciaElement, "id");
//                    String estanciaFecha = getTagValue(estanciaElement, "fecha");
//                    String estanciaParada = getTagValue(estanciaElement, "parada");
//                    String vip = getTagValue(estanciaElement, "vip");
//                    // Crea el objeto Estancia y agrégalo a la lista de estancias
//                    Estancia estancia = new Estancia(Long.parseLong(estanciaId), LocalDate.parse(estanciaFecha, DATE_TIME_FORMATTER), Boolean.parseBoolean(vip), paradas.get(i));
//                    estancias.add(estancia);
//                }
//            }

            // Configura el objeto Carnet
            carnet.setIdPeregrino(Long.parseLong(id));
            carnet.setFechaExp(LocalDate.parse(fechaExp, DATE_TIME_FORMATTER));
            carnet.setParadaInicial(paradas.get(0));
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

    /**
     * Obtiene el valor de una etiqueta (tag) de un elemento XML.
     *
     * @param element El elemento XML del que se extraerá el valor.
     * @param tagName El nombre de la etiqueta de la que se extraerá el valor.
     * @return El valor contenido en la etiqueta especificada.
     */
    private static String getTagValue(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return null;
    }

    /**
     * Este método lee datos de países desde un archivo XML y los almacena en un HashMap.
     *
     * @return Un HashMap que contiene los países identificados por su ID y su nombre.
     * @throws RuntimeException Si ocurren errores de lectura o parseo del archivo XML.
     */
    public static HashMap<String, String> readPaises() {
        // Inicializa un HashMap para almacenar los países.
        HashMap<String, String> paises = new HashMap<>();
        File xmlFile = new File(PATH_COUNTRIES);

        try {
            // Configura el parser para procesar el archivo XML.
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            // Normaliza el documento para asegurarse de que esté correctamente estructurado.
            doc.getDocumentElement().normalize();

            // Obtiene la lista de nodos "pais" del documento.
            NodeList nodeList = doc.getElementsByTagName("pais");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    // Obtiene el ID y el nombre del país.
                    String id = element.getElementsByTagName("id").item(0).getTextContent();
                    String nombre = element.getElementsByTagName("nombre").item(0).getTextContent();

                    // Almacena el país en el HashMap con el ID como clave y el nombre como valor.
                    paises.put(id, nombre);
                }
            }

            return paises;

        } catch (ParserConfigurationException | SAXException | IOException e) {
            // Maneja cualquier error que pueda ocurrir durante la lectura o el parseo del archivo.
            throw new RuntimeException(e);
        }
    }

}

