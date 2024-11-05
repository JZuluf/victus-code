package co.edu.uco.victusresidencias.businesslogic.usecase.administrator.impl;

import java.util.UUID;

import co.edu.uco.victusresidencias.businesslogic.adapter.entity.AdministratorEntityAdapter;
import co.edu.uco.victusresidencias.businesslogic.usecase.administrator.RegisterNewAdministrator;
import co.edu.uco.victusresidencias.businesslogic.usecase.administrator.rules.AdministratorNameConsistencyIsValid;
import co.edu.uco.victusresidencias.businesslogic.usecase.administrator.rules.impl.AdministratorNameConsistencyIsValidImpl;
import co.edu.uco.victusresidencias.crosscutting.exceptions.BusinessLogicVictusResidenciasException;
import co.edu.uco.victusresidencias.crosscutting.helpers.ObjectHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.UUIDHelper;
import co.edu.uco.victusresidencias.data.dao.DAOFactory;
import co.edu.uco.victusresidencias.domain.AdministratorDomain;

public final class RegisterNewAdministratorImpl implements RegisterNewAdministrator{

	private DAOFactory daoFactory;
	private AdministratorNameConsistencyIsValid administratorNameConsistencyIsValid = new AdministratorNameConsistencyIsValidImpl();
			
	public RegisterNewAdministratorImpl(final DAOFactory daoFactory) {
		setDaoFactory(daoFactory);
	}
	
	private void setDaoFactory(final DAOFactory daoFactory) {
		if (ObjectHelper.isNull(daoFactory)) {
			var userMessage = "Se ha presentado un problema inesperado, tratando de llevar a cabo el registro de la información de una nueva zona común deseada. Por favor intente de nuevo y si el problema persiste, llame a Luz Mery Rios Alzate...";
			var technicalMessage = "El DAO factory requerido para crear la clase registra la zona común llegó nula...";
			throw BusinessLogicVictusResidenciasException.crear(userMessage, technicalMessage);
		}
		this.daoFactory = daoFactory;
	}
	
	@Override
	public void execute(final AdministratorDomain data) {
		administratorNameConsistencyIsValid.execute(data.getName());
		
		var administratorDomainToMap = AdministratorDomain.create(); //organizar despues
		var administratorEntity = AdministratorEntityAdapter.getAdministratorEntityAdapter().adaptSource(administratorDomainToMap);
		daoFactory.getAdministratorDAO().create(administratorEntity);		
	}
	
	private UUID generateId() {
		var id = UUIDHelper.generate();
		var administratorEntity = daoFactory.getAdministratorDAO().fingByID(id);    
		
		if (UUIDHelper.isEqual(administratorEntity.getId(), id)) {
			id = generateId();
		}
		
		return id;
		
	}

}
