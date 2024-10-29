package co.edu.uco.victusresidencias.entity;

import java.time.LocalDate;

import java.util.UUID;

import co.edu.uco.victusresidencias.crosscutting.helpers.DateHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.NumericHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.ObjectHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.TextHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.UUIDHelper;

public class ResidentEntity extends DomainEntity {
	
	private String name;
	private String lastName;
	private String documentType;
	private int documentNumber;
	private LocalDate birthDate;
	private int contactNumber;
	private String password;
	private PropertyEntity property;


	public ResidentEntity() {
		super(UUIDHelper.getDefault());
		setName(TextHelper.EMPTY);
		setLastName(TextHelper.EMPTY);
		setDocumetType(TextHelper.EMPTY);
		setDocumentNumber(NumericHelper.CERO);
		setBirthDate(DateHelper.DEFAULT_DATE);
		setContactNumber(NumericHelper.CERO);
		setPassword(TextHelper.EMPTY);
		setProperty(new PropertyEntity());
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = TextHelper.applyTrim(name);
	}
	
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumetType(String documentType) {
		this.documentType = documentType;
	}

	public int getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(int documentNumber) {
		this.documentNumber = documentNumber;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public int getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(int contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void setId(final UUID id) {
		super.setId(id);
	}
	@Override
	public UUID getId() {
		return super.getId();
	}
	
	public PropertyEntity getProperty() {
		return property;
	}

	public void setProperty(final PropertyEntity property) {
		this.property = ObjectHelper.getDefault(property, new PropertyEntity());
	}
	
	
}