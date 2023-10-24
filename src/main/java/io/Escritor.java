package io;

import entities.Estancia;
import entities.Parada;
import entities.Peregrino;
import entities.Perfil;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import utilidades.AppendableObjectOutputStream;

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

public class Escritor {

    public static void writeCredencial(String nombre, String pass, Perfil perfil, long lastId){

        String str = nombre + " " + pass + " " + perfil + " " + lastId + "\n";

        try (FileWriter fileWriter = new FileWriter(PATH_CREDENTIALS, true)) {
            fileWriter.write(str);
            System.out.println("Contenido agregado al archivo.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void writeParada(Parada parada) {

        try {
            FileOutputStream fos = new FileOutputStream(PATH_STOPS, true);
            AppendableObjectOutputStream oos = new AppendableObjectOutputStream(fos, true);
            oos.writeObject(parada);

            System.out.println("Se ha agregado una nueva parada con éxito");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void writeCarnet(Peregrino pilgrim) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            DOMImplementation implementation = dBuilder.getDOMImplementation();
            Document doc = implementation.createDocument(PATH_EXPORTS, "Carnet", null);

            Element root = doc.getDocumentElement();
            newElementXML("id", String.valueOf(pilgrim.getId()), root, doc);
            newElementXML("fechaexp", pilgrim.getCarnet().getFechaExp().format(DATE_TIME_FORMATTER), root, doc);
            newElementXML("expedidoen", pilgrim.getCarnet().getParadaInicial().getNombre(), root, doc);

            createPeregrinoElement(doc, root, pilgrim);
            newElementXML("hoy", LocalDate.now().format(DATE_TIME_FORMATTER), root, doc);
            newElementXML("distanciaTotal", String.valueOf(pilgrim.getCarnet().getDistancia()), root, doc);

            createParadasElement(doc, root, pilgrim);

            createEstanciasElement(doc, root, pilgrim);

            Source src = new DOMSource(doc);
            File fileXML = new File(PATH_EXPORTS + pilgrim.getNombre() + ".xml");
            Result rslt = new StreamResult(fileXML);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(src, rslt);

            System.out.println("Fichero exportado con éxito");
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    private static void createPeregrinoElement(Document doc, Element root, Peregrino pilgrim) {
        Element peregrino = doc.createElement("peregrino");
        root.appendChild(peregrino);
        newElementXML("nombre", pilgrim.getNombre(), peregrino, doc);
        newElementXML("nacionalidad", pilgrim.getNacionalidad(), peregrino, doc);
    }

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

    private static void createEstanciasElement(Document doc, Element root, Peregrino pilgrim) {
        if (pilgrim.getEstancias() != null) {
            Element estancias = doc.createElement("estancias");
            root.appendChild(estancias);
            int clock = 1;

            ArrayList<Estancia> listaEstancias = pilgrim.getEstancias();
            for (Estancia stay : listaEstancias) {
                Element estancia = doc.createElement("estancia");
                estancias.appendChild(estancia);
                newElementXML("id", String.valueOf(stay.getId()), estancia, doc);
                newElementXML("fecha",stay.getFecha().format(DATE_TIME_FORMATTER), estancia, doc);
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
