package dao;

import entities.Carnet;

public interface CarnetDAO {

    void insert(Carnet carnet);

    void update(Carnet carnet);

    void delete(Carnet carnet);

    Carnet getByPeregrinoId(long id);
}
