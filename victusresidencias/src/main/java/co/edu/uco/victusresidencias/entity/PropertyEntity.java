package co.edu.uco.victusresidencias.entity;

import java.util.UUID;

import co.edu.uco.crosscutting.helpers.ObjectHelper;
import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;

public class PropertyEntity extends DomainEntity {
	
	private String tipoInmueble;
	private int numeroVivienda;
	private PropertyZoneEntity zonaInmueble;
	
	public PropertyEntity() {
		super(UUIDHelper.getDefault());
		setTipoInmueble(TextHelper.EMPTY);
		setZonaInmueble(new PropertyZoneEntity());
	}
	
	public String getTipoInmueble() {
		return tipoInmueble;
	}

	public void setTipoInmueble(String tipoInmueble) {
		this.tipoInmueble = TextHelper.applyTrim(tipoInmueble);
	}
	
	
	public int getNumeroVivienda() {
		return numeroVivienda;
	}

	public void setNumeroVivienda(int numeroVivienda) {
		this.numeroVivienda = numeroVivienda;
	}

	@Override
	public void setId(final UUID id) {
		super.setId(id);
	}
	@Override
	public UUID getId() {
		return super.getId();
	}

	public PropertyZoneEntity getZonaInmueble() {
		return zonaInmueble;
	}

	public void setZonaInmueble(final PropertyZoneEntity zonaInmueble) {
		this.zonaInmueble = ObjectHelper.getDefault(zonaInmueble, new PropertyZoneEntity());
	}
	
	
//	public static void main(String[] args) {
//		CountryDTO country = new CountryDTO();
//		country.setId(null);
//		
//		System.out.println(country.getId());
//		System.out.println(country.getName());
//	}
}