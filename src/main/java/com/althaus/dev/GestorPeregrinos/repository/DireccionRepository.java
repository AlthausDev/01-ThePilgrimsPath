package com.althaus.dev.GestorPeregrinos.repository;

import com.althaus.dev.GestorPeregrinos.model.Direccion;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public class DireccionRepository {

    @PersistenceContext(unitName = "objectdbEntityManager")
    private EntityManager entityManager;

    @Transactional
    public List<Direccion> getAllDirecciones() {
        return entityManager.createQuery("SELECT d FROM Direccion d", Direccion.class).getResultList();
    }

    @Transactional
    public Direccion getDireccionById(Long id) {
        return entityManager.find(Direccion.class, id);
    }

    @Transactional
    public Direccion create(Direccion direccion) {
        entityManager.persist(direccion);
        return direccion;
    }

    @Transactional
    public void deleteDireccion(Long id) {
        Direccion direccion = entityManager.find(Direccion.class, id);
        if (direccion != null) {
            entityManager.remove(direccion);
        }
    }
}
