package co.edu.uco.victusresidencias.data.dao.impl.postgresql;

import java.sql.Connection;




import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.victusresidencias.crosscutting.helpers.ObjectHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.TextHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.UUIDHelper;
import co.edu.uco.victusresidencias.crosscutting.exceptions.DataVictusResidenciasException;
import co.edu.uco.victusresidencias.data.dao.AdministratorDAO;
import co.edu.uco.victusresidencias.data.dao.CountryDAO;
import co.edu.uco.victusresidencias.data.dao.impl.sql.SqlDAO;
import co.edu.uco.victusresidencias.entity.AdministratorEntity;
import co.edu.uco.victusresidencias.entity.CityEntity;
import co.edu.uco.victusresidencias.entity.CountryEntity;
import co.edu.uco.victusresidencias.entity.StateEntity;

final class AdministratorPostgreSQLDAO extends SqlDAO implements AdministratorDAO {
	
	public AdministratorPostgreSQLDAO(Connection connection) {
		super(connection);
	}	
	
	@Override
	public AdministratorEntity fingByID(UUID id) {
		var administratorEntityFilter = new AdministratorEntity();
	    administratorEntityFilter.setId(id);
	    
	    var result = findByFilter(administratorEntityFilter);
	    return (result.isEmpty()) ? null : result.get(0); // Retorna null si no encuentra
	}
	

	@Override
	public List<AdministratorEntity> findAll() {
		AdministratorEntity pruebaAdministrador = new AdministratorEntity();
		System.out.println("el administrador nuevo tiene el id " + pruebaAdministrador.getId());
		return findByFilter(new AdministratorEntity());  //default 0000 y name =""
	}
	

	@Override
	public List<AdministratorEntity> findByFilter(AdministratorEntity filter) { //filter datos 
		System.out.println("La carga paso por aqui del filter");
		final var statement = new StringBuilder(); //sentencia SQL
	    final var parameters = new ArrayList<>();  // ?
	    final var resultSelect = new ArrayList<AdministratorEntity>(); //select para una lista entity
	    var statementWasPrepared = false;	//sentencia fue preparada?	 
	    
	    // Select
	    createSelect(statement);
//		SELECT id, name FROM country ORDER BY name ASC
	    // From
	    createFrom(statement);
	    
	    // Where
	    createWhere(statement, filter, parameters);
	    
	    // Order By
	    createOrderBy(statement);
	    
	    try (var preparedStatement = getConnection().prepareStatement(statement.toString())) {
	        for (var arrayIndex = 0; arrayIndex < parameters.size(); arrayIndex++) {
	            var statementIndex = arrayIndex + 1;
	            preparedStatement.setObject(statementIndex, parameters.get(arrayIndex));
	        }
	        System.out.println("Sentencia preparada " + statement);
	        
	        statementWasPrepared = true;
	        
	        final var result = preparedStatement.executeQuery();
	        while (result.next()) {
	            var administratorEntityTmp = new AdministratorEntity();
	            //var stateEntityTmp = new StateEntity();
	            administratorEntityTmp.setId(UUID.fromString(result.getString("id")));
	            System.out.println("id que inserta a la lista " + UUID.fromString(result.getString("id")));
	            
	            administratorEntityTmp.setName(result.getString("name"));
	            
	            //stateEntityTmp.setId(UUID.fromString(result.getString("state")));
	            
	            //countryEntityTmp.setState(stateEntityTmp);
	            
	            resultSelect.add(administratorEntityTmp);		
	        }
	    } catch (final SQLException exception) {
	        var userMessage = "Se ha presentado un problema tratando de llevar a cabo la consulta de los administradores.";
	        var technicalMessage = statementWasPrepared ? 
	            "Problema ejecutando la consulta de administradores en la base de datos." : 
	            "Problema preparando la consulta de administradores en la base de datos.";
	        
	        throw DataVictusResidenciasException.crear(userMessage, technicalMessage, exception);
	    }
	    
	    return resultSelect;
	}
	
	private void createSelect(final StringBuilder statement) {
		statement.append("SELECT id, name ");
	}
	
	private void createFrom(final StringBuilder statement) {
		statement.append("FROM administrator ");
	}

	private void createWhere(final StringBuilder statement, 
            final AdministratorEntity filter, 
            final List<Object> parameters) {//filter.getId = 0000000
			if (!UUIDHelper.isDefault(filter.getId())) { // Se asegura de que el ID no sea el valor predeterminado
				System.out.println("Sentencia preparada con where " + filter.getId());
				statement.append("WHERE id = ? ");
				parameters.add(filter.getId());
			} else if (!TextHelper.isEmpty(filter.getName())) { // Condición para filtro de nombre
				statement.append("WHERE name = ? ");
				parameters.add(filter.getName());
			}//este if es para filtar por id o por nombre
	}
	
