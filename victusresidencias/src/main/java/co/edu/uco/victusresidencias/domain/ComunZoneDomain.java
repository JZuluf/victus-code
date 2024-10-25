package co.edu.uco.victusresidencias.domain;

import java.util.UUID;

import co.edu.uco.crosscutting.helpers.NumericHelper;
import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;

public class ComunZoneDomain extends Domain {
    
    private String name;
    private String descripcion;
    private int capacidadPersonas;
    private int tiempoUso;
    private String unidadTiempoUso;
    private String normas;
    private ResidentialComplexDomain conjuntoResidencial;

    // Constructor privado
    private ComunZoneDomain(final UUID id, final String name, final String descripcion, final int capacidadPersonas, 
                            final int tiempoUso, final String unidadTiempoUso, final String normas, 
                            final ResidentialComplexDomain conjuntoResidencial) {
        super(id);
        setName(name);
        setDescripcion(descripcion);
        setCapacidadPersonas(capacidadPersonas);
        setTiempoUso(tiempoUso);
        setUnidadTiempoUso(unidadTiempoUso);
        setNormas(normas);
        setConjuntoResidencial(conjuntoResidencial);
    }

    // Método estático para crear una instancia con parámetros
    public static ComunZoneDomain create(final UUID id, final String name, final String descripcion, final int capacidadPersonas, 
                                         final int tiempoUso, final String unidadTiempoUso, final String normas, 
                                         final ResidentialComplexDomain conjuntoResidencial) {
        return new ComunZoneDomain(id, name, descripcion, capacidadPersonas, tiempoUso, unidadTiempoUso, normas, conjuntoResidencial);
    }

    // Método estático para crear una instancia vacía por defecto
    public static ComunZoneDomain create() {
        return new ComunZoneDomain(UUIDHelper.getDefault(), TextHelper.EMPTY, TextHelper.EMPTY, NumericHelper.CERO, 
                                   NumericHelper.CERO, TextHelper.EMPTY, TextHelper.EMPTY, ResidentialComplexDomain.create());
    }

    // Getters y Setters

    public String getName() {
        return name;
    }

    private void setName(final String name) {
        this.name = TextHelper.applyTrim(name);
    }

    public String getDescripcion() {
        return descripcion;
    }

    private void setDescripcion(final String descripcion) {
        this.descripcion = TextHelper.applyTrim(descripcion);
    }

    public int getCapacidadPersonas() {
        return capacidadPersonas;
    }

    private void setCapacidadPersonas(final int capacidadPersonas) {
        this.capacidadPersonas = (capacidadPersonas > 0) ? capacidadPersonas : NumericHelper.CERO;
    }

    public int getTiempoUso() {
        return tiempoUso;
    }

    private void setTiempoUso(final int tiempoUso) {
        this.tiempoUso = (tiempoUso > 0) ? tiempoUso : NumericHelper.CERO;
    }

    public String getUnidadTiempoUso() {
        return unidadTiempoUso;
    }

    private void setUnidadTiempoUso(final String unidadTiempoUso) {
        this.unidadTiempoUso = TextHelper.applyTrim(unidadTiempoUso);
    }

    public String getNormas() {
        return normas;
    }

    private void setNormas(final String normas) {
        this.normas = TextHelper.applyTrim(normas);
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
