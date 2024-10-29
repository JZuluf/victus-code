package co.edu.uco.victusresidencias.dto;

import co.edu.uco.victusresidencias.crosscutting.helpers.NumericHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.ObjectHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.TextHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.UUIDHelper;

public class ComunZoneDTO extends DomainDTO {

    private String name;
    private String descripcion;
    private int capacidadPersonas;
    private int tiempoUso;
    private String unidadTiempoUso;
    private String normas;
    private ResidentialComplexDTO conjuntoResidencial;

    // Constructor privado
    private ComunZoneDTO() {
        super(UUIDHelper.getDefaultAsString());
        setName(TextHelper.EMPTY);
        setDescripcion(TextHelper.EMPTY);
        setCapacidadPersonas(NumericHelper.CERO);
        setTiempoUso(NumericHelper.CERO);
        setUnidadTiempoUso(TextHelper.EMPTY);
        setNormas(TextHelper.EMPTY);
        setConjuntoResidencial(ResidentialComplexDTO.create());
    }

    // Método estático para crear una instancia
    public static ComunZoneDTO create() {
        return new ComunZoneDTO();
    }

    // Getters y Setters con return this para fluidez

    public String getName() {
        return name;
    }

    public ComunZoneDTO setName(String name) {
        this.name = TextHelper.applyTrim(name);
        return this;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public ComunZoneDTO setDescripcion(String descripcion) {
        this.descripcion = TextHelper.applyTrim(descripcion);
        return this;
    }

    public int getCapacidadPersonas() {
        return capacidadPersonas;
    }

    public ComunZoneDTO setCapacidadPersonas(int capacidadPersonas) {
        this.capacidadPersonas = capacidadPersonas;
        return this;
    }

    public int getTiempoUso() {
        return tiempoUso;
    }

    public ComunZoneDTO setTiempoUso(int tiempoUso) {
        this.tiempoUso = tiempoUso;
        return this;
    }

    public String getUnidadTiempoUso() {
        return unidadTiempoUso;
    }

    public ComunZoneDTO setUnidadTiempoUso(String unidadTiempoUso) {
        this.unidadTiempoUso = TextHelper.applyTrim(unidadTiempoUso);
        return this;
    }

    public String getNormas() {
        return normas;
    }

    public ComunZoneDTO setNormas(String normas) {
        this.normas = TextHelper.applyTrim(normas);
        return this;
    }

    public ResidentialComplexDTO getConjuntoResidencial() {
        return conjuntoResidencial;
    }

    public ComunZoneDTO setConjuntoResidencial(ResidentialComplexDTO conjuntoResidencial) {
        this.conjuntoResidencial = ObjectHelper.getDefault(conjuntoResidencial, ResidentialComplexDTO.create());
        return this;
    }

    // Métodos para el manejo del ID heredado de DomainDTO
    public ComunZoneDTO setId(final String id) {
        super.setIdentifier(id);
        return this;
    }

    @Override
    public String getId() {
        return super.getId();
    }
}
