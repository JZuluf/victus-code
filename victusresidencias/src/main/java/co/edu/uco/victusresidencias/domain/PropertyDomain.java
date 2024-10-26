package co.edu.uco.victusresidencias.domain;

import java.util.UUID;

import co.edu.uco.crosscutting.helpers.NumericHelper;
import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;

public class PropertyDomain extends Domain {

    private String tipoInmueble;
    private int numeroVivienda;
    private PropertyZoneDomain zonaInmueble;

    // Constructor privado
    private PropertyDomain(final UUID id, final String tipoInmueble, final int numeroVivienda, final PropertyZoneDomain zonaInmueble) {
        super(id);
        setTipoInmueble(tipoInmueble);
        setNumeroVivienda(numeroVivienda);
        setZonaInmueble(zonaInmueble);
    }

    // Método estático para crear una instancia con parámetros
    public static PropertyDomain create(final UUID id, final String tipoInmueble, final int numeroVivienda, final PropertyZoneDomain zonaInmueble) {
        return new PropertyDomain(id, tipoInmueble, numeroVivienda, zonaInmueble);
    }

    // Método estático para crear una instancia vacía por defecto
    public static PropertyDomain create() {
        return new PropertyDomain(UUIDHelper.getDefault(), TextHelper.EMPTY, NumericHelper.CERO, PropertyZoneDomain.create());
    }

    // Getters y Setters

    public String getTipoInmueble() {
        return tipoInmueble;
    }

    private void setTipoInmueble(final String tipoInmueble) {
        this.tipoInmueble = TextHelper.applyTrim(tipoInmueble);
    }

    public int getNumeroVivienda() {
        return numeroVivienda;
    }

    private void setNumeroVivienda(final int numeroVivienda) {
        this.numeroVivienda = (numeroVivienda >= 0) ? numeroVivienda : NumericHelper.CERO;
    }

    public PropertyZoneDomain getZonaInmueble() {
        return zonaInmueble;
    }

    private void setZonaInmueble(final PropertyZoneDomain zonaInmueble) {
        this.zonaInmueble = (zonaInmueble != null) ? zonaInmueble : PropertyZoneDomain.create();
    }

    @Override
    public UUID getId() {
        return super.getId();
    }
}
