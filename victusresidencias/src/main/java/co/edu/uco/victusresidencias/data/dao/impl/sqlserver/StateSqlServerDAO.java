package co.edu.uco.victusresidencias.data.dao.impl.sqlserver;

import java.sql.Statement;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.crosscutting.helpers.UUIDHelper;
import co.edu.uco.victusresidencias.data.dao.StateDAO;
import co.edu.uco.victusresidencias.entity.CountryEntity;
import co.edu.uco.victusresidencias.entity.StateEntity;

public class StateSqlServerDAO implements StateDAO {
	
	private final String url = "jdbc:postgresql://localhost:5432/baseJavaPractica";
	private final String user = "postgres";
	private final String password = "123456";

	@Override
	public StateEntity fingByID(UUID id) {
//		String instruccionSQL = "SELECT id, nombre, pais FROM state WHERE id = ?";
//	    StateEntity state = null; // Instancia para almacenar el resultado
//	    
//	    try (Connection connection = DriverManager.getConnection(url, user, password)) {
//	        if (connection != null) {
//	            PreparedStatement miSentencia = connection.prepareStatement(instruccionSQL);
//	            miSentencia.setString(1, id.toString()); // Asignar el UUID como String
//	            
//	            ResultSet miResultset = miSentencia.executeQuery();
//	            
//	            if (miResultset.next()) { // Verificar si hay resultado
//	                // Crear un nuevo objeto CountryEntity y asignar los valores
//	                state = new StateEntity();
//	                state.setId(UUIDHelper.convertToUUID(miResultset.getString("id"))); // Asignar el UUID
//	                state.setName(miResultset.getString("nombre")); // Asignar el nombre
//	             // Obtener el country_id y buscar el país asociado
//	                UUID countryId = UUIDHelper.convertToUUID(miResultset.getString("pais"));
//	                
//	                // Obtener la entidad Country utilizando el DAO de países
//	                CountrySqlServerDAO countrySql = new CountrySqlServerDAO();
//	                CountryEntity country = countrySql.fingByID(countryId); // Método para obtener el país
//	                
//	                // Asignar el país al estado
//	                state.setCountry(country);
//	                //state.setCountry(miResultset.getString("pais")));
//	            }
//	            
//	            miResultset.close();
//	        } else {
//	            System.out.println("Failed to connect to the database");
//	        }
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    }
//	    
//	    // Retornar el objeto CountryEntity (si no se encuentra, retornará null)
//	    return state;
		return null;
	}

	@Override
	public List<StateEntity> findAll() {
//		String instruccionSQL = "SELECT id, nombre, pais FROM state"; // Cambiar a la tabla state
//	    List<StateEntity> stateList = new ArrayList<>(); // Lista para almacenar los resultados
//	    
//	    try (Connection connection = DriverManager.getConnection(url, user, password)) {
//	        if (connection != null) {
//	            Statement miSentencia = connection.createStatement();
//	            ResultSet miResultset = miSentencia.executeQuery(instruccionSQL);
//	            
//	            while (miResultset.next()) {
//	                // Crear un nuevo objeto StateEntity y asignar los valores
//	                StateEntity state = new StateEntity();
//	                
//	                // Convertir el id a UUID desde el String
//	                UUID id = UUIDHelper.convertToUUID(miResultset.getString("id"));
//	                state.setId(id); // Asignar el UUID
//	                
//	                // Asignar el nombre del estado
//	                state.setName(miResultset.getString("nombre"));
//	                
//	                // Obtener el country_id y buscar el país asociado
//	                UUID countryId = UUIDHelper.convertToUUID(miResultset.getString("pais"));
//	                
//	                // Obtener la entidad Country utilizando el DAO de países
//	                CountrySqlServerDAO countrySql = new CountrySqlServerDAO();
//	                CountryEntity country = countrySql.fingByID(countryId); // Método para obtener el país
//	                
//	                // Asignar el país al estado
//	                state.setCountry(country);
//	                
//	                // Agregar el objeto StateEntity a la lista
//	                stateList.add(state);
//	            }
//	            
//	            miResultset.close();
//	        } else {
//	            System.out.println("Failed to connect to the database");
//	        }
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    }
//	    
//	    // Retornar la lista de StateEntity
//	    return stateList;
		return null;
	}

	@Override
	public List<StateEntity> findByFilter(StateEntity filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
//	public static void main(String[] args) {
//		PrintStream consola = System.out;
//		// Crear una instancia de la clase donde está el método findAll()
//      StateSqlServerDAO stateService = new StateSqlServerDAO();
//
//      // Llamar a findAll() para obtener la lista de CountryEntity
//      List<StateEntity> states = stateService.findAll();
//
//      // Verificar si la lista no está vacía y recorrerla
//      if (states != null && !states.isEmpty()) {
//          for (StateEntity state : states) {
//              // Mostrar los atributos de cada CountryEntity
//              System.out.println("ID: " + state.getId() + ", -NOMBRE: " + state.getName()+", -PAIS: "+ state.getCountry().getName());
//          }
//      } else {
//          System.out.println("No se encontraron departamentos.");
//      }
//      	//CountrySqlServerDAO countryService = new CountrySqlServerDAO();
//  		UUID idd = UUIDHelper.convertToUUID("ac50c53a-93f2-49f2-9820-0472d70e7d78");
//  		StateEntity stateId = stateService.fingByID(idd);
//  		consola.println("Buscando por id");
//  		consola.println("ID: " +stateId.getId()+", -NOMBRE: "+ stateId.getName()+", -PAIS: "+ stateId.getCountry().getName());
//	}
	

}
