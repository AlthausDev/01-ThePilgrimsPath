package com.althaus.dev.GestorPeregrinos.util.io;

import com.althaus.dev.GestorPeregrinos.model.*;
import com.althaus.dev.GestorPeregrinos.service.CarnetService;
import com.althaus.dev.GestorPeregrinos.service.PeregrinoService;
import com.althaus.dev.GestorPeregrinos.service.impl.CarnetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.althaus.dev.GestorPeregrinos.util.Constantes.DATE_TIME_FORMATTER;
import static com.althaus.dev.GestorPeregrinos.util.Constantes.PATH_EXPORTS;


/**
 * Clase que proporciona métodos para escribir datos en diferentes formatos y almacenarlos en archivos.
 * Incluye métodos para escribir credenciales, paradas y carnet de peregrinos, así como exportar datos a archivos XML.
 *
 * @author S.Althaus
 */

public class XMLWriter {


    public XMLWriter() {
    }

    /**
     * Exporta los datos del carnet de un peregrino a un archivo XML.
     */
    public void exportarCarnet(Peregrino peregrino, Carnet carnet) {
        try {

                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                DOMImplementation implementation = dBuilder.getDOMImplementation();
                Document doc = implementation.createDocument(null, "Carnet", null);

                Element root = doc.getDocumentElement();
                newElementXML("id", String.valueOf(peregrino.getId()), root, doc);
                newElementXML("fechaexp", carnet.getFechaExp().toString(), root, doc);
                newElementXML("expedidoen", carnet.getParadaInicial().getNombre(), root, doc);

                createPeregrinoElement(doc, root, peregrino);
                createParadasElement(doc, root, peregrino);
                createEstanciasElement(doc, root, peregrino);

                createPeregrinoElement(doc, root, peregrino);
                newElementXML("hoy", LocalDate.now().toString(), root, doc);
                newElementXML("distanciaTotal", String.valueOf(carnet.getDistancia()), root, doc);

                createParadasElement(doc, root, peregrino);
                createEstanciasElement(doc, root, peregrino);

                Source src = new DOMSource(doc);
                File fileXML = new File(PATH_EXPORTS + peregrino.getName() + ".xml");
                StreamResult rslt = new StreamResult(fileXML);

                try {
                    Transformer transformer = TransformerFactory.newInstance().newTransformer();
                    transformer.transform(src, rslt);
                    System.out.println("Fichero exportado con éxito");
                } catch (TransformerException e) {
                    e.printStackTrace();
                }


            System.out.println("Fichero exportado con éxito");
        } catch (ParserConfigurationException e) {
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
        newElementXML("nombre", pilgrim.getName(), peregrino, doc);
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

        List<Parada> listaParadas = pilgrim.getParadas();
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

            List<Estancia> listaEstancias = pilgrim.getEstancias();
            for (Estancia stay : listaEstancias) {
                Element estancia = doc.createElement("estancia");
                estancias.appendChild(estancia);
                newElementXML("id", String.valueOf(stay.getId()), estancia, doc);
                newElementXML("fecha", stay.getFecha().format(DATE_TIME_FORMATTER), estancia, doc);
                newElementXML("parada", stay.getParada().getNombre(), estancia, doc);
                newElementXML("vip", String.valueOf(stay.getVip()), estancia, doc);
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
