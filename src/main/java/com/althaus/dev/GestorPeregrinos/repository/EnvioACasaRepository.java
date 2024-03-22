package com.althaus.dev.GestorPeregrinos.repository;

import com.althaus.dev.GestorPeregrinos.model.Parada;
import com.althaus.dev.GestorPeregrinos.model.objectDB.EnvioACasa;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class EnvioACasaRepository {

    @PersistenceContext(unitName = "objectdbEntityManager")
    private EntityManager entityManager;

    @Transactional
    public List<EnvioACasa> getEnviosParada(Parada parada) {
        TypedQuery<EnvioACasa> query = entityManager.createQuery("SELECT e FROM EnvioACasa e WHERE e.parada = :parada", EnvioACasa.class);
        query.setParameter("parada", parada);
        List<EnvioACasa> envios = query.getResultList();
        return envios;
    }


    @Transactional
    public void save(EnvioACasa envio){
        entityManager.persist(envio);
    }
}
