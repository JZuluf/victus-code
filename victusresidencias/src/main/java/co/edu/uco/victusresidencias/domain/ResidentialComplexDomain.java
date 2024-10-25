package co.edu.uco.victusresidencias.domain;

import java.util.UUID;

import co.edu.uco.crosscutting.helpers.NumericHelper;
import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;

public class ResidentialComplexDomain extends Domain {

    private String name;
    private String direccion;
    private CityDomain city;
    private int contactoRecepcion;
    private String descripcion;
    private AdministratorDomain administrador;

    // Constructor privado
    private ResidentialComplexDomain(final UUID id, final String name, final String direccion, final CityDomain city, final int contactoRecepcion, final String descripcion, final AdministratorDomain administrador) {
        super(id);
        setName(name);
        setDireccion(direccion);
        setCity(city);
        setContactoRecepcion(contactoRecepcion);
        setDescripcion(descripcion);
        setAdministrador(administrador);
    }

    // Método estático para crear una instancia con parámetros
    public static ResidentialComplexDomain create(final UUID id, final String name, final String direccion, final CityDomain city, final int contactoRecepcion, final String descripcion, final AdministratorDomain administrador) {
        return new ResidentialComplexDomain(id, name, direccion, city, contactoRecepcion, descripcion, administrador);
    }

    // Método estático para crear una instancia vacía por defecto
    public static ResidentialComplexDomain create() {
        return new ResidentialComplexDomain(UUIDHelper.getDefault(), TextHelper.EMPTY, TextHelper.EMPTY, CityDomain.create(), NumericHelper.CERO, TextHelper.EMPTY, AdministratorDomain.create());
    }

    // Getters y Setters

    public String getName() {
        return name;
    }

    private void setName(final String name) {
        this.name = TextHelper.applyTrim(name);
    }

    public String getDireccion() {
        return direccion;
    }

    private void setDireccion(final String direccion) {
        this.direccion = TextHelper.applyTrim(direccion);
    }

    public CityDomain getCity() {
        return city;
    }

    private void setCity(final CityDomain city) {
        this.city = (city != null) ? city : CityDomain.create();  // Si city es nulo, establece una ciudad vacía por defecto
    }

    public int getContactoRecepcion() {
        return contactoRecepcion;
    }

    private void setContactoRecepcion(final int contactoRecepcion) {
        this.contactoRecepcion = (contactoRecepcion >= 0) ? contactoRecepcion : NumericHelper.CERO;
    }

    public String getDescripcion() {
        return descripcion;
    }

    private void setDescripcion(final String descripcion) {
        this.descripcion = TextHelper.applyTrim(descripcion);
    }

    public AdministratorDomain getAdministrador() {
        return administrador;
    }

    private void setAdministrador(final AdministratorDomain administrador) {
        this.administrador = (administrador != null) ? administrador : AdministratorDomain.create();  // Si el administrador es nulo, se crea uno por defecto
    }

    @Override
    public UUID getId() {
        return super.getId();
    }
}
