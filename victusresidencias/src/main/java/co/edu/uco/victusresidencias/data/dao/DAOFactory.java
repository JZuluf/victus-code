package co.edu.uco.victusresidencias.data.dao;

import co.edu.uco.victusresidencias.data.dao.enums.DAOSource;

public abstract class DAOFactory {
	
	public final static DAOFactory getFactory(final DAOSource source) {
		return null;
	}
	protected abstract void openConnection();

	public abstract void initTransaction();
	
	public abstract void commitTransaction();
	
	public abstract void rollbackTransaction();
	
	public abstract void closeConnection();
	
	public abstract CityDAO getCityDAO();
	public abstract CountryDAO getCountryDAO();
	public abstract StateDAO getStateDAO();
	public abstract ResidentialComplexDAO getResidentialComplexDAO();
	public abstract CommonZoneDAO getCommonZoneDAO();
}
