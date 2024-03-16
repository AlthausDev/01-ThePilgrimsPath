package com.althaus.dev.GestorPeregrinos.service;

import com.althaus.dev.GestorPeregrinos.model.EnvioACasa;
import com.althaus.dev.GestorPeregrinos.model.Parada;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Profile("objectdb")
public interface EnvioACasaService extends CoreService<EnvioACasa> {
    List<EnvioACasa> getEnviosDesdeParada(Parada parada);

}
