package co.edu.uco.victusresidencias.businesslogic.adapter.dto;


import co.edu.uco.victusresidencias.crosscutting.helpers.ObjectHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.UUIDHelper;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.victusresidencias.businesslogic.adapter.Adapter;
import co.edu.uco.victusresidencias.domain.AdministratorDomain;
import co.edu.uco.victusresidencias.domain.CityDomain;
import co.edu.uco.victusresidencias.dto.AdministratorDTO;
import co.edu.uco.victusresidencias.dto.CityDTO;

public class AdministratorDTOAdapter implements Adapter<AdministratorDomain, AdministratorDTO> {

    private static final Adapter<AdministratorDomain, AdministratorDTO> instance = new AdministratorDTOAdapter();

    private AdministratorDTOAdapter() {
    }

    public static Adapter<AdministratorDomain, AdministratorDTO> getAdministratorDTOAdapter() {
        return instance;
    }

    @Override
    public AdministratorDomain adaptSource(AdministratorDTO data) {
        // Usar un DTO por defecto si el dato es nulo
        var dtoToAdapt = ObjectHelper.getDefault(data, AdministratorDTO.create());

        // Adaptación de DTO a Domain
        return AdministratorDomain.create(
                UUIDHelper.convertToUUID(dtoToAdapt.getId()),
                dtoToAdapt.getName(),
                dtoToAdapt.getLastName(),
                dtoToAdapt.getIdType(),
                dtoToAdapt.getIdNumber(),
                dtoToAdapt.getContactNumber(),
                dtoToAdapt.getEmail(),
                dtoToAdapt.getPassword()
        );
    }

    @Override
    public AdministratorDTO adaptTarget(AdministratorDomain data) {
        // Usar un Domain por defecto si el dato es nulo
        var domainToAdapt = ObjectHelper.getDefault(data, AdministratorDomain.create());

        // Adaptación de Domain a DTO
        return AdministratorDTO.create()
                .setId(UUIDHelper.getDefaultAsString())
                .setName(domainToAdapt.getName())
                .setLastName(domainToAdapt.getLastName())
                .setIdType(domainToAdapt.getIdType())
                .setIdNumber(domainToAdapt.getIdNumber())
                .setContactNumber(domainToAdapt.getContactNumber())
                .setEmail(domainToAdapt.getEmail())
                .setPassword(domainToAdapt.getPassword());
                
    }
    @Override
	public List<AdministratorDTO> adaptTarget(final List<AdministratorDomain> data) {
		
		var results = new ArrayList<AdministratorDTO>();
		
		for (AdministratorDomain domain : data) {
			results.add(adaptTarget(domain));
		}
		
		return results;
	}
    
}
