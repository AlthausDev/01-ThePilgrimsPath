package com.althaus.dev.GestorPeregrinos.service.impl;

import com.althaus.dev.GestorPeregrinos.model.Parada;
import com.althaus.dev.GestorPeregrinos.repository.ParadaRepository;
import com.althaus.dev.GestorPeregrinos.service.AdminParadaService;
import com.althaus.dev.GestorPeregrinos.service.CredencialesService;
import com.althaus.dev.GestorPeregrinos.service.ParadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParadaServiceImpl extends CoreServiceImpl<Parada> implements ParadaService {

    private final ParadaRepository paradaRepository;

    @Autowired
    public ParadaServiceImpl(ParadaRepository repository,
                             ParadaRepository paradaRepository) {
        super(repository);
        this.paradaRepository = paradaRepository;
    }

    @Override
    public boolean existsByNombre(String nombre) {
        return paradaRepository.existsByNombre(nombre);
    }

}
