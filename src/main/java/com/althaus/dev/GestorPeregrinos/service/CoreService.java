package com.althaus.dev.GestorPeregrinos.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz base para servicios genéricos de entidades.
 *
 * @param <T> Tipo de entidad.
 */
public interface CoreService<T> {

    /**
     * Crea una nueva entidad.
     *
     * @param entity La entidad a crear.
     * @return La entidad creada.
     */
    T create(T entity);

    /**
     * Lee una entidad por su ID.
     *
     * @param id El ID de la entidad a leer.
     * @return Una {@code Optional} que contiene la entidad leída, o vacía si no se encuentra.
     */
    Optional<T> read(Long id);

    /**
     * Obtiene una lista de todas las entidades.
     *
     * @return Lista de todas las entidades.
     */
    List<T> readAllList();

    /**
     * Obtiene un mapa de todas las entidades, donde las claves son los IDs de las entidades.
     *
     * @return Un mapa de todas las entidades.
     */
    HashMap<Long, T> readAllMap();

    /**
     * Actualiza una entidad existente.
     *
     * @param entity La entidad a actualizar.
     * @return La entidad actualizada.
     */
    T update(T entity);

    /**
     * Elimina una entidad.
     *
     * @param entity La entidad a eliminar.
     */
    void delete(T entity);

    /**
     * Elimina una entidad por su ID.
     *
     * @param id El ID de la entidad a eliminar.
     */
    void deleteById(Long id);

    /**
     * Elimina todas las entidades proporcionadas en la lista.
     *
     * @param entities Lista de entidades a eliminar.
     */
    void deleteAll(List<T> entities);
}
