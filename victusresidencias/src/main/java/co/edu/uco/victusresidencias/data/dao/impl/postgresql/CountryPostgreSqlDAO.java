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
import co.edu.uco.victusresidencias.data.dao.CountryDAO;
import co.edu.uco.victusresidencias.data.dao.impl.sql.SqlDAO;
import co.edu.uco.victusresidencias.entity.CountryEntity;

final class CountryPostgreSQLDAO extends SqlDAO implements CountryDAO {
	
	public CountryPostgreSQLDAO(Connection connection) {
		super(connection);
	}	
	
	@Override
	public CountryEntity fingByID(UUID id) {
		var countryEntityFilter = new CountryEntity();
	    countryEntityFilter.setId(id);
	    
	    var result = findByFilter(countryEntityFilter);
	    return (result.isEmpty()) ? null : result.get(0); // Retorna null si no encuentra
	}
	

	@Override
	public List<CountryEntity> findAll() {
		return findByFilter(new CountryEntity());
	}

	@Override
	public List<CountryEntity> findByFilter(final CountryEntity filter) {
		final var statement = new StringBuilder();
		final var parameters = new ArrayList<>();
		final var resultSelect = new ArrayList<CountryEntity>();
		var statementWasPrepared = false;		 
		
		// Select
		createSelect(statement);
		
		// From
		createFrom(statement);
		
		// Where
		createWhere(statement, filter, parameters);
		
		// Order By
		createOrderBy(statement);
		
		try (var preparedStatement = getConnection().prepareStatement(statement.toString())){
			for (var arrayIndex = 0; arrayIndex < parameters.size(); arrayIndex++) {
				var statementIndex = arrayIndex + 1;
				preparedStatement.setObject(statementIndex, parameters.get(arrayIndex));
			}
			
			statementWasPrepared = true;
			
			final var result = preparedStatement.executeQuery();
			while(result.next()) {
				var countryEntityTmp = new CountryEntity();
				countryEntityTmp.setId(UUIDHelper.convertToUUID(result.getString("id")));
				countryEntityTmp.setName(result.getString("name"));
				
				resultSelect.add(countryEntityTmp);		
			}
		} catch (final SQLException exception) {
			var userMessage = "Se ha presentado un problema tratando de llevar a cabo la "
					+ "consulta de los paises por el filtro deseado. "
					+ "Por favor intente de nuevo y si el problema persiste reporte la novedad ...";
			var technicalMessege = "Se ha presentado un problema al tratar de consultar la "
					+ "informacion de los paises por el filtro deseado en la base de datos SQL server tratando de preparar "
					+ "la sentencia SQL que se iba a ejecutar. Por favor valide el log de errores para encontrar "
					+ "mayores detalles del problema presentado...";
			if (statementWasPrepared) {
				technicalMessege = "Se ha presentado un problema al tratar de consultar "
						+ "la informacion de los paises por el filtro deseado en la "
						+ "base de datos SQL server tratando de ejecutar la sentencia SQL. Por favor valide el log de errores para encontrar mayores detalles del problema presentado...";
			}
			throw DataVictusResidenciasException.crear(userMessage, technicalMessege, exception);
		}
	    return resultSelect;//resultSelect; 
	}
	
	private void createSelect(final StringBuilder statement) {
		statement.append("SELECT id, name ");
	}
	
	private void createFrom(final StringBuilder statement) {
		statement.append("FROM country ");
	}
	
	private void createWhere(final StringBuilder statement, 
            final CountryEntity filter, 
            final List<Object> parameters) {
			if (!UUIDHelper.isDefault(filter.getId())) { // Se asegura de que el ID no sea el valor predeterminado
				statement.append("WHERE id = ? ");
				parameters.add(filter.getId());
			} else if (!TextHelper.isEmpty(filter.getName())) { // Condición para filtro de nombre
				statement.append("WHERE name = ? ");
				parameters.add(filter.getName());
			}
	}
	
	private void createOrderBy(final StringBuilder statement) {
		statement.append("ORDER BY name ASC");
	}

	@Override
	public void create(CountryEntity data) {
		final StringBuilder statement = new StringBuilder();
		statement.append("INSERT INTO country(id, name) VALUES (?, ?)");
 
		try (final var preparedStatement = getConnection().prepareStatement(statement.toString())) {
 
			preparedStatement.setObject(1, data.getId());
			preparedStatement.setString(2, data.getName());
 
			preparedStatement.executeUpdate();
			System.out.println("Se creo el pais con el nombre "+ data.getName()+ " Exitosamente");
 
		} catch (final SQLException exception) {
			var userMessage = "Se ha presentado un problema tratando de llevar a cabo el registro de la información del nuevo país. Por favor intente de nuevo y si el problema persiste reporte la novedad...";
			var technicalMessage = "Se ha presentado un problema al tratar de registrar la informaciòn del nuevo país en la base de datos SQL Server. Por favor valide el log de errores para encontrar mayores detalles del problema presentado...";
 
			throw DataVictusResidenciasException.crear(userMessage, technicalMessage, exception);
		}
		
	}

	@Override
	public void delete(UUID data) {
		final StringBuilder statement = new StringBuilder();
	    statement.append("DELETE FROM country WHERE id = ?");

	    try (final var preparedStatement = getConnection().prepareStatement(statement.toString())) {

	        preparedStatement.setObject(1, data);

	        preparedStatement.executeUpdate();

	    } catch (final SQLException exception) {
	        var userMessage = "Se ha presentado un problema tratando de eliminar la ciudad seleccionada. Por favor intente de nuevo y si el problema persiste reporte la novedad...";
	        var technicalMessage = "Se ha presentado un problema al tratar de eliminar la ciudad en la base de datos SQL Server. Por favor valide el log de errores para encontrar mayores detalles del problema presentado...";

	        throw DataVictusResidenciasException.crear(userMessage, technicalMessage, exception);
	    }
	}

	@Override
	public void update(CountryEntity data) {
		final StringBuilder statement = new StringBuilder();
	    statement.append("UPDATE country SET name = ? WHERE id = ?");

	    try (final var preparedStatement = getConnection().prepareStatement(statement.toString())) {

	        preparedStatement.setString(1, data.getName());
	        preparedStatement.setObject(2, data.getId());

	        preparedStatement.executeUpdate();

	    } catch (final SQLException exception) {
	        var userMessage = "Se ha presentado un problema tratando de actualizar la información de la ciudad. Por favor intente de nuevo y si el problema persiste reporte la novedad...";
	        var technicalMessage = "Se ha presentado un problema al tratar de actualizar la información de la ciudad en la base de datos SQL Server. Por favor valide el log de errores para encontrar mayores detalles del problema presentado...";

	        throw DataVictusResidenciasException.crear(userMessage, technicalMessage, exception);
	    }
		
	}

	
}
