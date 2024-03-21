package com.althaus.dev.GestorPeregrinos.repository;

import com.althaus.dev.GestorPeregrinos.model.Contratado;

import java.util.List;

public interface ContratadoRepository {

    void save(Contratado contratado);

    void update(Contratado contratado);

    void deleteById(Long id);

    Contratado findById(Long id);

    List<Contratado> findAll();
}
