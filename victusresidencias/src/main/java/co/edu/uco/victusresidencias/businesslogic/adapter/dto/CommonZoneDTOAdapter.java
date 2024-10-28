package co.edu.uco.victusresidencias.businesslogic.adapter.dto;

import java.util.UUID;

import co.edu.uco.victusresidencias.businesslogic.adapter.Adapter;
import co.edu.uco.victusresidencias.businesslogic.adapter.createDefault;
import co.edu.uco.victusresidencias.crosscutting.helpers.ObjectHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.UUIDHelper;
import co.edu.uco.victusresidencias.domain.CommonZoneDomain;
import co.edu.uco.victusresidencias.domain.PropertyDomain;
import co.edu.uco.victusresidencias.domain.ResidentialComplexDomain;
import co.edu.uco.victusresidencias.dto.ComunZoneDTO;
import co.edu.uco.victusresidencias.dto.PropertyDTO;

public class CommonZoneDTOAdapter implements Adapter<CommonZoneDomain, ComunZoneDTO>{
	
	private static final Adapter<CommonZoneDomain, ComunZoneDTO> instance = new CommonZoneDTOAdapter();
	 private CommonZoneDTOAdapter() {
	    }

	    public static Adapter<CommonZoneDomain, ComunZoneDTO> getComunZoneDTOAdapter() {
	        return instance;
	    }

	    @Override
	    public CommonZoneDomain adaptSource(ComunZoneDTO data) {
	        // Si el DTO es nulo, usamos un DTO por defecto
	        var dtoToAdapt = ObjectHelper.getDefault(data, ComunZoneDTO.create());

	        // Convertimos el DTO a Domain
	        return CommonZoneDomain.create(
	                UUIDHelper.convertToUUID(dtoToAdapt.getId()),
	                dtoToAdapt.getName(),
	                dtoToAdapt.getDescripcion(),
	                dtoToAdapt.getCapacidadPersonas(),
	                dtoToAdapt.getTiempoUso(),
	                dtoToAdapt.getUnidadTiempoUso(),
	                dtoToAdapt.getNormas(),
	                ResidentialComplexDTOAdapter.getResidentialComplexDTOAdapter().adaptSource(dtoToAdapt.getConjuntoResidencial())
	        );
	    }

	    @Override
	    public ComunZoneDTO adaptTarget(CommonZoneDomain data) {
	        // Si el Domain es nulo, usamos un Domain por defecto
	        var domainToAdapt = ObjectHelper.getDefault(data,createDefault.COMUN_ZONE);
	        
	        return ComunZoneDTO.create()
	        		.setId(ObjectHelper.getDefault(domainToAdapt.getId().toString(), UUIDHelper.getDefaultAsString()))
	        		.setName(domainToAdapt.getName())
	        		.setDescripcion(domainToAdapt.getDescription())
	        		.setCapacidadPersonas(domainToAdapt.getPeopleLimit())
	        		.setTiempoUso(domainToAdapt.getUseTime())
	        		.setUnidadTiempoUso(domainToAdapt.getUseTimeUnit())
	        		.setNormas(domainToAdapt.getRules())
	        		.setConjuntoResidencial(ResidentialComplexDTOAdapter.getResidentialComplexDTOAdapter()
	        				.adaptTarget(domainToAdapt.getResidentialComplex()));

	    }
}
