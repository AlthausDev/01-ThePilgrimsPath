package com.althaus.dev.GestorPeregrinos.service.impl;

import com.althaus.dev.GestorPeregrinos.model.Servicio;
import com.althaus.dev.GestorPeregrinos.repository.ServicioRepository;
import com.althaus.dev.GestorPeregrinos.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioServiceImpl implements ServicioService {
    private final ServicioRepository servicioRepository;

    @Autowired
    public ServicioServiceImpl(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    @Override
    public void createServicio(Servicio servicio) {
        servicioRepository.save(servicio);
    }

    @Override
    public void updateServicio(Servicio servicio) {
        servicioRepository.update(servicio);
    }

    @Override
    public void deleteServicio(Long id) {
        servicioRepository.deleteById(id);
    }

    @Override
    public Servicio getServicioById(Long id) {
        return servicioRepository.findById(id);
    }

    @Override
    public List<Servicio> getAllServicios() {
        return servicioRepository.findAll();
    }
}
