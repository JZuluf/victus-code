package co.edu.uco.victusresidencias.businesslogic.usecase.country.impl;


import co.edu.uco.victusresidencias.crosscutting.helpers.ObjectHelper;

import co.edu.uco.victusresidencias.businesslogic.adapter.entity.CityEntityAdapter;
import co.edu.uco.victusresidencias.businesslogic.usecase.city.UpdateCity;
import co.edu.uco.victusresidencias.businesslogic.usecase.country.UpdateCountry;
import co.edu.uco.victusresidencias.crosscutting.exceptions.BusinessLogicVictusResidenciasException;
import co.edu.uco.victusresidencias.data.dao.DAOFactory;
import co.edu.uco.victusresidencias.domain.CityDomain;

public final class UpdateCountryImpl implements UpdateCountry{
	
	private DAOFactory daoFactory;
	
	public UpdateCountryImpl(DAOFactory daoFactory){
		setDaoFactory(daoFactory);
	}
	
	@Override
	public void execute(final CityDomain data) {
		// Validate policies
		
		var countryEntity = CityEntityAdapter.getCityEntityAdapter().adaptSource(data);
		daoFactory.getCityDAO().update(countryEntity);
		
	}

	private void setDaoFactory(final DAOFactory daoFactory) {
		if (ObjectHelper.isNull(daoFactory)) {
			var userMessage = "Se ha presentado un problema inesperado, tratando de llevar a cabo la modificación de la información del pais deseado, Por favor intente de nuevo y si el problema persiste cominuquese con soporte...";
			var technicalMessage = "El dao factory requerido para crear la clase que actualiza la ciudad llegó nula...";
			throw BusinessLogicVictusResidenciasException.crear(userMessage, technicalMessage);
		}
		
		this.daoFactory = daoFactory;
	}
}
