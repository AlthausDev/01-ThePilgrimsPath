package com.althaus.dev.GestorPeregrinos.service.impl;

import com.althaus.dev.GestorPeregrinos.model.Parada;
import com.althaus.dev.GestorPeregrinos.model.Peregrino;
import com.althaus.dev.GestorPeregrinos.repository.PeregrinoRepository;
import com.althaus.dev.GestorPeregrinos.service.PeregrinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeregrinoServiceImpl extends CoreServiceImpl<Peregrino> implements PeregrinoService {

    private final PeregrinoRepository peregrinoRepository;

    @Autowired
    public PeregrinoServiceImpl(PeregrinoRepository peregrinoRepository) {
        super(peregrinoRepository);
        this.peregrinoRepository = peregrinoRepository;
    }


}
