package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import database.MySqlConexion;

public abstract class CoreDAO <T>{

    protected static Connection conexion;

    public CoreDAO (){
        conexion = MySqlConexion.getInstance().getConexion();
    }
    public abstract void create (T entidad);

    public abstract T read(long id);

    public abstract HashMap<Long, T> readAll ();

    public abstract void update (T entidad);

    public abstract void delete (long id);

    protected abstract T getResultSet(ResultSet rs);

}
