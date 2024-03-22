package com.althaus.dev.GestorPeregrinos.service.impl;

import com.althaus.dev.GestorPeregrinos.model.EnvioACasa;
import com.althaus.dev.GestorPeregrinos.model.Parada;
import com.althaus.dev.GestorPeregrinos.repository.EnvioACasaRepository;
import com.althaus.dev.GestorPeregrinos.service.EnvioACasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
public class EnvioACasaServiceImpl implements EnvioACasaService {
    private final EnvioACasaRepository envioACasaRepository;

    @Autowired
    public EnvioACasaServiceImpl(EnvioACasaRepository envioACasaRepository) {
        this.envioACasaRepository = envioACasaRepository;
    }

    @Transactional
    public void create(EnvioACasa envioACasa) {
        envioACasaRepository.save(envioACasa);
    }

    @Transactional
    @Override
    public List<EnvioACasa> getEnviosDesdeParada(Parada parada) {
        return envioACasaRepository.getEnviosParada(parada);
    }
}
