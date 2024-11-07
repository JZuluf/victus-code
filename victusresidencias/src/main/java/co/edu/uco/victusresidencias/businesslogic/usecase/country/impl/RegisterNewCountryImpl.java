package co.edu.uco.victusresidencias.businesslogic.usecase.country.impl;

import java.util.UUID;

import co.edu.uco.victusresidencias.businesslogic.adapter.entity.CityEntityAdapter;
import co.edu.uco.victusresidencias.businesslogic.adapter.entity.CountryEntityAdapter;
import co.edu.uco.victusresidencias.businesslogic.usecase.city.RegisterNewCity;
import co.edu.uco.victusresidencias.businesslogic.usecase.city.rules.CityNameConsistencyIsValid;
import co.edu.uco.victusresidencias.businesslogic.usecase.city.rules.CityNameDoesNotExistsForState;
import co.edu.uco.victusresidencias.businesslogic.usecase.city.rules.impl.CityNameConsistencyIsValidImpl;
import co.edu.uco.victusresidencias.businesslogic.usecase.city.rules.impl.CityNameDoesNotExistsForStateImpl;
import co.edu.uco.victusresidencias.businesslogic.usecase.country.RegisterNewCountry;
import co.edu.uco.victusresidencias.businesslogic.usecase.country.rules.CountryNameConsistencyIsValid;
import co.edu.uco.victusresidencias.businesslogic.usecase.country.rules.impl.CountryNameConsistencyIsValidImpl;
import co.edu.uco.victusresidencias.businesslogic.usecase.state.rules.StateExists;
import co.edu.uco.victusresidencias.businesslogic.usecase.state.rules.impl.StateExistsImpl;
import co.edu.uco.victusresidencias.crosscutting.exceptions.BusinessLogicVictusResidenciasException;
import co.edu.uco.victusresidencias.crosscutting.helpers.ObjectHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.UUIDHelper;
import co.edu.uco.victusresidencias.data.dao.DAOFactory;
import co.edu.uco.victusresidencias.domain.CityDomain;
import co.edu.uco.victusresidencias.domain.CountryDomain;

public final class RegisterNewCountryImpl implements RegisterNewCountry{

	private DAOFactory daoFactory;
	private CountryNameConsistencyIsValid countryNameConsistencyIsValid = new CountryNameConsistencyIsValidImpl();
			
	public RegisterNewCountryImpl(final DAOFactory daoFactory) {
		setDaoFactory(daoFactory);
	}
	
	private void setDaoFactory(final DAOFactory daoFactory) {
		if (ObjectHelper.isNull(daoFactory)) {
			var userMessage = "Se ha presentado un problema inesperado, tratando de llevar a cabo el registro de la información de la ciudad deseada. Por favor intente de nuevo y si el problema persiste, llame a Luz Mery Rios Alzate...";
			var technicalMessage = "El DAO factory requerido para crear la clase registra la ciudad llegó nula...";
			throw BusinessLogicVictusResidenciasException.crear(userMessage, technicalMessage);
		}
		this.daoFactory = daoFactory;
	}
	
	@Override
	public void execute(final CountryDomain data) {
		countryNameConsistencyIsValid.execute(data.getName());
		
		var countryDomainToMap = CountryDomain.create(generateId(), data.getName());
		var countryEntity = CountryEntityAdapter.getCountryEntityAdapter().adaptSource(countryDomainToMap);
		daoFactory.getCountryDAO().create(countryEntity);		
	}
	private UUID generateId() {
	    UUID id;
	    do {
	        id = UUIDHelper.generate();
	    } while (daoFactory.getCountryDAO().fingByID(id) != null);
	    
	    return id;
	}

	
//	private UUID generateId() {
//		var id = UUIDHelper.generate();
//		var countryEntity = daoFactory.getCountryDAO().fingByID(id);    
//		
//		if (UUIDHelper.isEqual(countryEntity.getId(), id)) {
//			id = generateId();
//		}
//		
//		return id;
//		
//	}

}
