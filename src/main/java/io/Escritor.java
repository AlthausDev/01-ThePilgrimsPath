package io;

import entities.Estancia;
import entities.Parada;
import entities.Peregrino;
import entities.Perfil;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

import static utilidades.Constantes.*;

/**
 * Clase que proporciona métodos para escribir datos en diferentes formatos y almacenarlos en archivos.
 * Incluye métodos para escribir credenciales, paradas y carnet de peregrinos, así como exportar datos a archivos XML.
 *
 * @author S.Althaus
 */
public class Escritor {

    /**
     * Escribe las credenciales de un usuario en el archivo de credenciales.
     *
     * @param nombre El nombre de usuario.
     * @param pass   La contraseña del usuario.
     * @param perfil El perfil del usuario.
     * @param lastId El último ID utilizado.
     */
    public static void writeCredencial(String nombre, String pass, Perfil perfil, long lastId) {
        String str = nombre + " " + pass + " " + perfil + " " + lastId + "\n";

        try (FileWriter fileWriter = new FileWriter(PATH_CREDENTIALS, true)) {
            fileWriter.write(str);
            System.out.println("Contenido agregado al archivo de credenciales.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Escribe los datos de una parada en el archivo de paradas.
     *
     * @param parada La parada a escribir.
     */
//    public static void writeParada(Parada parada) {
//        try {
//            FileOutputStream fos = new FileOutputStream(PATH_STOPS, true);
//            AppendableObjectOutputStream oos = new AppendableObjectOutputStream(fos, true);
//            oos.writeObject(parada);
//
//            System.out.println("Se ha agregado una nueva parada con éxito.");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public static void writeParada(Parada parada) {
        try {
            ObjectOutputStream oos;
            File file = new File(PATH_STOPS);

            if (file.exists()) {
                // Si el archivo existe, abre un FileOutputStream en modo de anexado
                FileOutputStream fos = new FileOutputStream(PATH_STOPS, true);
                oos = new ObjectOutputStream(fos) {
                    protected void writeStreamHeader() throws IOException {
                        reset();
                    }
                };
            } else {
                // Si el archivo no existe, crea un nuevo archivo
                FileOutputStream fos = new FileOutputStream(PATH_STOPS);
                oos = new ObjectOutputStream(fos);
            }

            oos.writeObject(parada);
            oos.close();

            System.out.println("Se ha agregado una nueva parada con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Exporta los datos del carnet de un peregrino a un archivo XML.
     *
     * @param pilgrim El peregrino cuyo carnet se exportará.
     */
    public static void writeCarnet(Peregrino pilgrim) {
        try {
            // Crear una fábrica de documentos XML.
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            // Crear un generador de documentos XML.
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            // Obtener la implementación de DOM.
            DOMImplementation implementation = dBuilder.getDOMImplementation();
            // Crear un nuevo documento XML.
            Document doc = implementation.createDocument(PATH_EXPORTS, "Carnet", null);

            // Obtener el elemento raíz del documento XML.
            Element root = doc.getDocumentElement();
            // Agregar elementos con datos del carnet del peregrino.
            newElementXML("id", String.valueOf(pilgrim.getId()), root, doc);
            newElementXML("fechaexp", pilgrim.getCarnet().getFechaExp().format(DATE_TIME_FORMATTER), root, doc);
            newElementXML("expedidoen", pilgrim.getCarnet().getParadaInicial().getNombre(), root, doc);

            // Agregar elementos relacionados con el peregrino.
            createPeregrinoElement(doc, root, pilgrim);
            newElementXML("hoy", LocalDate.now().format(DATE_TIME_FORMATTER), root, doc);
            newElementXML("distanciaTotal", String.valueOf(pilgrim.getCarnet().getDistancia()), root, doc);

            // Agregar elementos relacionados con las paradas del peregrino.
            createParadasElement(doc, root, pilgrim);

            // Agregar elementos relacionados con las estancias del peregrino.
            createEstanciasElement(doc, root, pilgrim);

            // Crear una fuente para el documento XML.
            Source src = new DOMSource(doc);
            // Crear un archivo XML en base al nombre del peregrino.
            File fileXML = new File(PATH_EXPORTS + pilgrim.getNombre() + ".xml");
            // Crear un resultado para la transformación.
            Result rslt = new StreamResult(fileXML);
            // Crear un transformador para realizar la exportación.
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            // Realizar la transformación y escribir el archivo XML.
            transformer.transform(src, rslt);

            System.out.println("Fichero exportado con éxito");
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Crea elementos XML relacionados con el peregrino y los agrega al documento.
     *
     * @param doc     El documento XML al que se agregarán los elementos.
     * @param root    El elemento raíz del documento.
     * @param pilgrim El peregrino cuyos datos se exportarán.
     */
    private static void createPeregrinoElement(Document doc, Element root, Peregrino pilgrim) {
        Element peregrino = doc.createElement("peregrino");
        root.appendChild(peregrino);
        newElementXML("nombre", pilgrim.getNombre(), peregrino, doc);
        newElementXML("nacionalidad", pilgrim.getNacionalidad(), peregrino, doc);
    }

    /**
     * Crea elementos XML relacionados con las paradas del peregrino y los agrega al documento.
     *
     * @param doc     El documento XML al que se agregarán los elementos.
     * @param root    El elemento raíz del documento.
     * @param pilgrim El peregrino cuyas paradas se exportarán.
     */
    private static void createParadasElement(Document doc, Element root, Peregrino pilgrim) {
        Element paradas = doc.createElement("paradas");
        root.appendChild(paradas);

        ArrayList<Parada> listaParadas = pilgrim.getParadas();
        int clock = 1;
        for (Parada stand : listaParadas) {
            Element parada = doc.createElement("parada");
            paradas.appendChild(parada);
            newElementXML("orden", String.valueOf(clock++), parada, doc);
            newElementXML("nombre", stand.getNombre(), parada, doc);
            newElementXML("region", String.valueOf(stand.getRegion()), parada, doc);
        }
    }

    /**
     * Crea elementos XML relacionados con las estancias del peregrino y los agrega al documento.
     * Si no existe ninguna estancia asociada, se añaden campos vacíos
     *
     * @param doc     El documento XML al que se agregarán los elementos.
     * @param root    El elemento raíz del documento.
     * @param pilgrim El peregrino cuyas estancias se exportarán.
     */
    private static void createEstanciasElement(Document doc, Element root, Peregrino pilgrim) {

        Element estancias = doc.createElement("estancias");
        root.appendChild(estancias);

        if (pilgrim.getEstancias() != null) {

            ArrayList<Estancia> listaEstancias = pilgrim.getEstancias();
            for (Estancia stay : listaEstancias) {
                Element estancia = doc.createElement("estancia");
                estancias.appendChild(estancia);
                newElementXML("id", String.valueOf(stay.getId()), estancia, doc);
                newElementXML("fecha", stay.getFecha().format(DATE_TIME_FORMATTER), estancia, doc);
                newElementXML("parada", stay.getParada().getNombre(), estancia, doc);
                newElementXML("vip", String.valueOf(stay.isVIP()), estancia, doc);
            }

        } else {
            Element estancia = doc.createElement("estancia");
            estancias.appendChild(estancia);
            newElementXML("id", "", estancia ,doc);
            newElementXML("fecha", "", estancia, doc);
            newElementXML("parada", "", estancia, doc);
            newElementXML("vip", "", estancia, doc);
        }
    }

    /**
     * Crea un nuevo elemento XML con una etiqueta y un valor dados y lo agrega al documento.
     *
     * @param tag   La etiqueta del elemento.
     * @param value El valor del elemento.
     * @param root  El elemento raíz del documento.
     * @param doc   El documento XML al que se agregará el nuevo elemento.
     */
    private static void newElementXML(String tag, String value, Element root, Document doc) {
        Element element = doc.createElement(tag);
        Text text = doc.createTextNode(value);
        element.appendChild(text);
        root.appendChild(element);
    }
}
