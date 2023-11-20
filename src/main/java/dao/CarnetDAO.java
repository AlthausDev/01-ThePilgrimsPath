package dao;

import entities.Carnet;

public interface CarnetDAO {
    Carnet getById(long id);

    void save(Carnet carnet);

    void update(Carnet carnet);

    void delete(Carnet carnet);
}
