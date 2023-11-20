package dao;

import entities.AdminParada;
import entities.Carnet;
import entities.Parada;
import entities.Perfil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class AdminParadaDAOImpl implements AdminParadaDAO {
  
	 private Connection connection;

	    public AdminParadaDAOImpl(Connection connection) {
	        this.connection = connection;
	    }

	    @Override
	    public AdminParada getById(long id) {
	    	AdminParada adminParada = null;
	        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Carnet WHERE idPeregrino = ?")) {
	            statement.setLong(1, id);
	            ResultSet resultSet = statement.executeQuery();
	            if (resultSet.next()) {
	                adminParada = mapResultSetToCarnet(resultSet);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return adminParada;
	    }	    


		@Override
	    public void insert(AdminParada adminParada) {
	        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO AdminParada VALUES (?, ?)")) {
	            statement.setLong(1, adminParada.getId());
	            statement.setString(2, adminParada.getName());
	            //statement.setPerfil(3, adminParada.getPerfil());	          
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public void update(AdminParada adminParada) {
	        try (PreparedStatement statement = connection.prepareStatement(
	                "UPDATE AdminParada SET id = ?, nombre = ? WHERE idAdminParadas = ?")) {
	        	statement.setLong(1, adminParada.getId());
	            statement.setString(2, adminParada.getName());
	            //statement.setPerfil(3, adminParada.getPerfil());
	            statement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public void delete(AdminParada adminParada) {
	        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM AdminParada WHERE idAdminParadas = ?")) {
	        	statement.setLong(1, adminParada.getId());
	            statement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    private AdminParada mapResultSetToCarnet(ResultSet resultSet) {
			// TODO Auto-generated method stub
			return null;
		}
	   

}
