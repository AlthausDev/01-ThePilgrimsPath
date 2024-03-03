package com.althaus.dev.GestorPeregrinos.service.impl;

import com.althaus.dev.GestorPeregrinos.model.Direccion;
import com.althaus.dev.GestorPeregrinos.repository.DireccionRepository;
import com.althaus.dev.GestorPeregrinos.service.DireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DireccionServiceImpl extends CoreServiceImpl<Direccion> implements DireccionService {

    private final DireccionRepository direccionRepository;

    @Autowired
    public DireccionServiceImpl(DireccionRepository direccionRepository) {
        super(direccionRepository);
        this.direccionRepository = direccionRepository;
    }
}
