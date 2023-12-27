package com.althaus.dev.GestorPeregrinos.service;

import com.althaus.dev.GestorPeregrinos.model.Parada;
import org.springframework.stereotype.Service;

@Service
public interface ParadaService extends CoreService<Parada> {
    void create(String nombreParada, char regionParada, String nombreAdminParada, String passAdminParada);
}

