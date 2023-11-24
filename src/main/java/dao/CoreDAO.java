package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import persistencia.MySqlConexion;

public abstract class CoreDAO <T>{

    private Connection conexion;

    public CoreDAO (){
        this.conexion = MySqlConexion.getInstance().getConexion();
    }
    public abstract void create (T entidad);

    public abstract T read(int id);

    public abstract HashMap<Integer, T> readAll ();

    public abstract void update (T entidad);

    public abstract void delete (int id);

    protected abstract T getResultSet(ResultSet rs);


}
