package com.althaus.dev.GestorPeregrinos.service.impl;

import com.althaus.dev.GestorPeregrinos.model.Carnet;
import com.althaus.dev.GestorPeregrinos.repository.AdminParadaRepository;
import com.althaus.dev.GestorPeregrinos.repository.CarnetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarnetServiceImpl extends CoreServiceImpl<Carnet> {

    @Autowired
    public CarnetServiceImpl(CarnetRepository carnetRepository) {
        super(carnetRepository);
    }
}

