package co.edu.uco.victusresidencias.entity;

import java.util.UUID;

import co.edu.uco.victusresidencias.crosscutting.helpers.TextHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.UUIDHelper;

public class AdministratorEntity extends DomainEntity {
	
	private String name;
	private String lastName;
	private String tipoDocumento;
	private String numeroDocumento;
	private String numeroContacto;
	private String correoElectronico;
	private String contrasena;


	public AdministratorEntity() {
		super(UUIDHelper.getDefault());
		setName(TextHelper.EMPTY);
		
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String apellido) {
		this.lastName = TextHelper.applyTrim(apellido);
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getNumeroContacto() {
		return numeroContacto;
	}

	public void setNumeroContacto(String numeroContacto) {
		this.numeroContacto = numeroContacto;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getContraseña() {
		return contrasena;
	}

	public void setContraseña(String contraseña) {
		this.contrasena = contraseña;
	}
}