package com.althaus.dev.GestorPeregrinos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Interfaz base para repositorios genéricos de entidades.
 *
 * @param <T> Tipo de entidad.
 * @param <ID> Tipo de ID de la entidad.
 */
@NoRepositoryBean
public interface CoreRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
}
