package dao;

import entities.Peregrino;

import java.util.List;

public interface PeregrinoDAO {
    Peregrino getById(long id);

    List<Peregrino> getAll();

    void save(Peregrino peregrino);

    void update(Peregrino peregrino);

    void delete(Peregrino peregrino);
}
