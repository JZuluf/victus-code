package co.edu.uco.victusresidencias.businesslogic.usecase.country.impl;

import java.util.List;


import co.edu.uco.victusresidencias.businesslogic.usecase.UseWithReturn;
import co.edu.uco.victusresidencias.domain.CityDomain;
import co.edu.uco.victusresidencias.domain.CountryDomain;

public interface FindCountry extends UseWithReturn<CountryDomain, List<CountryDomain>>{

}
