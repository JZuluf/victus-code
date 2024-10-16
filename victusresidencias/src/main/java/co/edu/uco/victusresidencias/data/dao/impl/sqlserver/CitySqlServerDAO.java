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
import co.edu.uco.victusresidencias.data.dao.CityDAO;
import co.edu.uco.victusresidencias.crosscutting.exceptions.DataVictusResidenciasException;
import co.edu.uco.victusresidencias.data.dao.impl.sql.SqlDAO;
import co.edu.uco.victusresidencias.entity.CityEntity;
import co.edu.uco.victusresidencias.entity.CountryEntity;
import co.edu.uco.victusresidencias.entity.StateEntity;

public final class CitySqlServerDAO extends SqlDAO implements CityDAO {
	
	protected CitySqlServerDAO(final Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

//	private final String url = "jdbc:sqlserver://ucobet-server.database.windows.net:1433;database=ucobet-db";
//	private final String user = "ucobetdbuser";
//	private final String password = "uc0b3tdbus3r!";
	
	private final String url = "jdbc:postgresql://localhost:5432/baseJavaPractica";
	private final String user = "postgres";
	private final String password = "123456";

	@Override
	public CityEntity fingByID(UUID id) {
		try(Connection connection = DriverManager.getConnection(url, user, password);){
			if(connection != null) {
				
				
			}else {
				System.out.print("Failed to connect Post");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CityEntity> findAll() {
		String instruccionSQL = "SELECT id, nombre, departamento FROM city"; // Cambiar a la tabla state
	    List<CityEntity> cityList = new ArrayList<>(); // Lista para almacenar los resultados
	    
	    try (Connection connection = DriverManager.getConnection(url, user, password)) {
	        if (connection != null) {
	            Statement miSentencia = connection.createStatement();
	            ResultSet miResultset = miSentencia.executeQuery(instruccionSQL);
	            
	            while (miResultset.next()) {
	                // Crear un nuevo objeto StateEntity y asignar los valores
	                CityEntity city = new CityEntity();
	                
	                // Convertir el id a UUID desde el String
	                UUID id = UUIDHelper.convertToUUID(miResultset.getString("id"));
	                city.setId(id); // Asignar el UUID
	                
	                // Asignar el nombre del estado
	                city.setName(miResultset.getString("nombre"));
	                
	                // Obtener el state_id y buscar el departamento asociado
	                UUID countryId = UUIDHelper.convertToUUID(miResultset.getString("departamento"));
	                
	                // Obtener la entidad departamento utilizando el DAO de países
	                StateSqlServerDAO stateSql = new StateSqlServerDAO();
	                StateEntity state = stateSql.fingByID(countryId); // Método para obtener el país
	                
	                // Asignar el departemento a la ciudad
	                city.setState(state);
	                
	                // Agregar el objeto CityEntity a la lista
	                cityList.add(city);
	            }
	            
	            miResultset.close();
	        } else {
	            System.out.println("Failed to connect to the database");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    // Retornar la lista de CityEntity
	    return cityList;
	}

	@Override
	public List<CityEntity> findByFilter(CityEntity filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(CityEntity data) {
		final StringBuilder statement = new StringBuilder();
		statement.append("INSERT INTO City(id, name, state) VALUES (?, ?, ?)");
 
		try (final var preparedStatement = getConnection().prepareStatement(statement.toString())) {
 
			preparedStatement.setObject(1, data.getId());
			preparedStatement.setString(2, data.getName());
			preparedStatement.setObject(3, data.getState().getId());
 
			preparedStatement.executeUpdate();
 
		} catch (final SQLException exception) {
			var userMessage = "Se ha presentado un problema tratando de llevar a cabo el registro de la información del nuevo país. Por favor intente de nuevo y si el problema persiste reporte la novedad...";
			var technicalMessage = "Se ha presentado un problema al tratar de registrar la informaciòn del nuevo país en la base de datos SQL Server. Por favor valide el log de errores para encontrar mayores detalles del problema presentado...";
 
			throw DataVictusResidenciasException.crear(userMessage, technicalMessage, exception);
		}
//		try(Connection connection = DriverManager.getConnection(url, user, password);){
//			if(connection != null) {
//				System.out.print("Connection succesfull");
//			
//				String instruccionSQL="INSERT INTO city (id,nombre, departamento) VALUES (?,?,?)";
//				
//				PreparedStatement miSentencia = connection.prepareStatement(instruccionSQL);
//				
//				miSentencia.setString(1, data.getId().toString());
//				miSentencia.setString(2, data.getName());
//				miSentencia.setString(3, data.getState().getId().toString());
//				
//				System.out.print("\n Actualizo la base");
//				miSentencia.executeQuery();
//				
//			}else {
//				System.out.print("Failed to connect Post");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
	}

	@Override
	public void delete(CityEntity data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(UUID data) {
		// TODO Auto-generated method stub
		
	}
	
//	public static void main(String[] args) {
////		PrintStream consola = System.out;
////		// Crear una instancia de la clase donde está el método findAll()
////      CitySqlServerDAO cityService = new CitySqlServerDAO();
////
////      // Llamar a findAll() para obtener la lista de CityEntity
////      List<CityEntity> cities = cityService.findAll();
////
////      // Verificar si la lista no está vacía y recorrerla
////      if (cities != null && !cities.isEmpty()) {
////          for (CityEntity city : cities) {
////              // Mostrar los atributos de cada CountryEntity
////              System.out.println("ID: " + city.getId() + ", -NOMBRE: " + city.getName()+", -DEPARTAMENTO: "+ city.getState().getName());
////          }
////      } else {
////          System.out.println("No se encontraron departamentos.");
////      }
////      	//CountrySqlServerDAO countryService = new CountrySqlServerDAO();
////  		UUID idd = UUIDHelper.convertToUUID("ac50c53a-93f2-49f2-9820-0472d70e7d78");
////  		CityEntity cityId = cityService.fingByID(idd);
////  		consola.println("Buscando por id");
////  		consola.println("ID: " +cityId.getId()+", -NOMBRE: "+ cityId.getName()+", -DEPARTAMENTO: "+ cityId.getState().getName());
////		CitySqlServerDAO cityService = new CitySqlServerDAO();
////		UUID idd = UUIDHelper.convertToUUID("ac50c53a-93f2-49f2-9820-0472d70e7d78");
////		CityEntity cityId = cityService.fingByID(idd);
////  		cityService.create(cityId);
//		CitySqlServerDAO cityService = new CitySqlServerDAO();
//		CityEntity city = new CityEntity();
//		StateEntity state = new StateEntity();
//		UUID idCity = UUIDHelper.generate();
//		city.setId(idCity);
//		city.setName("Boston");
//		state.setId(UUIDHelper.convertToUUID("fa19e967-6416-4eb1-b8b6-f1357dc38889"));
//		city.setState(state);
//		cityService.create(city);
//  		
//	}

}
