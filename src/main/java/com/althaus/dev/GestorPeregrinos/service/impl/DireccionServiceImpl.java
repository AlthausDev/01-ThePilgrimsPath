package com.althaus.dev.GestorPeregrinos.service.impl;

import com.althaus.dev.GestorPeregrinos.model.objectDB.Direccion;
import com.althaus.dev.GestorPeregrinos.repository.DireccionRepository;
import com.althaus.dev.GestorPeregrinos.service.DireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DireccionServiceImpl implements DireccionService {

    private final DireccionRepository direccionRepository;

    @Autowired
    public DireccionServiceImpl(DireccionRepository direccionRepository) {
        this.direccionRepository = direccionRepository;
    }

    @Override
    public List<Direccion> getAllDirecciones() {
        return direccionRepository.getAllDirecciones();
    }

    @Override
    public Direccion getDireccionById(Long id) {
        return direccionRepository.getDireccionById(id);
    }

    @Override
    public Direccion create(Direccion direccion) {
        return direccionRepository.create(direccion);
    }

    @Override
    public void deleteDireccion(Long id) {
        direccionRepository.deleteDireccion(id);
    }
}
