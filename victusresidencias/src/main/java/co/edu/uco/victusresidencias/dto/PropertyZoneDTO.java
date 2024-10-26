package co.edu.uco.victusresidencias.dto;

import co.edu.uco.crosscutting.helpers.NumericHelper;
import co.edu.uco.crosscutting.helpers.ObjectHelper;
import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;

public class PropertyZoneDTO extends DomainDTO {
    
    private String tipoZonaInmueble;
    private int numeroZonaInmueble;
    private ResidentialComplexDTO conjuntoResidencial;

    // Constructor privado
    private PropertyZoneDTO() {
        super(UUIDHelper.getDefaultAsString());
        setTipoZonaInmueble(TextHelper.EMPTY);
        setNumeroZonaInmueble(NumericHelper.CERO);
        setConjuntoResidencial(ResidentialComplexDTO.create());
    }

    // Método estático para crear una instancia
    public static PropertyZoneDTO create() {
        return new PropertyZoneDTO();
    }

    // Getters y Setters con return this para fluidez

    public String getTipoZonaInmueble() {
        return tipoZonaInmueble;
    }

    public PropertyZoneDTO setTipoZonaInmueble(String tipoZonaInmueble) {
        this.tipoZonaInmueble = TextHelper.applyTrim(tipoZonaInmueble);
        return this;
    }

    public int getNumeroZonaInmueble() {
        return numeroZonaInmueble;
    }

    public PropertyZoneDTO setNumeroZonaInmueble(int numeroZonaInmueble) {
        this.numeroZonaInmueble = numeroZonaInmueble;
        return this;
    }

    public ResidentialComplexDTO getConjuntoResidencial() {
        return conjuntoResidencial;
    }

    public PropertyZoneDTO setConjuntoResidencial(ResidentialComplexDTO conjuntoResidencial) {
        this.conjuntoResidencial = ObjectHelper.getDefault(conjuntoResidencial, ResidentialComplexDTO.create());
        return this;
    }

    // Métodos para el manejo del ID heredado de DomainDTO
    public PropertyZoneDTO setId(final String id) {
        super.setIdentifier(id);
        return this;
    }

    @Override
    public String getId() {
        return super.getId();
    }
}
