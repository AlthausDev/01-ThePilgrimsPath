package com.althaus.dev.GestorPeregrinos.service;

import com.althaus.dev.GestorPeregrinos.model.Estancia;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface EstanciaService extends CoreService <Estancia> {
    List<Estancia> getEstanciasByParadaAndFecha(long idParada, LocalDate fechaInicio, LocalDate fechaFin);
}
