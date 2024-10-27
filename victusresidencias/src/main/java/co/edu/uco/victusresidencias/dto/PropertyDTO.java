package co.edu.uco.victusresidencias.dto;

import co.edu.uco.victusresidencias.crosscutting.helpers.NumericHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.ObjectHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.TextHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.UUIDHelper;

public class PropertyDTO extends DomainDTO {
    
    private String tipoInmueble;
    private int numeroVivienda;
    private PropertyZoneDTO zonaInmueble;

    // Constructor privado
    private PropertyDTO() {
        super(UUIDHelper.getDefaultAsString());
        setTipoInmueble(TextHelper.EMPTY);
        setNumeroVivienda(NumericHelper.CERO);
        setZonaInmueble(PropertyZoneDTO.create());
    }

    // Método estático para crear una instancia
    public static PropertyDTO create() {
        return new PropertyDTO();
    }

    // Getters y Setters con return this para fluidez

    public String getTipoInmueble() {
        return tipoInmueble;
    }

    public PropertyDTO setTipoInmueble(String tipoInmueble) {
        this.tipoInmueble = TextHelper.applyTrim(tipoInmueble);
        return this;
    }

    public int getNumeroVivienda() {
        return numeroVivienda;
    }

    public PropertyDTO setNumeroVivienda(int numeroVivienda) {
        this.numeroVivienda = numeroVivienda;
        return this;
    }

    public PropertyZoneDTO getZonaInmueble() {
        return zonaInmueble;
    }

    public PropertyDTO setZonaInmueble(PropertyZoneDTO zonaInmueble) {
        this.zonaInmueble = ObjectHelper.getDefault(zonaInmueble, PropertyZoneDTO.create());
        return this;
    }

    // Métodos para el manejo del ID heredado de DomainDTO
    public PropertyDTO setId(final String id) {
        super.setIdentifier(id);
        return this;
    }

    @Override
    public String getId() {
        return super.getId();
    }
}
