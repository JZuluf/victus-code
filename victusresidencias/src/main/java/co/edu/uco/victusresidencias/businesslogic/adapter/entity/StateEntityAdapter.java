package co.edu.uco.ucobet.businesslogic.adapter.entity;

import co.edu.uco.crosscutting.helpers.ObjectHelper;
import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;
import co.edu.uco.ucobet.businesslogic.adapter.Adapter;
import co.edu.uco.ucobet.domain.StateDomain;
import co.edu.uco.ucobet.entity.StateEntity;

public class StateEntityAdapter implements Adapter<StateEntity,StateDomain>{
	
	private static final Adapter<StateEntity,StateDomain> instance = new StateEntityAdapter();
	
	private StateEntityAdapter() {
		
	}
	
	public static Adapter<StateEntity,StateDomain> getStateEntityAdapter(){
		return instance;
	}

	@Override
	public StateEntity adaptSource(StateDomain data) {
		// Ensure data is not null, use a default value if it is
        var domainToAdapt = ObjectHelper.getDefault(data, StateDomain.create(UUIDHelper.getDefault(), TextHelper.EMPTY, null));

        // Convert StateDomain to StateEntity
        var entityToAdapt = new StateEntity();
        entityToAdapt.setId(domainToAdapt.getId());
        entityToAdapt.setName(domainToAdapt.getName());
        entityToAdapt.setCountry(CountryEntityAdapter.getCountryEntityAdapter().adaptSource(domainToAdapt.getCountry()));

        return entityToAdapt;
	}

	@Override
	public StateDomain adaptTarget(StateEntity data) {
		// Ensure data is not null, use a default value if it is
        var entityToAdapt = ObjectHelper.getDefault(data, new StateEntity());

        // Convert StateEntity to StateDomain
        return StateDomain.create(
            entityToAdapt.getId(),
            entityToAdapt.getName(),
            CountryEntityAdapter.getCountryEntityAdapter().adaptTarget(entityToAdapt.getCountry()) // Adapt CountryEntity to CountryDomain
        );
	}




}
