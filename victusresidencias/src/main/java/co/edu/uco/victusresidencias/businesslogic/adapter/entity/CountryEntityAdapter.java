package co.edu.uco.ucobet.businesslogic.adapter.entity;

import co.edu.uco.crosscutting.helpers.ObjectHelper;

import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;
import co.edu.uco.ucobet.businesslogic.adapter.Adapter;
import co.edu.uco.ucobet.domain.CountryDomain;
import co.edu.uco.ucobet.entity.CountryEntity;


public class CountryEntityAdapter implements Adapter<CountryEntity,CountryDomain>{
	
	private static final Adapter<CountryEntity,CountryDomain> instance = new CountryEntityAdapter();
	
	private CountryEntityAdapter() {
		
	}
	public static Adapter<CountryEntity,CountryDomain> getCountryEntityAdapter(){
		return instance;
	}

	@Override
	public CountryEntity adaptSource(CountryDomain data) {
		var domainToAdapt = ObjectHelper.getDefault(data, CountryDomain.create(UUIDHelper.getDefault(), TextHelper.EMPTY));
		
		var entityToAdapt = new CountryEntity();
		entityToAdapt.setId(domainToAdapt.getId());
		entityToAdapt.setName(domainToAdapt.getName());
		
		return entityToAdapt;
	}

	@Override
	public CountryDomain adaptTarget(CountryEntity data) {
		var entityToAdapt = ObjectHelper.getDefault(data, new CountryEntity());
		return CountryDomain.create(entityToAdapt.getId(), entityToAdapt.getName());
	}
	

}
