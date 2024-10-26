package co.edu.uco.victusresidencias.businesslogic.adapter.dto;

import co.edu.uco.crosscutting.helpers.NumericHelper;
import co.edu.uco.crosscutting.helpers.ObjectHelper;
import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;
import co.edu.uco.victusresidencias.businesslogic.adapter.Adapter;
import co.edu.uco.victusresidencias.businesslogic.adapter.createDefault;
import co.edu.uco.victusresidencias.domain.PropertyDomain;
import co.edu.uco.victusresidencias.dto.PropertyDTO;

public class PropertyDTOAdapter implements Adapter<PropertyDomain, PropertyDTO> {

    private static final Adapter<PropertyDomain, PropertyDTO> instance = new PropertyDTOAdapter();

    private PropertyDTOAdapter() {
    }

    public static Adapter<PropertyDomain, PropertyDTO> getPropertyDTOAdapter() {
        return instance;
    }

    @Override
    public PropertyDomain adaptSource(PropertyDTO data) {
        // Si el DTO es nulo, usamos un DTO por defecto
        var dtoToAdapt = ObjectHelper.getDefault(data, PropertyDTO.create());

        // Convertimos el DTO a Domain
        return PropertyDomain.create(
                UUIDHelper.convertToUUID(dtoToAdapt.getId()),
                dtoToAdapt.getTipoInmueble(),
                dtoToAdapt.getNumeroVivienda(),
                PropertyZoneDTOAdapter.getPropertyZoneDTOAdapter().adaptSource(dtoToAdapt.getZonaInmueble())
        );
    }

    @Override
    public PropertyDTO adaptTarget(PropertyDomain data) {
        // Si el Domain es nulo, usamos un Domain por defecto
        var domainToAdapt = ObjectHelper.getDefault(data,createDefault.PROPERTY);

        // Convertimos el Domain a DTO
//        return PropertyDTO.create()
//                .setId(UUIDHelper.getDefaultAsString())
//                .setAddress(domainToAdapt.getAddress())
//                .setType(domainToAdapt.getType())
//                .setResidentialComplex(ResidentialComplexDTOAdapter.getResidentialComplexDTOAdapter().adaptTarget(domainToAdapt.getResidentialComplex()))
//                .setPropertyZone(PropertyZoneDTOAdapter.getPropertyZoneDTOAdapter().adaptTarget(domainToAdapt.getPropertyZone()));
    return null;
    }
}
