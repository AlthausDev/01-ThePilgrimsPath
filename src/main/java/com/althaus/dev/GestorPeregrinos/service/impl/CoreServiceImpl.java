package com.althaus.dev.GestorPeregrinos.service.impl;

import com.althaus.dev.GestorPeregrinos.model.Identifiable;
import com.althaus.dev.GestorPeregrinos.repository.CoreRepository;
import com.althaus.dev.GestorPeregrinos.service.CoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * Implementación abstracta del servicio central que proporciona operaciones CRUD comunes para entidades identificables.
 *
 * @param <T> El tipo de entidad identificable.
 */
public abstract class CoreServiceImpl<T extends Identifiable> implements CoreService<T> {

    private static final Logger logger = LoggerFactory.getLogger(CoreServiceImpl.class);
    private final JpaRepository<T, Long> repository;
    private final Class<T> entityClass;

    /**
     * Constructor para la inicialización de la implementación del servicio central.
     *
     * @param repository El repositorio JPA utilizado para acceder a la capa de persistencia.
     */
    public CoreServiceImpl(CoreRepository repository) {
        this.repository = repository;
        this.entityClass = initEntityClass();
    }

    private Class<T> initEntityClass() {
        Class<?> clazz = getClass();
        Type type = clazz.getGenericSuperclass();

        while (!(type instanceof ParameterizedType parameterizedType)) {
            if (clazz.getSuperclass() == null) {
                throw new IllegalArgumentException("No se pudo determinar el tipo de la entidad.");
            }

            clazz = clazz.getSuperclass();
            type = clazz.getGenericSuperclass();
        }

        Type[] typeArguments = parameterizedType.getActualTypeArguments();

        if (typeArguments.length > 0 && typeArguments[0] instanceof Class) {
            return (Class<T>) typeArguments[0];
        } else {
            throw new IllegalArgumentException("No se pudo determinar el tipo de la entidad.");
        }
    }

    /**
     * Crea una nueva entidad identificable.
     *
     * @param entity La entidad que se va a crear.
     * @return La entidad creada con éxito.
     * @throws RuntimeException Si hay un error al crear la entidad.
     */
    @Override
    public T create(T entity) {
        try {
            T savedEntity = repository.save(entity);
            logger.info("{} creado con éxito: {}", entity.getClass().getSimpleName(), savedEntity);
            return savedEntity;
        } catch (Exception e) {
            logger.error("Error al crear {}: {}", entity.getClass().getSimpleName(), e.getMessage(), e);
            throw new RuntimeException("Error al crear la entidad", e);
        }
    }

    /**
     * Lee una entidad identificable por su ID.
     *
     * @param id El ID de la entidad a leer.
     * @return Una instancia opcional de la entidad identificable leída, si existe.
     * @throws RuntimeException Si hay un error al leer la entidad por ID.
     */
    @Override
    public Optional<T> read(Long id) {
        try {
            Optional<T> entity = repository.findById(id);
            entity.ifPresent(e -> logger.info("{} leído con éxito por ID {}: {}", e.getClass().getSimpleName(), id, e));
            return entity;
        } catch (Exception e) {
            logger.error("Error al leer por ID {}: {}", entityClass.getSimpleName(), e.getMessage(), e);
            throw new RuntimeException("Error al leer la entidad por ID", e);
        }
    }

