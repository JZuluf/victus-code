package co.edu.uco.victusresidencias.data.dao.impl.postgresql;



import java.sql.Connection;
import java.util.UUID;

import co.edu.uco.victusresidencias.crosscutting.helpers.SqlConnectionHelper;
<<<<<<< HEAD
import co.edu.uco.victusresidencias.crosscutting.helpers.UUIDHelper;
=======
import co.edu.uco.victusresidencias.data.dao.AdministratorDAO;
>>>>>>> e5d45d98e4e7f9feeab830a9d622a5555ecb4274
import co.edu.uco.victusresidencias.data.dao.CityDAO;
import co.edu.uco.victusresidencias.data.dao.CommonZoneDAO;
import co.edu.uco.victusresidencias.data.dao.CountryDAO;
import co.edu.uco.victusresidencias.data.dao.DAOFactory;
import co.edu.uco.victusresidencias.data.dao.ResidentialComplexDAO;
import co.edu.uco.victusresidencias.data.dao.StateDAO;
import co.edu.uco.victusresidencias.entity.CountryEntity;

public final class PostgreSqlDAOFactory extends DAOFactory {

	private Connection connection;
	private final String url = "jdbc:postgresql://localhost:5432/LocalBaseDatosJava";
	private final String user = "postgres";
	private final String password = "123456";

	public PostgreSqlDAOFactory() {
		openConnection();
	}

	@Override
	protected void openConnection() {
		SqlConnectionHelper.validateIfConnectionIsOpen(connection);
		connection = SqlConnectionHelper.openConnectionPostgreSQL(url,user,password);
		System.out.println("Buen trabajo se conecto a la base de datos");
	}

//	public static void main(String[] args) {
//		PostgreSqlDAOFactory prueba = new PostgreSqlDAOFactory();
//		CountryPostgreSQLDAO pais = new CountryPostgreSQLDAO(prueba.connection); // repository
//		CountryEntity paisEntidad = new CountryEntity(); //domain
//		paisEntidad.setId(UUIDHelper.generate());
//		paisEntidad.setName("Venezuela");
//		pais.create(paisEntidad);
//		prueba.closeConnection();
//	}
	@Override
	public void initTransaction() {
		SqlConnectionHelper.initTransaction(connection);
	}

	@Override
	public void commitTransaction() {
		SqlConnectionHelper.commitTransaction(connection);
	}

	@Override
	public void rollbackTransaction() {
		SqlConnectionHelper.rollbackTransaction(connection);
	}

	@Override
	public void closeConnection() {
		SqlConnectionHelper.closeConnection(connection);
		System.out.println("Buen trabajo se Desconecto a la base de datos");
	}

	@Override
	public CityDAO getCityDAO() {
		return new CityPostgreSqlDAO(connection);
	}

	@Override
	public StateDAO getStateDAO() {
		return new StatePostgreSqlDAO(connection);
				
	}

	@Override
	public CountryDAO getCountryDAO() {
		return new CountryPostgreSQLDAO(connection);
	}

	@Override
	public ResidentialComplexDAO getResidentialComplexDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonZoneDAO getCommonZoneDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdministratorDAO getAdministratorDAO() {
		// TODO Auto-generated method stub
		return null;
	}

}
