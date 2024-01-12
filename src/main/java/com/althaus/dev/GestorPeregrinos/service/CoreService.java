package com.althaus.dev.GestorPeregrinos.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz base para servicios genéricos de entidades.
 *
 * <p>
 * Esta interfaz define las operaciones comunes para la gestión de entidades genéricas y hereda las operaciones CRUD básicas
 * de la interfaz {@link CoreService}.
 * </p>
 *
 * @author Althaus_Dev
 * @param <T> Tipo de entidad.
 *
 * @see CoreService
 */
@Service
public interface CoreService<T> {

    /**
     * Crea una nueva entidad.
     *
     * @param entity La entidad a crear.
     * @return La entidad creada.
     *
     * @see CoreService#create(Object)
     */
    T create(T entity);

    /**
     * Lee una entidad por su ID.
     *
     * @param id El ID de la entidad a leer.
     * @return Una {@code Optional} que contiene la entidad leída, o vacía si no se encuentra.
     *
     * @see CoreService#read(Long)
     */
    Optional<T> read(Long id);

    /**
     * Obtiene una lista de todas las entidades.
     *
     * @return Lista de todas las entidades.
     *
     * @see CoreService#readAllList()
     */
    List<T> readAllList();

    /**
     * Obtiene un mapa de todas las entidades, donde las claves son los IDs de las entidades.
     *
     * @return Un mapa de todas las entidades.
     *
     * @see CoreService#readAllMap()
     */
    HashMap<Long, T> readAllMap();

    /**
     * Actualiza una entidad existente.
     *
     * @param entity La entidad a actualizar.
     * @return La entidad actualizada.
     *
     * @see CoreService#update(Object)
     */
    T update(T entity);

    /**
     * Elimina una entidad.
     *
     * @param entity La entidad a eliminar.
     *
     * @see CoreService#delete(Object)
     */
    void delete(T entity);

    /**
     * Elimina una entidad por su ID.
     *
     * @param id El ID de la entidad a eliminar.
     *
     * @see CoreService#deleteById(Long)
     */
    void deleteById(Long id);

    /**
     * Elimina todas las entidades proporcionadas en la lista.
     *
     * @param entities Lista de entidades a eliminar.
     *
     * @see CoreService#deleteAll(List)
     */
    void deleteAll(List<T> entities);
}
