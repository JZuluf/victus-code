package co.edu.uco.victusresidencias.domain;

import java.util.UUID;


import co.edu.uco.victusresidencias.crosscutting.helpers.TextHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.UUIDHelper;

public class AdministratorDomain extends Domain {

    private String name;
    private String apellido;
    private String tipoDocumento;
    private int numeroDocumento;
    private int numeroContacto;
    private String correoElectronico;
    private String contrasena;

    //construtor privado solo el Domain
    private AdministratorDomain(final UUID id, final String name, final String apellido, final String tipoDocumento, 
                                final int numeroDocumento, final int numeroContacto, final String correoElectronico, final String contrasena) {
        super(id);
        setName(name);
        setApellido(apellido);
        setTipoDocumento(tipoDocumento);
        setNumeroDocumento(numeroDocumento);
        setNumeroContacto(numeroContacto);
        setCorreoElectronico(correoElectronico);
        setContrasena(contrasena);
    }

    // Método estático para crear una instancia
    public static final AdministratorDomain create(final UUID id, final String name, final String apellido, 
                                                   final String tipoDocumento, final int numeroDocumento, final int numeroContacto, 
                                                   final String correoElectronico, final String contrasena) {
        return new AdministratorDomain(id, name, apellido, tipoDocumento, numeroDocumento, numeroContacto, correoElectronico, contrasena);
    }

    // Método estático para crear una instancia vacía por defecto
    public static final AdministratorDomain create() {
        return new AdministratorDomain(UUIDHelper.getDefault(), TextHelper.EMPTY, TextHelper.EMPTY, TextHelper.EMPTY, 0, 0, TextHelper.EMPTY, TextHelper.EMPTY);
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
        this.numeroDocumento = numeroDocumento;
    }

    public int getNumeroContacto() {
        return numeroContacto;
    }

    private void setNumeroContacto(final int numeroContacto) {
        this.numeroContacto = numeroContacto;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    private void setCorreoElectronico(final String correoElectronico) {
        this.correoElectronico = TextHelper.applyTrim(correoElectronico);
    }

    public String getContrasena() {
        return contrasena;
    }

    private void setContrasena(final String contrasena) {
        this.contrasena = TextHelper.applyTrim(contrasena);
    }

    @Override
    public UUID getId() {
        return super.getId();
    }
}
