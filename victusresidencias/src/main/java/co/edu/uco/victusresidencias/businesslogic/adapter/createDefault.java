package co.edu.uco.victusresidencias.businesslogic.adapter;

import co.edu.uco.crosscutting.helpers.DateHelper;
import co.edu.uco.crosscutting.helpers.NumericHelper;

import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;
import co.edu.uco.victusresidencias.domain.AdministratorDomain;
import co.edu.uco.victusresidencias.domain.CityDomain;
import co.edu.uco.victusresidencias.domain.ComunZoneDomain;
import co.edu.uco.victusresidencias.domain.CountryDomain;
import co.edu.uco.victusresidencias.domain.PropertyDomain;
import co.edu.uco.victusresidencias.domain.PropertyZoneDomain;
import co.edu.uco.victusresidencias.domain.ResidentDomain;
import co.edu.uco.victusresidencias.domain.ResidentialComplexDomain;
import co.edu.uco.victusresidencias.domain.StateDomain;

public class createDefault {
	
	//para los DTO
	public static final CountryDomain COUNTRY = CountryDomain.create(UUIDHelper.getDefault(), TextHelper.EMPTY);
	public static final StateDomain STATE = StateDomain.create(UUIDHelper.getDefault(), TextHelper.EMPTY, COUNTRY);
	public static final CityDomain CITY = CityDomain.create(UUIDHelper.getDefault(), TextHelper.EMPTY, STATE);
	public static final AdministratorDomain ADMINISTRATOR = AdministratorDomain.create(UUIDHelper.getDefault(), TextHelper.EMPTY, TextHelper.EMPTY,TextHelper.EMPTY,NumericHelper.CERO,NumericHelper.CERO,TextHelper.EMPTY,TextHelper.EMPTY);
	public static final ResidentialComplexDomain RESIDENTIAL_COMPLEX = ResidentialComplexDomain.create(UUIDHelper.getDefault(), 
			TextHelper.EMPTY, 
			TextHelper.EMPTY,
			CITY,
			TextHelper.EMPTY,
			TextHelper.EMPTY,
			ADMINISTRATOR);
	public static final PropertyZoneDomain PROPERTY_ZONE = PropertyZoneDomain.create(UUIDHelper.getDefault(), 
			TextHelper.EMPTY,
			NumericHelper.CERO,
			RESIDENTIAL_COMPLEX);
	public static final PropertyDomain PROPERTY = PropertyDomain.create(UUIDHelper.getDefault(),
            TextHelper.EMPTY,
            NumericHelper.CERO,
			PROPERTY_ZONE);
	public static final ResidentDomain RESIDENT = ResidentDomain.create(UUIDHelper.getDefault(),
			TextHelper.EMPTY,
			TextHelper.EMPTY,
			TextHelper.EMPTY,
			NumericHelper.CERO,
			DateHelper.DEFAULT_DATE,
			NumericHelper.CERO,
			TextHelper.EMPTY,
			PROPERTY);
	public static final ComunZoneDomain COMUN_ZONE = ComunZoneDomain.create(UUIDHelper.getDefault(),
			TextHelper.EMPTY,
			TextHelper.EMPTY,
			NumericHelper.CERO,
			NumericHelper.CERO,
			TextHelper.EMPTY,
			TextHelper.EMPTY,
			RESIDENTIAL_COMPLEX);
	
	
}
