package com.althaus.dev.GestorPeregrinos.service;

import com.althaus.dev.GestorPeregrinos.model.Credenciales;
import com.althaus.dev.GestorPeregrinos.repository.CredencialesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CredencialesServiceImpl implements CredencialesService {

    private final CredencialesRepository credencialesRepository;

    @Autowired
    public CredencialesServiceImpl(CredencialesRepository credencialesRepository) {
        this.credencialesRepository = credencialesRepository;
    }

    /**
     * @param entity
     * @return
     */
    @Override
    public Credenciales create(Credenciales entity) {
        return credencialesRepository.save(entity);
    }

    /**
     * @param entity
     * @return
     */
    @Override
    public Credenciales read(Credenciales entity) {
        return credencialesRepository.findById(entity.getId()).orElse(null);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<Credenciales> readById(Long id) {
        return Optional.empty();
    }

    /**
     * @param entity
     * @return
     */
    @Override
    public HashMap<Long, Credenciales> readAll(Credenciales entity) {
        return null;
    }

    /**
     * @param entity
     * @return
     */
    @Override
    public Credenciales update(Credenciales entity) {
        return null;
    }

    /**
     * @param entity
     */
    @Override
    public void delete(Credenciales entity) {

    }

    /**
     * @param id
     */
    @Override
    public void deleteById(Long id) {

    }

    /**
     * @param entity
     */
    @Override
    public void deleteAll(List<Credenciales> entity) {

    }

}
