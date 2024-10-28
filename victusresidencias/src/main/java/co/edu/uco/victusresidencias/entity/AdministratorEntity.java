package co.edu.uco.victusresidencias.entity;

import java.util.UUID;
import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;

public class AdministratorEntity extends DomainEntity {
	
	private String name;
	private String lastName;
	private String documentType;
	private int documentNumber;
	private int contactNumber;
	private String email;
	private String password;


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

	public void setLastName(String lastName) {
		this.lastName = TextHelper.applyTrim(lastName);
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public int getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(int documentNumber) {
		this.documentNumber = documentNumber;
	}

	public int getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(int contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}