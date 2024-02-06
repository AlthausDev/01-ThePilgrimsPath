package com.althaus.dev.GestorPeregrinos.service;

import com.althaus.dev.GestorPeregrinos.model.Servicio;

public interface ServicioService {
    void createServicio(Servicio servicio);
    Servicio findServicioByName(String nombre);
}
