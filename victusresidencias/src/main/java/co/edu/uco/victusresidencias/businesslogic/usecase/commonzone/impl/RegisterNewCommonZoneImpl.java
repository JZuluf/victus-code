package co.edu.uco.victusresidencias.businesslogic.usecase.commonzone.impl;

import java.util.UUID;

import co.edu.uco.victusresidencias.businesslogic.adapter.entity.CommonZoneEntityAdapter;
import co.edu.uco.victusresidencias.businesslogic.adapter.entity.ResidentialComplexEntityAdapter;
import co.edu.uco.victusresidencias.businesslogic.usecase.commonzone.RegisterNewCommonZone;
import co.edu.uco.victusresidencias.businesslogic.usecase.commonzone.rules.CommonZoneNameConsistencyIsValid;
import co.edu.uco.victusresidencias.businesslogic.usecase.commonzone.rules.impl.CommonZoneNameConsistencyIsValidImpl;
import co.edu.uco.victusresidencias.businesslogic.usecase.residentialcomplex.RegisterNewResidentialComplex;
import co.edu.uco.victusresidencias.businesslogic.usecase.residentialcomplex.rules.ResidentialComplexNameConsistencyIsValid;
import co.edu.uco.victusresidencias.businesslogic.usecase.residentialcomplex.rules.impl.ResidentialcomplexNameConsistencyIsValidImpl;
import co.edu.uco.victusresidencias.crosscutting.exceptions.BusinessLogicVictusResidenciasException;
import co.edu.uco.victusresidencias.crosscutting.helpers.ObjectHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.UUIDHelper;
import co.edu.uco.victusresidencias.data.dao.DAOFactory;
import co.edu.uco.victusresidencias.domain.CommonZoneDomain;
import co.edu.uco.victusresidencias.domain.ResidentialComplexDomain;

public final class RegisterNewCommonZoneImpl implements RegisterNewCommonZone{

	private DAOFactory daoFactory;
	private CommonZoneNameConsistencyIsValid commonZoneNameConsistencyIsValid = new CommonZoneNameConsistencyIsValidImpl();
			
	public RegisterNewCommonZoneImpl(final DAOFactory daoFactory) {
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
	public void execute(final CommonZoneDomain data) {
		commonZoneNameConsistencyIsValid.execute(data.getName());
		
		var commonZoneDomainToMap = CommonZoneDomain.create(); //organizar despues
		var commonZoneEntity = CommonZoneEntityAdapter.getCommonZoneEntityAdapter().adaptSource(commonZoneDomainToMap);
		daoFactory.getCommonZoneDAO().create(commonZoneEntity);		
	}
	
	private UUID generateId() {
		var id = UUIDHelper.generate();
		var residentialComplexEntity = daoFactory.getResidentialComplexDAO().fingByID(id);    
		
		if (UUIDHelper.isEqual(residentialComplexEntity.getId(), id)) {
			id = generateId();
		}
		
		return id;
		
	}

}
