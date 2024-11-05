package co.edu.uco.victusresidencias.businesslogic.facade.administrator.impl;

import co.edu.uco.victusresidencias.businesslogic.adapter.dto.AdministratorDTOAdapter;
import co.edu.uco.victusresidencias.businesslogic.adapter.dto.CityDTOAdapter;
import co.edu.uco.victusresidencias.businesslogic.facade.administrator.RegisterNewAdministratorFacade;
import co.edu.uco.victusresidencias.businesslogic.facade.city.RegisterNewCityFacade;
import co.edu.uco.victusresidencias.businesslogic.usecase.administrator.impl.RegisterNewAdministratorImpl;
import co.edu.uco.victusresidencias.businesslogic.usecase.city.impl.RegisterNewCityImpl;
import co.edu.uco.victusresidencias.crosscutting.exceptions.BusinessLogicVictusResidenciasException;
import co.edu.uco.victusresidencias.crosscutting.exceptions.VictusResidenciasException;
import co.edu.uco.victusresidencias.data.dao.DAOFactory;
import co.edu.uco.victusresidencias.data.dao.enums.DAOSource;
import co.edu.uco.victusresidencias.dto.AdministratorDTO;
import co.edu.uco.victusresidencias.dto.CityDTO;

public final class RegisterNewAdministratorFacadeImpl implements RegisterNewAdministratorFacade{
	@Override
	public void execute(final AdministratorDTO data) {
		
		var factory = DAOFactory.getFactory(DAOSource.POSTGRESQL);
		
		try {
			factory.initTransaction(); 
			
			var registerNewAdministratorUSeCase = new RegisterNewAdministratorImpl(factory);
			var administratorDomain = AdministratorDTOAdapter.getAdministratorDTOAdapter().adaptSource(data);
			
			registerNewAdministratorUSeCase.execute(administratorDomain);
			
			factory.commitTransaction();
		} catch (final VictusResidenciasException exception) {
			factory.rollbackTransaction();
			throw exception;
		} catch (final Exception exception) {
			
			factory.rollbackTransaction();
			
			var userMEssage ="Se ha presentado un problema tratando de registerar la informacion de un nuevo administrador...";
			var technicalMEssage="Se ha presentado un problema inseperado registrando la informacion de un nuevo administrador. por favor revise el log de errores para tener mas detalles...";
			
			throw BusinessLogicVictusResidenciasException.create(userMEssage, technicalMEssage, exception) ;  
		}finally {
			factory.closeConnection();
		}
		
	}
}