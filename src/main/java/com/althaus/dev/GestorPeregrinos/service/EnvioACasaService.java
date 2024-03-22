package com.althaus.dev.GestorPeregrinos.service;

import com.althaus.dev.GestorPeregrinos.model.Parada;
import com.althaus.dev.GestorPeregrinos.model.objectDB.EnvioACasa;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EnvioACasaService {
    List<EnvioACasa> getEnviosDesdeParada(Parada parada);

    void create(EnvioACasa envioACasa);
}