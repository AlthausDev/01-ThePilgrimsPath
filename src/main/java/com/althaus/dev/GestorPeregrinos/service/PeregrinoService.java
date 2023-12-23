package com.althaus.dev.GestorPeregrinos.service;

import com.althaus.dev.GestorPeregrinos.model.Parada;
import com.althaus.dev.GestorPeregrinos.model.Peregrino;

import java.util.List;
import java.util.Optional;

public interface PeregrinoService extends CoreService <Peregrino> {

    Optional<Peregrino> registrarNuevoPeregrino(String nombre, String nacionalidad, Long paradaId);

    Optional<Peregrino> login(String nombre, String nacionalidad);

    void logout(Long peregrinoId);

    List<Parada> obtenerParadasDePeregrino(Long peregrinoId);

    String exportarCarnetXml(Long peregrinoId);

    List<Peregrino> obtenerPeregrinosPorNacionalidad(String nacionalidad);

    Optional<Peregrino> obtenerPeregrinoPorNombreYParada(String nombre, Long paradaId);

    List<Peregrino> obtenerPeregrinosPorParada(Long paradaId);

    List<Peregrino> obtenerPeregrinosConEstanciasVip();
}
