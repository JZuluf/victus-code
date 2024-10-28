package co.edu.uco.victusresidencias.businesslogic.adapter.dto;



import co.edu.uco.victusresidencias.crosscutting.helpers.NumericHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.ObjectHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.TextHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.UUIDHelper;
import co.edu.uco.victusresidencias.businesslogic.adapter.Adapter;
import co.edu.uco.victusresidencias.businesslogic.adapter.createDefault;
import co.edu.uco.victusresidencias.domain.AdministratorDomain;
import co.edu.uco.victusresidencias.domain.CityDomain;
import co.edu.uco.victusresidencias.domain.CountryDomain;
import co.edu.uco.victusresidencias.domain.PropertyZoneDomain;
import co.edu.uco.victusresidencias.domain.ResidentialComplexDomain;
import co.edu.uco.victusresidencias.domain.StateDomain;
import co.edu.uco.victusresidencias.dto.PropertyZoneDTO;

public class PropertyZoneDTOAdapter implements Adapter<PropertyZoneDomain, PropertyZoneDTO> {

    private static final Adapter<PropertyZoneDomain, PropertyZoneDTO> instance = new PropertyZoneDTOAdapter();

    private PropertyZoneDTOAdapter() {
    }

    public static Adapter<PropertyZoneDomain, PropertyZoneDTO> getPropertyZoneDTOAdapter() {
        return instance;
    }

    @Override
    public PropertyZoneDomain adaptSource(PropertyZoneDTO data) {
        // Usar un DTO por defecto si data es nulo
        var dtoToAdapt = ObjectHelper.getDefault(data, PropertyZoneDTO.create());

        // Convertimos el DTO a Domain
        return PropertyZoneDomain.create(
                UUIDHelper.convertToUUID(dtoToAdapt.getId()),
                dtoToAdapt.getTipoZonaInmueble(),
                dtoToAdapt.getNumeroZonaInmueble(),
                ResidentialComplexDTOAdapter.getResidentialComplexDTOAdapter().adaptSource(dtoToAdapt.getConjuntoResidencial())
        );
    }

    @Override
    public PropertyZoneDTO adaptTarget(PropertyZoneDomain data) {
        // Usar un Domain por defecto si data es nulo
        var domainToAdapt = ObjectHelper.getDefault(data, createDefault.PROPERTY_ZONE);

        // Convertimos el Domain a DTO
        return PropertyZoneDTO.create()
                .setId(UUIDHelper.getDefaultAsString())
                .setTipoZonaInmueble(domainToAdapt.getTipoZonaInmueble())
                .setNumeroZonaInmueble(domainToAdapt.getNumeroZonaInmueble())
                .setConjuntoResidencial(ResidentialComplexDTOAdapter.getResidentialComplexDTOAdapter().adaptTarget(domainToAdapt.getConjuntoResidencial()));
    }
}
