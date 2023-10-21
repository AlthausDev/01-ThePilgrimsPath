package controllers;

import entities.Estancia;
import entities.Parada;
import entities.Peregrino;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import static utilidades.Constantes.PATH_EXPORTS;

public class Export {

    public static void exportCanet(Peregrino pilgrim) {

        DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");

        try {
            DocumentBuilder builder = factoria.newDocumentBuilder();

            DOMImplementation implementation = builder.getDOMImplementation();
            Document doc = implementation.createDocument(PATH_EXPORTS, "Carnet", null);

            Element root = doc.getDocumentElement();
            Element peregrino = doc.createElement("peregrino");
            Element paradas = doc.createElement("paradas");
            Element parada = doc.createElement("parada");
            Element estancias = doc.createElement("estancias");
            Element estancia = doc.createElement("estancia");

            root.appendChild(peregrino);
            root.appendChild(paradas);
            root.appendChild(estancias);


            newElementXML("id", String.valueOf(pilgrim.getId()), root, doc);
            newElementXML("fechaexp", pilgrim.getNombre(), root, doc);
            newElementXML("expedidoen", formato.format(pilgrim.getCarnet().getFechaExp()), root, doc);

            newElementXML("nombre", pilgrim.getNombre(), peregrino, doc);
            newElementXML("nacionalidad", pilgrim.getNacionalidad(), peregrino, doc);
            newElementXML("hoy", formato.format(LocalDate.now()), peregrino, doc);
            newElementXML("distanciaTotal", String.valueOf(pilgrim.getCarnet().getDistancia()), peregrino, doc);

            ArrayList <Parada> listaParadas = pilgrim.getParadas();

            int clock =  1;
            for (Parada stand : listaParadas) {
                paradas.appendChild(parada);
                newElementXML("orden", String.valueOf(clock++), parada, doc);
                newElementXML("nombre", stand.getNombre(), parada, doc);
                newElementXML("region", String.valueOf(stand.getRegion()), parada, doc);
            }

            if(pilgrim.getEstancias() != null) {
                ArrayList<Estancia> listaEstancias = pilgrim.getEstancias();
                clock = 1;
                for (Estancia stay : listaEstancias) {
                    estancias.appendChild(estancia);
                    newElementXML("id", String.valueOf(stay.getId()), estancia, doc);
                    newElementXML("fecha", formato.format(stay.getFecha()), estancia, doc);
                    newElementXML("parada", stay.getParada().getNombre(), estancia, doc);
                    newElementXML("vip", String.valueOf(stay.isVIP()), estancia, doc);

                }
            }

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    private static void newElementXML(String tag, String value, Element root, Document doc) {
        Element element = doc.createElement(tag);
        Text text = doc.createTextNode(value);
        element.appendChild(text);
        root.appendChild(element);

    }
}
