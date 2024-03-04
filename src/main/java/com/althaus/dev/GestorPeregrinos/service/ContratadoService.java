package com.althaus.dev.GestorPeregrinos.service;

import com.althaus.dev.GestorPeregrinos.model.Contratado;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("db4o")
public interface ContratadoService {
    void createContratado(Contratado contratado);

    void updateContratado(Contratado contratado);

    void deleteContratado(Long id);

    Contratado getContratadoById(Long id);

    List<Contratado> getAllContratados();
}
