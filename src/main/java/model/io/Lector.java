package model.io;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static util.Constantes.PATH_COUNTRIES;

/**
 * Esta clase proporciona métodos para leer información desde archivos y XML, y cargarla en la aplicación.
 * Se utiliza principalmente para cargar credenciales de usuarios, datos de paradas, países y carnets de peregrinos.
 *
 * @author S.Althaus
 */
public class Lector {

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
