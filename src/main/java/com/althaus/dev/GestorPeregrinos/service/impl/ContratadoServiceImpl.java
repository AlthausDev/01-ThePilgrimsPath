package com.althaus.dev.GestorPeregrinos.service.impl;

import com.althaus.dev.GestorPeregrinos.model.Contratado;
import com.althaus.dev.GestorPeregrinos.repository.ContratadoRepository;
import com.althaus.dev.GestorPeregrinos.service.ContratadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContratadoServiceImpl implements ContratadoService {

    private final ContratadoRepository contratadoRepository;

    @Autowired
    public ContratadoServiceImpl(ContratadoRepository contratadoRepository) {
        this.contratadoRepository = contratadoRepository;
    }

    @Override
    public void createContratado(Contratado contratado) {
        contratadoRepository.save(contratado);
    }

    @Override
    public void updateContratado(Contratado contratado) {
        contratadoRepository.update(contratado);
    }

    @Override
    public void deleteContratado(Long id) {
        contratadoRepository.deleteById(id);
    }

    @Override
    public Contratado getContratadoById(Long id) {
        return contratadoRepository.findById(id);
    }

    @Override
    public List<Contratado> getAllContratados() {
        return contratadoRepository.findAll();
    }
}
