package co.edu.uco.victusresidencias.domain;

import java.util.UUID;

import co.edu.uco.crosscutting.helpers.NumericHelper;
import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;

public class PropertyZoneDomain extends Domain {

    private String tipoZonaInmueble;
    private int numeroZonaInmueble;
    private ResidentialComplexDomain conjuntoResidencial;

    // Constructor privado
    private PropertyZoneDomain(final UUID id, final String tipoZonaInmueble, final int numeroZonaInmueble, final ResidentialComplexDomain conjuntoResidencial) {
        super(id);
        setTipoZonaInmueble(tipoZonaInmueble);
        setNumeroZonaInmueble(numeroZonaInmueble);
        setConjuntoResidencial(conjuntoResidencial);
    }

    // Método estático para crear una instancia con parámetros
    public static PropertyZoneDomain create(final UUID id, final String tipoZonaInmueble, final int numeroZonaInmueble, 
    		final ResidentialComplexDomain conjuntoResidencial) {
        return new PropertyZoneDomain(id, tipoZonaInmueble, numeroZonaInmueble, conjuntoResidencial);
    }

    // Método estático para crear una instancia vacía por defecto
    public static PropertyZoneDomain create() {
        return new PropertyZoneDomain(UUIDHelper.getDefault(), TextHelper.EMPTY, NumericHelper.CERO, ResidentialComplexDomain.create());
    }

    // Getters y Setters

    public String getTipoZonaInmueble() {
        return tipoZonaInmueble;
    }

    private void setTipoZonaInmueble(final String tipoZonaInmueble) {
        this.tipoZonaInmueble = TextHelper.applyTrim(tipoZonaInmueble);
    }

    public int getNumeroZonaInmueble() {
        return numeroZonaInmueble;
    }

    private void setNumeroZonaInmueble(final int numeroZonaInmueble) {
        this.numeroZonaInmueble = (numeroZonaInmueble >= 0) ? numeroZonaInmueble : NumericHelper.CERO;
    }

    public ResidentialComplexDomain getConjuntoResidencial() {
        return conjuntoResidencial;
    }

    private void setConjuntoResidencial(final ResidentialComplexDomain conjuntoResidencial) {
        this.conjuntoResidencial = (conjuntoResidencial != null) ? conjuntoResidencial : ResidentialComplexDomain.create();
    }

    @Override
    public UUID getId() {
        return super.getId();
    }
}
