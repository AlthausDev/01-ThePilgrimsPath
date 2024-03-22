package com.althaus.dev.GestorPeregrinos.service;

import com.althaus.dev.GestorPeregrinos.model.objectDB.Direccion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DireccionService {

    List<Direccion> getAllDirecciones();

    Direccion getDireccionById(Long id);

    Direccion create(Direccion direccion);

    void deleteDireccion(Long id);
}
