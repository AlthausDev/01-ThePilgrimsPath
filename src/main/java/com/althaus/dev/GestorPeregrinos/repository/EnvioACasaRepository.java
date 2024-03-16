package com.althaus.dev.GestorPeregrinos.repository;

import com.althaus.dev.GestorPeregrinos.model.EnvioACasa;
import com.althaus.dev.GestorPeregrinos.model.Parada;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@Profile("objectdb")
public interface EnvioACasaRepository extends CoreRepository<EnvioACasa, Long> {
    @Query("SELECT e FROM EnvioACasa e WHERE e.parada = :parada")
    List<EnvioACasa> getEnviosParada(@Param("parada") Parada parada);

}
