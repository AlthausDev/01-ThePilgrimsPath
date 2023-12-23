package com.althaus.dev.GestorPeregrinos.service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public abstract class CoreServiceImpl<T> implements CoreService<T> {

    private static final Logger logger = LoggerFactory.getLogger(CoreServiceImpl.class);
    private final JpaRepository<T, Long> repository;
    private final Class<T> entityClass;

    public CoreServiceImpl(JpaRepository<T, Long> repository) {
        this.repository = repository;

        Class<?> clazz = getClass();
        Type type = clazz.getGenericSuperclass();

        while (!(type instanceof ParameterizedType)) {
            if (clazz.getSuperclass() == null) {
                throw new IllegalArgumentException("No se pudo determinar el tipo de la entidad.");
            }

            clazz = clazz.getSuperclass();
            type = clazz.getGenericSuperclass();
        }

        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type[] typeArguments = parameterizedType.getActualTypeArguments();

        if (typeArguments.length > 0 && typeArguments[0] instanceof Class) {
            this.entityClass = (Class<T>) typeArguments[0];
        } else {
            throw new IllegalArgumentException("No se pudo determinar el tipo de la entidad.");
        }
    }


    @Override
    public T create(T entity) {
        try {
            T savedEntity = repository.save(entity);
            logger.info("{} creado con éxito: {}", entity.getClass().getSimpleName(), savedEntity);
            return savedEntity;
        } catch (Exception e) {
            logger.error("Error al crear {}: {}", entity.getClass().getSimpleName(), e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public T read(T entity) {
        // Implementa la lógica para el método read si es necesario para tu aplicación
        return null;
    }

    @Override
    public Optional<T> readById(Long id) {
        try {
            Optional<T> entity = repository.findById(id);
            if (entity.isPresent()) {
                logger.info("{} leído con éxito por ID {}: {}", entity.get().getClass().getSimpleName(), id, entity.get());
            } else {
                logger.warn("{} no encontrado por ID: {}", entityClass.getSimpleName(), id);
            }
            return entity;
        } catch (Exception e) {
            logger.error("Error al leer por ID {}: {}", entityClass.getSimpleName(), e.getMessage(), e);
            throw e;
        }
    }


    @Override
    public HashMap<Long, T> readAll(T entity) {
        try {
            List<T> entities = repository.findAll();
            HashMap<Long, T> entityMap = new HashMap<>();

            for (T e : entities) {
                Long entityId = getIdFromEntity(e);
                entityMap.put(entityId, e);
            }

            logger.info("Todos los {} leídos con éxito.", entity.getClass().getSimpleName());
            return entityMap;
        } catch (Exception e) {
            logger.error("Error al leer todos {}: {}", entity.getClass().getSimpleName(), e.getMessage(), e);
            throw e;
        }
    }

    private Long getIdFromEntity(T entity) {
        try {
            Method getIdMethod = entity.getClass().getMethod("getId");
            return (Long) getIdMethod.invoke(entity);
        } catch (Exception ex) {
            logger.error("Error al obtener el ID de {}: {}", entity.getClass().getSimpleName(), ex.getMessage(), ex);
            throw new RuntimeException("Error al obtener el ID de la entidad", ex);
        }
    }


    @Override
    public T update(T entity) {
        try {
            T updatedEntity = repository.save(entity);
            logger.info("{} actualizado con éxito: {}", entity.getClass().getSimpleName(), updatedEntity);
            return updatedEntity;
        } catch (Exception e) {
            logger.error("Error al actualizar {}: {}", entity.getClass().getSimpleName(), e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void delete(T entity) {
        try {
            repository.delete(entity);
            logger.info("{} eliminado con éxito: {}", entity.getClass().getSimpleName(), entity);
        } catch (Exception e) {
            logger.error("Error al eliminar {}: {}", entity.getClass().getSimpleName(), e.getMessage(), e);
            throw e;
        }
    }
    @Override
    public void deleteById(Long id) {
        try {
            repository.deleteById(id);
            logger.info("{} con ID {} eliminado con éxito.", entityClass.getSimpleName(), id);
        } catch (Exception e) {
            logger.error("Error al eliminar por ID {}: {}", entityClass.getSimpleName(), e.getMessage(), e);
            throw e;
        }
    }


    @Override
    public void deleteAll(List<T> entities) {
        try {
            repository.deleteInBatch(entities);
            logger.info("Todos los {} eliminados con éxito.", entityClass.getSimpleName());
        } catch (Exception e) {
            logger.error("Error al eliminar todos {}: {}", entityClass.getSimpleName(), e.getMessage(), e);
            throw e;
        }
    }

}
