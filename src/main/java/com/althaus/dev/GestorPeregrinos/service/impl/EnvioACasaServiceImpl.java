package com.althaus.dev.GestorPeregrinos.service.impl;

import com.althaus.dev.GestorPeregrinos.model.EnvioACasa;
import com.althaus.dev.GestorPeregrinos.repository.CoreRepository;
import com.althaus.dev.GestorPeregrinos.repository.EnvioACasaRepository;
import com.althaus.dev.GestorPeregrinos.service.EnvioACasaService;
import org.springframework.beans.factory.annotation.Autowired;

public class EnvioACasaServiceImpl extends CoreServiceImpl<EnvioACasa> implements EnvioACasaService {
    private final EnvioACasaRepository envioACasaRepository;

    @Autowired
    public EnvioACasaServiceImpl(EnvioACasaRepository envioACasaRepository) {
        super(envioACasaRepository);
        this.envioACasaRepository = envioACasaRepository;
    }

    public void createEnvioACasa(EnvioACasa envioACasa) {
        envioACasaRepository.save(envioACasa);
    }
}
