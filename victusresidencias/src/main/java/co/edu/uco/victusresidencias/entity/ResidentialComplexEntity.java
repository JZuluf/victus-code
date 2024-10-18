package co.edu.uco.victusresidencias.entity;

import java.util.UUID;


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
		setState(new CityEntity());
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

	public void setState(final CityEntity state) {
		this.city = ObjectHelper.getDefault(state, new CityEntity());
	}
	
	public AdministratorEntity getAdministrator() {
		return administrador;
	}

	public void setAdministrator(final AdministratorEntity administrador) {
		this.administrador = ObjectHelper.getDefault(administrador, new AdministratorEntity());
	}
	
	
//	public static void main(String[] args) {
//		CountryDTO country = new CountryDTO();
//		country.setId(null);
//		
//		System.out.println(country.getId());
//		System.out.println(country.getName());
//	}
}