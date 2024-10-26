package co.edu.uco.victusresidencias.businesslogic.usecase.city.impl;


import co.edu.uco.crosscutting.helpers.ObjectHelper;

import co.edu.uco.victusresidencias.businesslogic.adapter.entity.CityEntityAdapter;
import co.edu.uco.victusresidencias.businesslogic.usecase.city.UpdateCity;
import co.edu.uco.victusresidencias.crosscutting.exceptions.BusinessLogicVictusResidenciasException;
import co.edu.uco.victusresidencias.data.dao.DAOFactory;
import co.edu.uco.victusresidencias.domain.CityDomain;

public final class UpdateCityImpl implements UpdateCity{
	
	private DAOFactory daoFactory;
	
	public UpdateCityImpl(DAOFactory daoFactory) {
		setDaoFactory(daoFactory);
	}

	private void setDaoFactory(final DAOFactory daoFactory) {
		if (ObjectHelper.isNull(daoFactory)) {
			var userMessage = "Se ha presentado un problema inesperado, tratando de llevar a cabo la modificación de la información de la ciudad deseada. Por favor intente de nuevo y si el problema persiste, llame a Luz Mery Rios Alzate...";
			var technicalMessage = "El DAO factory requerido para crear la clase que actualiza la ciudad llegó nula...";
			throw BusinessLogicVictusResidenciasException.create(userMessage, technicalMessage);
		}
		this.daoFactory = daoFactory;
	}

	@Override
	public void execute(final CityDomain data) {
		var cityEntity = CityEntityAdapter.getCityEntityAdapter().adaptSource(data);
		daoFactory.getCityDAO().update(cityEntity);
		//validate Policies

		
	}

}
