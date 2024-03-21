package com.althaus.dev.GestorPeregrinos.service;

import com.althaus.dev.GestorPeregrinos.model.Contratado;

import java.util.List;

public interface ContratadoService {
    void createContratado(Contratado contratado);

    void updateContratado(Contratado contratado);

    void deleteContratado(Long id);

    Contratado getContratadoById(Long id);

    List<Contratado> getAllContratados();
}
