package co.edu.uco.victusresidencias.dto;

import co.edu.uco.crosscutting.helpers.ObjectHelper;
import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;

public class AdministratorDTO extends DomainDTO {
	
	private String name;
	private String apellido;
	private String tipoDocumento;
	private int numeroDocumento;
	private int numeroContacto;
	private String correoElectronico;
	private String contrasena;

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
	
	
	
//	public static void main(String[] args) {
//		System.out.print(StateDTO.create().getCountry().getName());
//		System.out.print("\n");
//		System.out.print(StateDTO.create().getCountry().getId());
//	}
	

}