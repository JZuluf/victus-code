package co.edu.uco.victusresidencias.entity;

import java.util.UUID;


import co.edu.uco.crosscutting.helpers.ObjectHelper;
import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;

public class PropertyZoneEntity extends DomainEntity {
	
	private String tipoZonaInmueble;
	private String numeroZonaInmueble;
	private ResidentialComplexEntity conjuntoResidencial;


	public PropertyZoneEntity() {
		super(UUIDHelper.getDefault());
		setTipoZonaInmueble(TextHelper.EMPTY);
		setConjuntoResidencial(new ResidentialComplexEntity());
	}
	
	public String getTipoZonaInmueble() {
		return tipoZonaInmueble;
	}

	public void setTipoZonaInmueble(String tipoZonaInmueble) {
		this.tipoZonaInmueble = TextHelper.applyTrim(tipoZonaInmueble);
	}
	
	public String getNumeroZonaInmueble() {
		return numeroZonaInmueble;
	}

	public void setNumeroZonaInmueble(String numeroZonaInmueble) {
		this.numeroZonaInmueble = numeroZonaInmueble;
	}

	@Override
	public void setId(final UUID id) {
		super.setId(id);
	}
	@Override
	public UUID getId() {
		return super.getId();
	}

	public ResidentialComplexEntity getConjuntoResidencial() {
		return conjuntoResidencial;
	}

	public void setConjuntoResidencial(final ResidentialComplexEntity conjuntoResidencial) {
		this.conjuntoResidencial = ObjectHelper.getDefault(conjuntoResidencial, new ResidentialComplexEntity());
	}
	
	
//	public static void main(String[] args) {
//		CountryDTO country = new CountryDTO();
//		country.setId(null);
//		
//		System.out.println(country.getId());
//		System.out.println(country.getName());
//	}
}