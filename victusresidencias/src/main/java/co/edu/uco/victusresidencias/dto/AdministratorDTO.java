package co.edu.uco.victusresidencias.dto;

import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;

public class AdministratorDTO extends DomainDTO{
	
	private String name;

	public AdministratorDTO() {
		super(UUIDHelper.getDefaultAsString());
		setName(TextHelper.EMPTY);
	}
	public static final AdministratorDTO create() {
		return new AdministratorDTO();
	}
	
	public String getName() {
		return name;
	}

	public AdministratorDTO setName(String name) {
		this.name = TextHelper.applyTrim(name);
		return this;
	}

	public AdministratorDTO setId(final String id) {
		super.setIdentifier(id);
		return this;
	}
	@Override
	public String getId() {
		return super.getId();
	}
	
	
	
	
	
}
