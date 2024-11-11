package co.edu.uco.victusresidencias.businesslogic.usecase.administrator.impl;

import java.util.UUID;

import co.edu.uco.victusresidencias.businesslogic.adapter.entity.AdministratorEntityAdapter;
import co.edu.uco.victusresidencias.businesslogic.adapter.entity.CountryEntityAdapter;
import co.edu.uco.victusresidencias.businesslogic.usecase.administrator.RegisterNewAdministrator;
import co.edu.uco.victusresidencias.businesslogic.usecase.administrator.rules.AdministratorNameConsistencyIsValid;
import co.edu.uco.victusresidencias.businesslogic.usecase.administrator.rules.impl.AdministratorNameConsistencyIsValidImpl;
import co.edu.uco.victusresidencias.businesslogic.usecase.country.RegisterNewCountry;
import co.edu.uco.victusresidencias.businesslogic.usecase.country.rules.CountryNameConsistencyIsValid;
import co.edu.uco.victusresidencias.businesslogic.usecase.country.rules.impl.CountryNameConsistencyIsValidImpl;
import co.edu.uco.victusresidencias.crosscutting.exceptions.BusinessLogicVictusResidenciasException;
import co.edu.uco.victusresidencias.crosscutting.helpers.ObjectHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.UUIDHelper;
import co.edu.uco.victusresidencias.data.dao.DAOFactory;
import co.edu.uco.victusresidencias.domain.AdministratorDomain;
import co.edu.uco.victusresidencias.domain.CountryDomain;
import co.edu.uco.victusresidencias.entity.AdministratorEntity;
import co.edu.uco.victusresidencias.entity.CountryEntity;

public final class RegisterNewAdministratorImpl implements RegisterNewAdministrator{

	private DAOFactory daoFactory;
	private AdministratorNameConsistencyIsValid adminNameConsistencyIsValid = new AdministratorNameConsistencyIsValidImpl();
			
	public RegisterNewAdministratorImpl(final DAOFactory daoFactory) {
		setDaoFactory(daoFactory);
	}
	
	private void setDaoFactory(final DAOFactory daoFactory) {
		System.out.println("entra a la factoria");
		if (ObjectHelper.isNull(daoFactory)) {
			var userMessage = "Se ha presentado un problema inesperado, tratando de llevar a cabo el registro de la información del admin deseada. Por favor intente de nuevo y si el problema persiste, llame a Luz Mery Rios Alzate...";
			var technicalMessage = "El DAO factory requerido para crear la clase registra la ciudad llegó nula...";
			throw BusinessLogicVictusResidenciasException.crear(userMessage, technicalMessage);
		}
		this.daoFactory = daoFactory;
	}
	
	@Override
	public void execute(final AdministratorDomain data) {
		System.out.println("Validando");
	    adminNameConsistencyIsValid.execute(data.getName());
	    System.out.println("Validando2");
	 // Crear un filtro de entidad para buscar si existe un admin con el mismo nombre
	    var adminEntityFilter = new AdministratorEntity();
	    adminEntityFilter.setName(data.getName());
	    System.out.println("Validando3");
	    System.out.println(adminEntityFilter);
	    // Buscar en la base de datos utilizando el filtro
	    boolean adminExists = !daoFactory.getAdministratorDAO().findByFilter(adminEntityFilter).isEmpty();
	    System.out.println("Validando4");
	    // Lanzar excepción si se encuentra un país con el mismo nombre
	    if (adminExists) {
	        String userMessage = "El admin ya existe";
	        String technicalMessage = "El admin con el nombre '" + data.getName() + "' ya existe en la base de datos.";
	        
	        throw BusinessLogicVictusResidenciasException.crear(userMessage, technicalMessage);
	    }


	    // Si no existe, procede a crear el país
	    var adminDomainToMap = AdministratorDomain.create(generateId(), data.getName(),data.getLastName(), data.getIdType(),data.getIdNumber(),data.getContactNumber(),data.getEmail(),data.getPassword());
	    var adminEntity = AdministratorEntityAdapter.getAdministratorEntityAdapter().adaptSource(adminDomainToMap);
	    daoFactory.getAdministratorDAO().create(adminEntity);
	}
	
	private UUID generateId() {
	    UUID id;
	    do {
	        id = UUIDHelper.generate();
	    } while (daoFactory.getAdministratorDAO().fingByID(id) != null);
	    
	    return id;
	}

}
