package dao;

import entities.AdminParada;

public interface AdminParadaDAO {

	AdminParada getById(long id);

    void insert(AdminParada adminParada);

    void update(AdminParada adminParada);

    void delete(AdminParada adminParada);
}
