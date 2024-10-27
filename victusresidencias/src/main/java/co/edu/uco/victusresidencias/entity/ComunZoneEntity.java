package co.edu.uco.victusresidencias.entity;

import java.util.UUID;

import co.edu.uco.victusresidencias.crosscutting.helpers.NumericHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.ObjectHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.TextHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.UUIDHelper;

public class ComunZoneEntity extends DomainEntity {
	
	private String name;
	private String descripcion;
	private int capacidadPersonas;
	private int tiempoUso;
	private String unidadTiempoUso;
	private String normas;
	private ResidentialComplexEntity conjuntoResidencial;


	public ComunZoneEntity() {
		super(UUIDHelper.getDefault());
		setName(TextHelper.EMPTY);
		setDescripcion(TextHelper.EMPTY);
		setCapacidadPersonas(NumericHelper.CERO);
		setTiempoUso(NumericHelper.CERO);
		setUnidadTiempoUso(TextHelper.EMPTY);
		setNormas(TextHelper.EMPTY);
		setConjuntoResidencial(new ResidentialComplexEntity());
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = TextHelper.applyTrim(name);
	}
	
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCapacidadPersonas() {
		return capacidadPersonas;
	}

	public void setCapacidadPersonas(int capacidadPersonas) {
		this.capacidadPersonas = capacidadPersonas;
	}

	public int getTiempoUso() {
		return tiempoUso;
	}

	public void setTiempoUso(int tiempoUso) {
		this.tiempoUso = tiempoUso;
	}

	public String getUnidadTiempoUso() {
		return unidadTiempoUso;
	}

	public void setUnidadTiempoUso(String unidadTiempoUso) {
		this.unidadTiempoUso = unidadTiempoUso;
	}

	public String getNormas() {
		return normas;
	}

	public void setNormas(String normas) {
		this.normas = normas;
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
}