    /**
     * Obtiene una lista de todas las entidades identificables.
     *
     * @return La lista de todas las entidades identificables.
     * @throws RuntimeException Si hay un error al leer todas las entidades.
     */
    public List<T> readAllList() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            logger.error("Error al leer todos {}: {}", entityClass.getSimpleName(), e.getMessage(), e);
            throw new RuntimeException("Error al leer todas las entidades", e);
        }
    }

    /**
     * Obtiene un mapa de todas las entidades identificables con sus IDs como claves.
     *
     * @return El mapa de todas las entidades identificables.
     * @throws RuntimeException Si hay un error al leer todas las entidades como mapa.
     */
    @Override
    public HashMap<Long, T> readAllMap() {
        try {
            List<T> entities = readAllList();
            HashMap<Long, T> entityMap = new HashMap<>(entities.size());

            for (T entity : entities) {
                entityMap.put(entity.getId(), entity);
            }

            logger.info("Leídas con éxito {} {}.", entities.size(), entityClass.getSimpleName());
            return entityMap;
        } catch (Exception e) {
            logger.error("Error al leer todos {}: {}", entityClass.getSimpleName(), e.getMessage(), e);
            throw new RuntimeException("Error al leer todas las entidades como mapa", e);
        }
    }

    /**
     * Actualiza una entidad identificable existente.
     *
     * @param entity La entidad que se va a actualizar.
     * @return La entidad actualizada con éxito.
     * @throws IllegalArgumentException Si la entidad no tiene un ID.
     * @throws RuntimeException         Si hay un error al actualizar la entidad.
     */
    @Override
    public T update(T entity) {
        try {
            if (entity.getId() == null) {
                throw new IllegalArgumentException("No se puede actualizar una entidad sin ID.");
            }

            T updatedEntity = repository.saveAndFlush(entity);
            logger.info("{} actualizado con éxito: {}", entity.getClass().getSimpleName(), updatedEntity);
            return updatedEntity;
        } catch (Exception e) {
            logger.error("Error al actualizar {}: {}", entity.getClass().getSimpleName(), e.getMessage(), e);
            throw new RuntimeException("Error al actualizar la entidad", e);
        }
    }

    /**
     * Elimina una entidad identificable existente.
     *
     * @param entity La entidad que se va a eliminar.
     * @throws IllegalArgumentException Si la entidad no tiene un ID.
     * @throws RuntimeException         Si hay un error al eliminar la entidad.
     */
    @Override
    public void delete(T entity) {
        try {
            if (entity.getId() == null) {
                throw new IllegalArgumentException("No se puede eliminar una entidad sin ID.");
            }

            repository.delete(entity);
            logger.info("{} eliminado con éxito: {}", entity.getClass().getSimpleName(), entity);
        } catch (Exception e) {
            logger.error("Error al eliminar {}: {}", entity.getClass().getSimpleName(), e.getMessage(), e);
            throw new RuntimeException("Error al eliminar la entidad", e);
        }
    }

    /**
     * Elimina una entidad identificable por su ID.
     *
     * @param id El ID de la entidad que se va a eliminar.
     * @throws IllegalArgumentException Si el ID es nulo.
     * @throws RuntimeException         Si hay un error al eliminar la entidad por ID.
     */
    @Override
    public void deleteById(Long id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("El ID no puede ser nulo para eliminar la entidad.");
            }

            repository.deleteById(id);
            logger.info("{} con ID {} eliminado con éxito.", entityClass.getSimpleName(), id);
        } catch (Exception e) {
            logger.error("Error al eliminar por ID {}: {}", entityClass.getSimpleName(), e.getMessage(), e);
            throw new RuntimeException("Error al eliminar la entidad por ID", e);
        }
    }

    /**
     * Elimina todas las entidades identificables en la lista proporcionada.
     *
     * @param entities La lista de entidades que se van a eliminar.
     * @throws IllegalArgumentException Si la lista de entidades es nula o vacía.
     * @throws RuntimeException         Si hay un error al eliminar todas las entidades.
     */
    @Override
    public void deleteAll(List<T> entities) {
        try {
            if (entities == null || entities.isEmpty()) {
                throw new IllegalArgumentException("La lista de entidades no puede ser nula o vacía para eliminar todas las entidades.");
            }

            repository.deleteAll(entities);
            logger.info("Todos los {} eliminados con éxito.", entityClass.getSimpleName());
        } catch (Exception e) {
            logger.error("Error al eliminar todos {}: {}", entityClass.getSimpleName(), e.getMessage(), e);
            throw new RuntimeException("Error al eliminar todas las entidades", e);
        }
    }
}
