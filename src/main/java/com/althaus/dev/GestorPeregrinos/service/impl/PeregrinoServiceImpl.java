package com.althaus.dev.GestorPeregrinos.service.impl;

import com.althaus.dev.GestorPeregrinos.model.Parada;
import com.althaus.dev.GestorPeregrinos.model.Peregrino;
import com.althaus.dev.GestorPeregrinos.repository.PeregrinoRepository;
import com.althaus.dev.GestorPeregrinos.service.PeregrinoService;
import com.althaus.dev.GestorPeregrinos.util.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeregrinoServiceImpl extends CoreServiceImpl<Peregrino> implements PeregrinoService {

    private final XMLWriter xmlWriter;
    private final PeregrinoRepository peregrinoRepository;

    @Autowired
    public PeregrinoServiceImpl(PeregrinoRepository peregrinoRepository, XMLWriter xmlWriter) {
        super(peregrinoRepository);
        this.xmlWriter = xmlWriter;
        this.peregrinoRepository = peregrinoRepository;
    }

    /**
     * @param nombre
     * @param nacionalidad
     * @param paradaId
     * @return
     */
    @Override
    public Optional<Peregrino> registrarNuevoPeregrino(String nombre, String nacionalidad, Long paradaId) {
        return Optional.empty();
    }

    /**
     * @param peregrinoId
     * @return
     */
    @Override
    public List<Parada> obtenerParadasDePeregrino(Long peregrinoId) {
        return null;
    }

    public void exportarCarnetToXml(Long peregrinoId) {
        xmlWriter.exportarCarnet(peregrinoId);
    }

    /**
     * @param nacionalidad
     * @return
     */
    @Override
    public List<Peregrino> obtenerPeregrinosPorNacionalidad(String nacionalidad) {
        return null;
    }

    /**
     * @param paradaId
     * @return
     */
    @Override
    public List<Peregrino> obtenerPeregrinosPorParada(Long paradaId) {
        return null;
    }
}
