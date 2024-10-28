package co.edu.uco.victusresidencias.businesslogic.adapter.dto;

import co.edu.uco.victusresidencias.crosscutting.helpers.NumericHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.ObjectHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.TextHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.UUIDHelper;
import co.edu.uco.victusresidencias.businesslogic.adapter.Adapter;
import co.edu.uco.victusresidencias.businesslogic.adapter.createDefault;
import co.edu.uco.victusresidencias.domain.AdministratorDomain;
import co.edu.uco.victusresidencias.dto.AdministratorDTO;

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
                dtoToAdapt.getApellido(),
                dtoToAdapt.getTipoDocumento(),
                dtoToAdapt.getNumeroDocumento(),
                dtoToAdapt.getNumeroContacto(),
                dtoToAdapt.getCorreoElectronico(),
                dtoToAdapt.getContrasena()
        );
    }

    @Override
    public AdministratorDTO adaptTarget(AdministratorDomain data) {
        // Usar un Domain por defecto si el dato es nulo
        var domainToAdapt = ObjectHelper.getDefault(data, createDefault.ADMINISTRATOR);

        // Adaptación de Domain a DTO
        return AdministratorDTO.create()
                .setId(UUIDHelper.getDefaultAsString())
                .setName(domainToAdapt.getName())
                .setApellido(domainToAdapt.getLastName())
                .setTipoDocumento(domainToAdapt.getIdType())
                .setNumeroDocumento(domainToAdapt.getIdNumber());
                
    }
}
