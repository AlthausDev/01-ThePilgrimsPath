package dao;

/**
 * Interfaz base para los objetos de acceso a datos (DAO).
 *
 * @param <T> El tipo de entidad que manejará el DAO.
 */
public interface BaseDAO<T> {

    /**
     * Guarda un nuevo objeto en la base de datos.
     *
     * @param entity El objeto a guardar.
     * @return true si se guarda correctamente, false en caso contrario.
     */
    boolean save(T entity);

    /**
     * Obtiene un objeto por su identificador único.
     *
     * @param id El identificador único del objeto.
     * @return El objeto encontrado, o null si no existe.
     */
    T getById(long id);

    // Puedes agregar más métodos según sea necesario para tu aplicación.
}
