package co.edu.uco.victusresidencias.businesslogic.usecase.administrator.impl;


import co.edu.uco.victusresidencias.crosscutting.helpers.ObjectHelper;
import co.edu.uco.victusresidencias.businesslogic.adapter.entity.AdministratorEntityAdapter;
import co.edu.uco.victusresidencias.businesslogic.usecase.administrator.UpdateAdministrator;
import co.edu.uco.victusresidencias.crosscutting.exceptions.BusinessLogicVictusResidenciasException;
import co.edu.uco.victusresidencias.data.dao.DAOFactory;
import co.edu.uco.victusresidencias.domain.AdministratorDomain;

public final class UpdateAdministratorImpl implements UpdateAdministrator{
	
	private DAOFactory daoFactory;
	
	public UpdateAdministratorImpl(DAOFactory daoFactory){
		setDaoFactory(daoFactory);
	}
	
	@Override
	public void execute(final AdministratorDomain data) {
		// Validate policies
		
		var administratorEntity = AdministratorEntityAdapter.getAdministratorEntityAdapter().adaptSource(data);
		daoFactory.getAdministratorDAO().update(administratorEntity);
		
	}

	private void setDaoFactory(final DAOFactory daoFactory) {
		if (ObjectHelper.isNull(daoFactory)) {
			var userMessage = "Se ha presentado un problema inesperado, tratando de llevar a cabo la modificación de la información del administrador deseado, Por favor intente de nuevo y si el problema persiste comuniquese con soporte...";
			var technicalMessage = "El dao factory requerido para crear la clase que actualiza el administrador llegó nula...";
			throw BusinessLogicVictusResidenciasException.crear(userMessage, technicalMessage);
		}
		
		this.daoFactory = daoFactory;
	}

}
