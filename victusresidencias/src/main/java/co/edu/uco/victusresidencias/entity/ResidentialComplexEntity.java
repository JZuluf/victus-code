package co.edu.uco.victusresidencias.entity;

import java.util.UUID;

import co.edu.uco.crosscutting.helpers.NumericHelper;
import co.edu.uco.crosscutting.helpers.ObjectHelper;
import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;

public class ResidentialComplexEntity extends DomainEntity {
	
	private String name;
	private String direccion;
	private CityEntity city;
	private int contactoRecepcion;
	private String descripcion;
	private AdministratorEntity administrador;


	public ResidentialComplexEntity() {
		super(UUIDHelper.getDefault());
		setName(TextHelper.EMPTY);
		setAdministrator(new AdministratorEntity());
		setCity(new CityEntity());
		setContactoRecepcion(NumericHelper.CERO);
		setDireccion(TextHelper.EMPTY);
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

	public CityEntity getState() {
		return city;
	}

	public void setCity(final CityEntity state) {
		this.city = ObjectHelper.getDefault(state, new CityEntity());
	}
	
	public AdministratorEntity getAdministrator() {
		return administrador;
	}

	public void setAdministrator(final AdministratorEntity administrador) {
		this.administrador = ObjectHelper.getDefault(administrador, new AdministratorEntity());
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getContactoRecepcion() {
		return contactoRecepcion;
	}

	public void setContactoRecepcion(int contactoRecepcion) {
		this.contactoRecepcion = contactoRecepcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}