package dao;

import entities.Parada;

public interface ParadaDAO {
    Parada getById(long id);
    void save(Parada parada);
    void update(Parada parada);
    void delete(Parada parada);
}
