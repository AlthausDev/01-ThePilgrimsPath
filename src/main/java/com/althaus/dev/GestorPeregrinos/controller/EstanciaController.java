package com.althaus.dev.GestorPeregrinos.controller;

import com.althaus.dev.GestorPeregrinos.model.Estancia;
import com.althaus.dev.GestorPeregrinos.service.EstanciaService;
import com.althaus.dev.GestorPeregrinos.view.EstanciaView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Controller
public class EstanciaController {

    private final EstanciaService estanciaService;
    private final EstanciaView estanciaView;

    @Autowired
    public EstanciaController(EstanciaService estanciaService, EstanciaView estanciaView) {
        this.estanciaService = estanciaService;
        this.estanciaView = estanciaView;
    }

    public void exportarEstancias(long idParada, LocalDate fechaInicio, LocalDate fechaFin) {
        List<Estancia> estancias = estanciaService.getEstanciasByParadaAndFecha(idParada, fechaInicio, fechaFin);
        estanciaView.mostrarEstancias(estancias, idParada, fechaInicio, fechaFin);
    }
}
