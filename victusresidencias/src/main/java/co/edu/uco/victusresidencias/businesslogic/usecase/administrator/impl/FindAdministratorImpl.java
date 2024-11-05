package co.edu.uco.victusresidencias.businesslogic.usecase.administrator.impl;

import java.util.List;

import co.edu.uco.victusresidencias.businesslogic.usecase.administrator.FindAdministrator;
import co.edu.uco.victusresidencias.data.dao.DAOFactory;
import co.edu.uco.victusresidencias.domain.AdministratorDomain;

public final class FindAdministratorImpl implements FindAdministrator{

	public FindAdministratorImpl(final DAOFactory daoFactory) {
		
	}

	@Override
	public List<AdministratorDomain> execute(final AdministratorDomain data) {
		// TODO Auto-generated method stub
		return null;
	}

}
