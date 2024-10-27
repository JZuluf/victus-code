package co.edu.uco.victusresidencias.entity;

import java.util.UUID;


import co.edu.uco.victusresidencias.crosscutting.helpers.NumericHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.ObjectHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.TextHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.UUIDHelper;

public class ResidentialComplexEntity extends DomainEntity {
	
	private String name;
	private String Address;
	private CityEntity city;
	private int contactReception;
	private String description;
	private AdministratorEntity administrator;


	public ResidentialComplexEntity() {
		super(UUIDHelper.getDefault());
		setName(TextHelper.EMPTY);
		setAdministrator(new AdministratorEntity());
		setCity(new CityEntity());
		setContactoRecepcion(NumericHelper.CERO);
		setAddress(TextHelper.EMPTY);
		setDescripcion(TextHelper.EMPTY);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = TextHelper.applyTrim(name);
	}
	@Override
	public void setId(final UUID id) {
		super.setId(id);
	}
	@Override
	public UUID getId() {
		return super.getId();
	}

	public CityEntity getCity() {
		return city;
	}

	public void setCity(final CityEntity state) {
		this.city = ObjectHelper.getDefault(state, new CityEntity());
	}
	
	public AdministratorEntity getAdministrator() {
		return administrator;
	}

	public void setAdministrator(final AdministratorEntity administrator) {
		this.administrator = ObjectHelper.getDefault(administrator, new AdministratorEntity());
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		this.Address = address;
	}

	public int getContactoRecepcion() {
		return contactReception;
	}

	public void setContactoRecepcion(int contactoRecepcion) {
		this.contactReception = contactoRecepcion;
	}

	public String getDescripcion() {
		return description;
	}

	public void setDescripcion(String descripcion) {
		this.description = descripcion;
	}
	
}