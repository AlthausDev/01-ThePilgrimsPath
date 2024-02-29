package com.althaus.dev.GestorPeregrinos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Interfaz base para repositorios genéricos de entidades.
 *
 * <p>
 * Esta interfaz extiende la interfaz JpaRepository de Spring Data JPA y está marcada con la anotación
 * NoRepositoryBean para indicar que no se debe crear un bean de repositorio para esta interfaz.
 * </p>
 *
 * <p>
 * El autor de esta interfaz es Althaus_Dev.
 * </p>
 *
 * @param <T>  Tipo de entidad.
 * @param <ID> Tipo de ID de la entidad.
 */
@NoRepositoryBean
public interface CoreRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
}
