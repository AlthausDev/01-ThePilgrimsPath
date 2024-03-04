package com.althaus.dev.GestorPeregrinos.repository;

import com.althaus.dev.GestorPeregrinos.model.Contratado;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("db4o")
public interface ContratadoRepository {
    void save(Contratado contratado);

    void update(Contratado contratado);

    void deleteById(Long id);

    Contratado findById(Long id);

    List<Contratado> findAll();
}
