package dao;

import entities.Estancia;

import java.util.List;

public interface EstanciaDAO {
    Estancia getById(long id);

    List<Estancia> getAll();

    void save(Estancia estancia);

    void update(Estancia estancia);

    void delete(Estancia estancia);
}
