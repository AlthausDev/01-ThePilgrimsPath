package com.althaus.dev.GestorPeregrinos.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface CoreService<T> {
    T create(T entity);

    T read(T entity);

    Optional<T> readById(Long id);

    HashMap<Long, T> readAll(T entity);

    T update(T entity);

    void delete(T entity);

    void deleteById(Long id);

    void deleteAll(List<T> entity);

}
