package co.edu.uco.victusresidencias.entity;

import java.util.UUID;

import co.edu.uco.crosscutting.helpers.NumericHelper;
import co.edu.uco.crosscutting.helpers.ObjectHelper;
import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;

public class ComunZoneEntity extends DomainEntity {
	
	private String name;
	private String description;
	private int peopleCapacity;
	private int usingTime;
	private String usingTimeUnit;
	private String rule;
	private ResidentialComplexEntity residentialComplex;


	public ComunZoneEntity() {
		super(UUIDHelper.getDefault());
		setName(TextHelper.EMPTY);
		setDescription(TextHelper.EMPTY);
		setPeopleCapacity(NumericHelper.CERO);
		setUsingTime(NumericHelper.CERO);
		setUsingTimeUnit(TextHelper.EMPTY);
		setRule(TextHelper.EMPTY);
		setResidentialComplex(new ResidentialComplexEntity());
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = TextHelper.applyTrim(name);
	}
	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPeopleCapacity() {
		return peopleCapacity;
	}

	public void setPeopleCapacity(int peopleCapacity) {
		this.peopleCapacity = peopleCapacity;
	}

	public int getUsingTime() {
		return usingTime;
	}

	public void setUsingTime(int usingTime) {
		this.usingTime = usingTime;
	}

	public String getUsingTimeUnit() {
		return usingTimeUnit;
	}

	public void setUsingTimeUnit(String usingTimeUnit) {
		this.usingTimeUnit = usingTimeUnit;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
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
		return residentialComplex;
	}

	public void setResidentialComplex(final ResidentialComplexEntity residentialComplex) {
		this.residentialComplex = ObjectHelper.getDefault(residentialComplex, new ResidentialComplexEntity());
	}
}