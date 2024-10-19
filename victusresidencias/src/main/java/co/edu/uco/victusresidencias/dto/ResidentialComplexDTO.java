package co.edu.uco.victusresidencias.dto;

import co.edu.uco.crosscutting.helpers.ObjectHelper;
import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;
import co.edu.uco.victusresidencias.entity.AdministratorEntity;
import co.edu.uco.victusresidencias.entity.CityEntity;

public class ResidentialComplexDTO extends DomainDTO {
	
	private String name;
	private String direccion;
	private CityEntity city;
	private int contactoRecepcion;
	private String descripcion;
	private AdministratorEntity administrador;

	
	public ResidentialComplexDTO() {
		super(UUIDHelper.getDefaultAsString());
		setName(TextHelper.EMPTY);
		setAdministrator(administrador);
		
	}
	
	
	
	public String getDireccion() {
		return direccion;
	}



	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}



	public CityEntity getCity() {
		return city;
	}



	public void setCity(CityEntity city) {
		this.city = city;
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



	public AdministratorEntity getAdministrador() {
		return administrador;
	}



	public void setAdministrator(AdministratorEntity administrador) {
		this.administrador = administrador;
	}



	public static final ResidentialComplexDTO create() {
		return new ResidentialComplexDTO();
	}
	
	public String getName() {
		return name;
	}

	public ResidentialComplexDTO setName(String name) {
		this.name = TextHelper.applyTrim(name);
		return this;
	}

	public ResidentialComplexDTO setId(final String id) {
		super.setIdentifier(id);
		return this;
	}
	@Override
	public String getId() {
		return super.getId();
	}

	
//	public static void main(String[] args) {
//		System.out.print(StateDTO.create().getCountry().getName());
//		System.out.print("\n");
//		System.out.print(StateDTO.create().getCountry().getId());
//	}
	

}