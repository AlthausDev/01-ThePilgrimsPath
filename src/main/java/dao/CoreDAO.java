package dao;

import database.FactoryConexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public abstract class CoreDAO <T>{

    protected static Connection conexion;

    public CoreDAO (){
        try {
			this.conexion = FactoryConexion.getInstance().getConexion();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
    }
    public abstract void create (T entidad);

    public abstract T read(long id);

    public abstract HashMap<Long, T> readAll ();

    public abstract void update (T entidad);

    public abstract void delete (long id);

    protected abstract T getResultSet(ResultSet rs);

}
