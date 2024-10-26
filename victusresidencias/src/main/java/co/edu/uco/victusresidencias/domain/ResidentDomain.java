package co.edu.uco.victusresidencias.domain;

import java.time.LocalDate;
import java.util.UUID;

import co.edu.uco.crosscutting.helpers.DateHelper;
import co.edu.uco.crosscutting.helpers.NumericHelper;
import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;

public class ResidentDomain extends Domain {

    private String name;
    private String apellido;
    private String tipoDocumento;
    private int numeroDocumento;
    private LocalDate fechaNacimiento;
    private int numeroContacto;
    private String contraseña;
    private PropertyDomain inmueble;

    // Constructor privado
    private ResidentDomain(final UUID id, final String name, final String apellido, final String tipoDocumento, final int numeroDocumento, 
                           final LocalDate fechaNacimiento, final int numeroContacto, final String contraseña, final PropertyDomain inmueble) {
        super(id);
        setName(name);
        setApellido(apellido);
        setTipoDocumento(tipoDocumento);
        setNumeroDocumento(numeroDocumento);
        setFechaNacimiento(fechaNacimiento);
        setNumeroContacto(numeroContacto);
        setContraseña(contraseña);
        setInmueble(inmueble);
    }

    // Método estático para crear una instancia con parámetros
    public static ResidentDomain create(final UUID id, final String name, final String apellido, final String tipoDocumento, final int numeroDocumento, 
                                        final LocalDate fechaNacimiento, final int numeroContacto, final String contraseña, final PropertyDomain inmueble) {
        return new ResidentDomain(id, name, apellido, tipoDocumento, numeroDocumento, fechaNacimiento, numeroContacto, contraseña, inmueble);
    }

    // Método estático para crear una instancia vacía por defecto
    public static ResidentDomain create() {
        return new ResidentDomain(UUIDHelper.getDefault(), TextHelper.EMPTY, TextHelper.EMPTY, TextHelper.EMPTY, NumericHelper.CERO,
                                  DateHelper.DEFAULT_DATE, NumericHelper.CERO, TextHelper.EMPTY, PropertyDomain.create());
    }

    // Getters y Setters

    public String getName() {
        return name;
    }

    private void setName(final String name) {
        this.name = TextHelper.applyTrim(name);
    }

    public String getApellido() {
        return apellido;
    }

    private void setApellido(final String apellido) {
        this.apellido = TextHelper.applyTrim(apellido);
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    private void setTipoDocumento(final String tipoDocumento) {
        this.tipoDocumento = TextHelper.applyTrim(tipoDocumento);
    }

    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    private void setNumeroDocumento(final int numeroDocumento) {
        this.numeroDocumento = (numeroDocumento > 0) ? numeroDocumento : NumericHelper.CERO;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    private void setFechaNacimiento(final LocalDate fechaNacimiento) {
        this.fechaNacimiento = (fechaNacimiento != null) ? fechaNacimiento : DateHelper.DEFAULT_DATE;
    }

    public int getNumeroContacto() {
        return numeroContacto;
    }

    private void setNumeroContacto(final int numeroContacto) {
        this.numeroContacto = (numeroContacto > 0) ? numeroContacto : NumericHelper.CERO;
    }

    public String getContraseña() {
        return contraseña;
    }

    private void setContraseña(final String contraseña) {
        this.contraseña = TextHelper.applyTrim(contraseña);
    }

    public PropertyDomain getInmueble() {
        return inmueble;
    }

    private void setInmueble(final PropertyDomain inmueble) {
        this.inmueble = (inmueble != null) ? inmueble : PropertyDomain.create();
    }

    @Override
    public UUID getId() {
        return super.getId();
    }
}
