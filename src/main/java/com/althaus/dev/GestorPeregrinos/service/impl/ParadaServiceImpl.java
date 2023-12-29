package com.althaus.dev.GestorPeregrinos.service.impl;

import com.althaus.dev.GestorPeregrinos.model.AdminParada;
import com.althaus.dev.GestorPeregrinos.model.Credenciales;
import com.althaus.dev.GestorPeregrinos.model.Parada;
import com.althaus.dev.GestorPeregrinos.repository.ParadaRepository;
import com.althaus.dev.GestorPeregrinos.service.AdminParadaService;
import com.althaus.dev.GestorPeregrinos.service.CredencialesService;
import com.althaus.dev.GestorPeregrinos.service.ParadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParadaServiceImpl extends CoreServiceImpl<Parada> implements ParadaService {

    @Autowired
    private AdminParadaService adminParadaService;

    @Autowired
    private CredencialesService credencialesService;
    @Autowired
    private final ParadaRepository paradaRepository;

    @Autowired
    public ParadaServiceImpl(ParadaRepository repository,
                             ParadaRepository paradaRepository) {
        super(repository);
        this.paradaRepository = paradaRepository;
    }

    @Override
    public void create(String nombreParada, char regionParada, String nombreAdminParada, String passAdminParada) {
        try {
            Parada nuevaParada = new Parada(nombreParada, regionParada, null);

            AdminParada adminParada = new AdminParada(nombreAdminParada, nuevaParada);
            nuevaParada.setAdminParada(adminParada);

            adminParadaService.create(adminParada);
            Parada savedParada = create(nuevaParada);

            Credenciales credenciales = new Credenciales(nombreAdminParada, passAdminParada, adminParada);
            credencialesService.create(credenciales);

            System.out.println("Nueva parada registrada con éxito. ID de la parada: " + savedParada.getId());
        } catch (Exception e) {
            System.err.println("Error al registrar la nueva parada");
            e.printStackTrace();
        }
    }

    public long count(){
        return paradaRepository.count();
    }
}

