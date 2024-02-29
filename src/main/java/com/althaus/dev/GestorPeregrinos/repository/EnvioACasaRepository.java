package com.althaus.dev.GestorPeregrinos.repository;

import com.althaus.dev.GestorPeregrinos.model.EnvioACasa;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("objectDB")
public interface EnvioACasaRepository extends CoreRepository<EnvioACasa, Long> {
}
