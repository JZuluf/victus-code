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
	private String apellido;
	private String tipoDocumento;
	private int numeroDocumento;
	private LocalDate fecha;
	private int numeroContacto;
	private String contraseña;
	private PropertyEntity inmueble;


	public ResidentEntity() {
		super(UUIDHelper.getDefault());
		setName(TextHelper.EMPTY);
		setApellido(TextHelper.EMPTY);
		setTipoDocumento(TextHelper.EMPTY);
		setNumeroDocumento(NumericHelper.CERO);
		setFecha(DateHelper.DEFAULT_DATE);
		setNumeroContacto(NumericHelper.CERO);
		setContraseña(TextHelper.EMPTY);
		setInmueble(new PropertyEntity());
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = TextHelper.applyTrim(name);
	}
	
	
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public int getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(int numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public int getNumeroContacto() {
		return numeroContacto;
	}

	public void setNumeroContacto(int numeroContacto) {
		this.numeroContacto = numeroContacto;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	@Override
	public void setId(final UUID id) {
		super.setId(id);
	}
	@Override
	public UUID getId() {
		return super.getId();
	}
	
	public PropertyEntity getInmueble() {
		return inmueble;
	}

	public void setInmueble(final PropertyEntity inmueble) {
		this.inmueble = ObjectHelper.getDefault(inmueble, new PropertyEntity());
	}
	
	
}