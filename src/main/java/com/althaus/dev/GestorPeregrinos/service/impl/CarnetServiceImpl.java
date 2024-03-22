package com.althaus.dev.GestorPeregrinos.service.impl;

import com.althaus.dev.GestorPeregrinos.config.ExistDBConfig;
import com.althaus.dev.GestorPeregrinos.model.Carnet;
import com.althaus.dev.GestorPeregrinos.model.CarnetBackup;
import com.althaus.dev.GestorPeregrinos.model.Parada;
import com.althaus.dev.GestorPeregrinos.repository.CarnetRepository;
import com.althaus.dev.GestorPeregrinos.repository.MongoDBRepository;
import com.althaus.dev.GestorPeregrinos.service.CarnetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio para la gestión de Carnets.
 *
 * <p>Esta clase proporciona la implementación concreta de las operaciones específicas del servicio
 * para la entidad Carnet.</p>
 *
 * @author Althaus_Dev
 * @see Carnet
 * @see CarnetService
 * @since 2024-01-12
 */
@Service
public class CarnetServiceImpl extends CoreServiceImpl<Carnet> implements CarnetService {

    private final ExistDBConfig existDBConfig;

    private final CarnetRepository carnetRepository;
    private final MongoDBRepository mongoDBRepository;

    @Autowired
    public CarnetServiceImpl(CarnetRepository carnetRepository, ExistDBConfig existDBConfig, MongoDBRepository mongoDBRepository) {
        super(carnetRepository);
        this.carnetRepository = carnetRepository;
        this.mongoDBRepository = mongoDBRepository;
        this.existDBConfig = existDBConfig;

    }

    @Override
    public Carnet create(Carnet entity) {
        Carnet savedCarnet = super.create(entity);
        Parada paradaInicial = savedCarnet.getParadaInicial();
        if (paradaInicial != null) {
            existDBConfig.saveCarnetParada(paradaInicial.getId(), savedCarnet);
        } else {
            throw new RuntimeException("No se pudo obtener la parada inicial para el carné.");
        }
        return savedCarnet;
    }


    public void backupCarnets() {
        List<Carnet> carnets = carnetRepository.findAll();
        List<String> carnetsBackup = carnets.stream()
                .map(Carnet::toString)
                .collect(Collectors.toList());

        CarnetBackup carnetBackup = createBackupObject(carnetsBackup);

        saveBackupToMongoDB(carnetBackup);
    }

    private String generateBackupFileName() {
        return "backupcarnets_" + LocalDateTime.now().toString().replace(":", "-");
    }

    private CarnetBackup createBackupObject(List<String> carnetsBackup) {
        CarnetBackup carnetBackup = new CarnetBackup();
        carnetBackup.setFileName(generateBackupFileName());
        carnetBackup.setCarnets(carnetsBackup);
        return carnetBackup;
    }

    private void saveBackupToMongoDB(CarnetBackup carnetBackup) {
        mongoDBRepository.save(carnetBackup);
    }
}
