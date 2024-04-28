package com.althaus.dev.GestorPeregrinos.service.impl;

import com.althaus.dev.GestorPeregrinos.model.Parada;
import com.althaus.dev.GestorPeregrinos.model.Servicio;
import com.althaus.dev.GestorPeregrinos.repository.ServicioRepository;
import com.althaus.dev.GestorPeregrinos.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public void delete(Servicio servicio) {
        servicioRepository.delete(servicio);
    }

    @Override
    public void deleteById(Long id) {
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
    public List<Servicio> getServiciosDisponiblesPorParada(Optional<Parada> paradaOptional) {
        if (paradaOptional.isPresent()) {
            Parada parada = paradaOptional.get();
            return parada.getServicios();
        } else {
            return new ArrayList<>();
        }
    }


    @Override
    public void updateServicioPrecio(String nombreServicio, double nuevoPrecio) {
        servicioRepository.updatePrecio(nombreServicio, nuevoPrecio);
    }

    @Override
    public void updateServicioNombre(String nombreServicio, String nuevoNombre) {
        servicioRepository.updateNombre(nombreServicio, nuevoNombre);
    }

    @Override
    public void deleteServicioNombre(String nombreServicio) {
        servicioRepository.delete(servicioRepository.findByNombre(nombreServicio));
    }

    @Override
    public Servicio getServicioByNombre(String nombreServicio) {
        return servicioRepository.findByNombre(nombreServicio);
    }
}
