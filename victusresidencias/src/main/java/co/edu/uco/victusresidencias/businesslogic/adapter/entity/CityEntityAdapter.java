package co.edu.uco.ucobet.businesslogic.adapter.entity;

import co.edu.uco.crosscutting.helpers.ObjectHelper;
import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;
import co.edu.uco.ucobet.businesslogic.adapter.Adapter;
import co.edu.uco.ucobet.domain.CityDomain;
import co.edu.uco.ucobet.entity.CityEntity;

public final class CityEntityAdapter implements Adapter<CityEntity,CityDomain>{
	
	private static final Adapter<CityEntity,CityDomain> instance = new CityEntityAdapter();
	
	private CityEntityAdapter() {
		
	}
	public static Adapter<CityEntity,CityDomain> getCityEntityAdapter(){
		return instance;
	}
	@Override
	public CityEntity adaptSource(CityDomain data) {
		// Ensure data is not null, use a default value if it is
        var domainToAdapt = ObjectHelper.getDefault(data, CityDomain.create(UUIDHelper.getDefault(), TextHelper.EMPTY, null));

        // Convert CityDomain to CityEntity
        var entityToAdapt = new CityEntity();
        entityToAdapt.setId(domainToAdapt.getId());
        entityToAdapt.setName(domainToAdapt.getName());
        entityToAdapt.setState(StateEntityAdapter.getStateEntityAdapter().adaptSource(domainToAdapt.getState()));

        return entityToAdapt;
	}
	@Override
	public CityDomain adaptTarget(CityEntity data) {
		// Ensure data is not null, use a default value if it is
        var entityToAdapt = ObjectHelper.getDefault(data, new CityEntity());

        // Convert CityEntity to CityDomain
        return CityDomain.create(
            entityToAdapt.getId(),
            entityToAdapt.getName(),
            StateEntityAdapter.getStateEntityAdapter().adaptTarget(entityToAdapt.getState()) // Adapt StateEntity to StateDomain
        );
	}

	

}
