package io;

import entities.Estancia;
import entities.Parada;
import entities.Peregrino;
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
import java.util.ArrayList;

import static utilidades.Constantes.DATE_FORMAT;
import static utilidades.Constantes.PATH_EXPORTS;

public class Escritor {

    public static void  writeCredencial(Peregrino pilgrim){



    }

    public static void writeParada (Parada parada){

    }

    public static void writeCarnet(Peregrino pilgrim) {



        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            DOMImplementation implementation = dBuilder.getDOMImplementation();
            Document doc = implementation.createDocument(PATH_EXPORTS, "Carnet", null);
            Element root = doc.getDocumentElement();

            createPeregrinoElement(doc, root, pilgrim);
            createParadasElement(doc, root, pilgrim);
            createEstanciasElement(doc, root, pilgrim);

            Source src = new DOMSource(doc);
            File fileXML =  new File(PATH_EXPORTS + pilgrim.getNombre() + ".xml");
            Result rslt  = new StreamResult(fileXML);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(src, rslt);

            System.out.println("Fichero exportado con éxito");
        } catch (ParserConfigurationException | TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createPeregrinoElement(Document doc, Element root, Peregrino pilgrim) {
        Element peregrino = doc.createElement("peregrino");
        root.appendChild(peregrino);

        newElementXML("id", String.valueOf(pilgrim.getId()), root, doc);
        newElementXML("fechaexp", pilgrim.getNombre(), root, doc);
        newElementXML("expedidoen", DATE_FORMAT.format(pilgrim.getCarnet().getFechaExp()), root, doc);

        newElementXML("nombre", pilgrim.getNombre(), peregrino, doc);
        newElementXML("nacionalidad", pilgrim.getNacionalidad(), peregrino, doc);
        newElementXML("hoy", DATE_FORMAT.format(LocalDate.now()), peregrino, doc);
        newElementXML("distanciaTotal", String.valueOf(pilgrim.getCarnet().getDistancia()), peregrino, doc);

    }

    private static void createParadasElement(Document doc, Element root, Peregrino pilgrim) {
        Element paradas = doc.createElement("paradas");
        root.appendChild(paradas);

        Element parada = doc.createElement("parada");
        ArrayList <Parada> listaParadas = pilgrim.getParadas();
        int clock =  1;
        for (Parada stand : listaParadas) {
            paradas.appendChild(parada);
            newElementXML("orden", String.valueOf(clock++), parada, doc);
            newElementXML("nombre", stand.getNombre(), parada, doc);
            newElementXML("region", String.valueOf(stand.getRegion()), parada, doc);
        }
    }

    private static void createEstanciasElement(Document doc, Element root, Peregrino pilgrim) {

            Element estancias = doc.createElement("estancias");
            root.appendChild(estancias);

            Element estancia = doc.createElement("estancia");
            if(pilgrim.getEstancias() != null) {
                ArrayList<Estancia> listaEstancias = pilgrim.getEstancias();
                for (Estancia stay : listaEstancias) {
                    estancias.appendChild(estancia);
                    newElementXML("id", String.valueOf(stay.getId()), estancia, doc);
                    newElementXML("fecha", DATE_FORMAT.format(stay.getFecha()), estancia, doc);
                    newElementXML("parada", stay.getParada().getNombre(), estancia, doc);
                    newElementXML("vip", String.valueOf(stay.isVIP()), estancia, doc);

                }
            }
    }

    private static void newElementXML(String tag, String value, Element root, Document doc) {
        Element element = doc.createElement(tag);
        Text text = doc.createTextNode(value);
        element.appendChild(text);
        root.appendChild(element);
    }
}
