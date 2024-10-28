package co.edu.uco.victusresidencias.dto;

import co.edu.uco.victusresidencias.crosscutting.helpers.TextHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.UUIDHelper;

public class AdministratorDTO extends DomainDTO {
    
    private String name;
    private String apellido;
    private String tipoDocumento;
    private int numeroDocumento;
    private int numeroContacto;
    private String correoElectronico;
    private String contrasena;

    public AdministratorDTO() {
        super(UUIDHelper.getDefaultAsString());
        setName(TextHelper.EMPTY);
        setApellido(TextHelper.EMPTY);
        setTipoDocumento(TextHelper.EMPTY);
        setCorreoElectronico(TextHelper.EMPTY);
        setContrasena(TextHelper.EMPTY);
    }

    // Método estático para crear una instancia
    public static AdministratorDTO create() {
        return new AdministratorDTO();
    }

    // Getters y Setters con return this para fluidez

    public String getName() {
        return name;
    }

    public AdministratorDTO setName(String name) {
        this.name = TextHelper.applyTrim(name);
        return this;
    }

    public String getApellido() {
        return apellido;
    }

    public AdministratorDTO setApellido(String apellido) {
        this.apellido = TextHelper.applyTrim(apellido);
        return this;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public AdministratorDTO setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = TextHelper.applyTrim(tipoDocumento);
        return this;
    }

    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public AdministratorDTO setNumeroDocumento(int numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
        return this;
    }

    public int getNumeroContacto() {
        return numeroContacto;
    }

    public AdministratorDTO setNumeroContacto(int numeroContacto) {
        this.numeroContacto = numeroContacto;
        return this;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public AdministratorDTO setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = TextHelper.applyTrim(correoElectronico);
        return this;
    }

    public String getContrasena() {
        return contrasena;
    }

    public AdministratorDTO setContrasena(String contrasena) {
        this.contrasena = TextHelper.applyTrim(contrasena);
        return this;
    }

    // Métodos para el manejo del ID heredado de DomainDTO
    public AdministratorDTO setId(final String id) {
        super.setIdentifier(id);
        return this;
    }

    @Override
    public String getId() {
        return super.getId();
    }
}
