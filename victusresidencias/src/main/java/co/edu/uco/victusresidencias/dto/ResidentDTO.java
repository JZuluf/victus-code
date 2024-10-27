package co.edu.uco.victusresidencias.dto;

import java.time.LocalDate;

import co.edu.uco.victusresidencias.crosscutting.helpers.DateHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.NumericHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.ObjectHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.TextHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.UUIDHelper;

public class ResidentDTO extends DomainDTO {
    
    private String name;
    private String apellido;
    private String tipoDocumento;
    private int numeroDocumento;
    private LocalDate fecha;
    private int numeroContacto;
    private String contraseña;
    private PropertyDTO inmueble;

    // Constructor privado
    private ResidentDTO() {
        super(UUIDHelper.getDefaultAsString());
        setName(TextHelper.EMPTY);
        setApellido(TextHelper.EMPTY);
        setTipoDocumento(TextHelper.EMPTY);
        setNumeroDocumento(NumericHelper.CERO);
        setFecha(DateHelper.DEFAULT_DATE);
        setNumeroContacto(NumericHelper.CERO);
        setContraseña(TextHelper.EMPTY);
        setInmueble(PropertyDTO.create());
    }

    // Método estático para crear una instancia
    public static ResidentDTO create() {
        return new ResidentDTO();
    }

    // Getters y Setters con return this para fluidez

    public String getName() {
        return name;
    }

    public ResidentDTO setName(String name) {
        this.name = TextHelper.applyTrim(name);
        return this;
    }

    public String getApellido() {
        return apellido;
    }

    public ResidentDTO setApellido(String apellido) {
        this.apellido = TextHelper.applyTrim(apellido);
        return this;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public ResidentDTO setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = TextHelper.applyTrim(tipoDocumento);
        return this;
    }

    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public ResidentDTO setNumeroDocumento(int numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
        return this;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public ResidentDTO setFecha(LocalDate fecha) {
        this.fecha = ObjectHelper.getDefault(fecha, DateHelper.DEFAULT_DATE);
        return this;
    }

    public int getNumeroContacto() {
        return numeroContacto;
    }

    public ResidentDTO setNumeroContacto(int numeroContacto) {
        this.numeroContacto = numeroContacto;
        return this;
    }

    public String getContraseña() {
        return contraseña;
    }

    public ResidentDTO setContraseña(String contraseña) {
        this.contraseña = TextHelper.applyTrim(contraseña);
        return this;
    }

    public PropertyDTO getInmueble() {
        return inmueble;
    }

    public ResidentDTO setInmueble(PropertyDTO inmueble) {
        this.inmueble = ObjectHelper.getDefault(inmueble, PropertyDTO.create());
        return this;
    }

    // Métodos para el manejo del ID heredado de DomainDTO
    public ResidentDTO setId(final String id) {
        super.setIdentifier(id);
        return this;
    }

    @Override
    public String getId() {
        return super.getId();
    }
}
