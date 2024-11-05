package co.edu.uco.victusresidencias.businesslogic.usecase.commonzone;

import java.util.List;


import co.edu.uco.victusresidencias.businesslogic.usecase.UseWithReturn;
import co.edu.uco.victusresidencias.domain.CityDomain;
import co.edu.uco.victusresidencias.domain.CommonZoneDomain;
import co.edu.uco.victusresidencias.domain.ResidentialComplexDomain;

public interface FindCommonZone extends UseWithReturn<CommonZoneDomain, List<CommonZoneDomain>>{

}
