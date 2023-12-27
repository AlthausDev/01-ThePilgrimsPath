package com.althaus.dev.GestorPeregrinos.service;

import com.althaus.dev.GestorPeregrinos.model.Parada;
import com.althaus.dev.GestorPeregrinos.model.Peregrino;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PeregrinoService extends CoreService <Peregrino> {

    Optional<Peregrino> registrarNuevoPeregrino(String nombre, String nacionalidad, Long paradaId);

    List<Parada> obtenerParadasDePeregrino(Long peregrinoId);

   // void exportarCarnetToXml(Long peregrinoId);

    List<Peregrino> obtenerPeregrinosPorNacionalidad(String nacionalidad);

    List<Peregrino> obtenerPeregrinosPorParada(Long paradaId);

}
