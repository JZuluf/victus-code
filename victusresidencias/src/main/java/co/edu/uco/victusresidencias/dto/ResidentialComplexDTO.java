package co.edu.uco.victusresidencias.dto;

import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;

public class ResidentialComplexDTO extends DomainDTO {

    private String name;
    private String address;
    private String phoneNumber;
    private AdministratorDTO administrator;

    // Constructor privado
    private ResidentialComplexDTO() {
        super(UUIDHelper.getDefaultAsString());
        setName(TextHelper.EMPTY);
        setAddress(TextHelper.EMPTY);
        setPhoneNumber(TextHelper.EMPTY);
        setAdministrator(AdministratorDTO.create());
    }

    // Método estático para crear una instancia
    public static ResidentialComplexDTO create() {
        return new ResidentialComplexDTO();
    }

    // Getters y Setters con return this para fluidez

    public String getName() {
        return name;
    }

    public ResidentialComplexDTO setName(String name) {
        this.name = TextHelper.applyTrim(name);
        return this;
    }

    public String getAddress() {
        return address;
    }

    public ResidentialComplexDTO setAddress(String address) {
        this.address = TextHelper.applyTrim(address);
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ResidentialComplexDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = TextHelper.applyTrim(phoneNumber);
        return this;
    }

    public AdministratorDTO getAdministrator() {
        return administrator;
    }

    public ResidentialComplexDTO setAdministrator(AdministratorDTO administrator) {
        this.administrator = administrator != null ? administrator : AdministratorDTO.create();
        return this;
    }

    // Métodos para el manejo del ID heredado de DomainDTO
    public ResidentialComplexDTO setId(final String id) {
        super.setIdentifier(id);
        return this;
    }

    @Override
    public String getId() {
        return super.getId();
    }
}
