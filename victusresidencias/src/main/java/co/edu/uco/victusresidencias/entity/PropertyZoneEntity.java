package co.edu.uco.victusresidencias.entity;

import java.util.UUID;

import co.edu.uco.crosscutting.helpers.NumericHelper;
import co.edu.uco.crosscutting.helpers.ObjectHelper;
import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;

public class PropertyZoneEntity extends DomainEntity {
	
	private String tipoZonaInmueble;
	private int numeroZonaInmueble;
	private ResidentialComplexEntity conjuntoResidencial;


	public PropertyZoneEntity() {
		super(UUIDHelper.getDefault());
		setPropertyZoneType(TextHelper.EMPTY);
		setNumeroZonaInmueble(NumericHelper.CERO);
		setResidentialComplex(new ResidentialComplexEntity());
	}
	
	public String getPropertyZoneType() {
		return tipoZonaInmueble;
	}

	public void setPropertyZoneType(String tipoZonaInmueble) {
		this.tipoZonaInmueble = TextHelper.applyTrim(tipoZonaInmueble);
	}
	
	public int getNumeroZonaInmueble() {
		return numeroZonaInmueble;
	}

	public void setNumeroZonaInmueble(int numeroZonaInmueble) {
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

	public ResidentialComplexEntity getResidentialComplex() {
		return conjuntoResidencial;
	}

	public void setResidentialComplex(final ResidentialComplexEntity conjuntoResidencial) {
		this.conjuntoResidencial = ObjectHelper.getDefault(conjuntoResidencial, new ResidentialComplexEntity());
	}
	
}