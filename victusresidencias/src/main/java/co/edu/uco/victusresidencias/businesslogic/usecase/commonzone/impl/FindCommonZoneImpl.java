package co.edu.uco.victusresidencias.businesslogic.usecase.commonzone.impl;

import java.util.List;



import co.edu.uco.victusresidencias.businesslogic.usecase.city.FindCity;
import co.edu.uco.victusresidencias.businesslogic.usecase.commonzone.FindCommonZone;
import co.edu.uco.victusresidencias.businesslogic.usecase.residentialcomplex.FindResidentialComplex;
import co.edu.uco.victusresidencias.data.dao.DAOFactory;
import co.edu.uco.victusresidencias.domain.CityDomain;
import co.edu.uco.victusresidencias.domain.CommonZoneDomain;
import co.edu.uco.victusresidencias.domain.ResidentialComplexDomain;

public final class FindCommonZoneImpl implements FindCommonZone{

	public FindCommonZoneImpl(final DAOFactory daoFactory) {
		
	}

	@Override
	public List<CommonZoneDomain> execute(final CommonZoneDomain data) {
		// TODO Auto-generated method stub
		return null;
	}

}