	private void createOrderBy(final StringBuilder statement) {
		statement.append("ORDER BY name ASC");
	}

	@Override
	public void create(AdministratorEntity data) {
		
		// Verificar si ya existe un país con el mismo nombre
	    AdministratorEntity filter = new AdministratorEntity();
	    filter.setName(data.getName());
	    if (!findByFilter(filter).isEmpty()) {
	        throw DataVictusResidenciasException.crear(
	            "El administrador ya existe", "No se puede crear un administrador con el nombre duplicado: " + data.getName());
	    }
	    
	    final StringBuilder statement = new StringBuilder();
	    statement.append("INSERT INTO country(id, name) VALUES (?, ?)");

	    // Verificar si el ID es el UUID predeterminado, y si es así, generar uno nuevo.
	    if (UUIDHelper.isDefault(data.getId())) {
	        data.setId(UUIDHelper.generate()); // Genera un UUID único si es el valor predeterminado.
	    }

	    try (final var preparedStatement = getConnection().prepareStatement(statement.toString())) {
	        preparedStatement.setObject(1, data.getId());
	        preparedStatement.setString(2, data.getName());

	        preparedStatement.executeUpdate();
	        System.out.println("Se creó el administrador con el nombre " + data.getName() + " exitosamente");

	    } catch (final SQLException exception) {
	        var userMessage = "Se ha presentado un problema tratando de llevar a cabo el registro de la información del nuevo administrador. Por favor intente de nuevo y si el problema persiste reporte la novedad...";
	        var technicalMessage = "Se ha presentado un problema al tratar de registrar la información del nuevo administrador en la base de datos SQL Server. Por favor valide el log de errores para encontrar mayores detalles del problema presentado...";

	        throw DataVictusResidenciasException.crear(userMessage, technicalMessage, exception);
	    }
	}

//	@Override
//	public void create(CountryEntity data) {
//		final StringBuilder statement = new StringBuilder();
//		statement.append("INSERT INTO country(id, name) VALUES (?, ?)");
// 
//		try (final var preparedStatement = getConnection().prepareStatement(statement.toString())) {
// 
//			preparedStatement.setObject(1, data.getId());
//			preparedStatement.setString(2, data.getName());
// 
//			preparedStatement.executeUpdate();
//			System.out.println("Se creo el pais con el nombre "+ data.getName()+ " Exitosamente");
// 
//		} catch (final SQLException exception) {
//			var userMessage = "Se ha presentado un problema tratando de llevar a cabo el registro de la información del nuevo país. Por favor intente de nuevo y si el problema persiste reporte la novedad...";
//			var technicalMessage = "Se ha presentado un problema al tratar de registrar la informaciòn del nuevo país en la base de datos SQL Server. Por favor valide el log de errores para encontrar mayores detalles del problema presentado...";
// 
//			throw DataVictusResidenciasException.crear(userMessage, technicalMessage, exception);
//		}
//		
//	}

	@Override
	public void delete(UUID data) {
		final StringBuilder statement = new StringBuilder();
	    statement.append("DELETE FROM country WHERE id = ?");

	    try (final var preparedStatement = getConnection().prepareStatement(statement.toString())) {

	        preparedStatement.setObject(1, data);

	        preparedStatement.executeUpdate();

	    } catch (final SQLException exception) {
	        var userMessage = "Se ha presentado un problema tratando de eliminar el administrador seleccionado. Por favor intente de nuevo y si el problema persiste reporte la novedad...";
	        var technicalMessage = "Se ha presentado un problema al tratar de eliminar el administrador en la base de datos SQL Server. Por favor valide el log de errores para encontrar mayores detalles del problema presentado...";

	        throw DataVictusResidenciasException.crear(userMessage, technicalMessage, exception);
	    }
	}

	@Override
	public void update(AdministratorEntity data) {
		final StringBuilder statement = new StringBuilder();
	    statement.append("UPDATE country SET name = ? WHERE id = ?");

	    try (final var preparedStatement = getConnection().prepareStatement(statement.toString())) {

	        preparedStatement.setString(1, data.getName());
	        preparedStatement.setObject(2, data.getId());

	        preparedStatement.executeUpdate();

	    } catch (final SQLException exception) {
	        var userMessage = "Se ha presentado un problema tratando de actualizar la información del administrador. Por favor intente de nuevo y si el problema persiste reporte la novedad...";
	        var technicalMessage = "Se ha presentado un problema al tratar de actualizar la información del administrador en la base de datos SQL Server. Por favor valide el log de errores para encontrar mayores detalles del problema presentado...";

	        throw DataVictusResidenciasException.crear(userMessage, technicalMessage, exception);
	    }
		
	}

	
}
