package co.edu.uco.victusresidencias.entity;

import java.util.UUID;


import co.edu.uco.crosscutting.helpers.ObjectHelper;
import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;

public class ReservationEntity extends DomainEntity {
	
	private String numeroInvitado;
	private TurnEntity turno;
	private ResidentEntity residente;


	public ReservationEntity() {
		super(UUIDHelper.getDefault());
		setNumeroInvitado(numeroInvitado);
		setTurno(new TurnEntity());
		setResidente(new ResidentEntity());
	}
	
	public String getNumeroInvitado() {
		return numeroInvitado;
	}

	public void setNumeroInvitado(String numeroInvitado) {
		this.numeroInvitado = numeroInvitado;
	}

	@Override
	public void setId(final UUID id) {
		super.setId(id);
	}
	@Override
	public UUID getId() {
		return super.getId();
	}

	public TurnEntity getTurno() {
		return turno;
	}

	public void setTurno(final TurnEntity turno) {
		this.turno = ObjectHelper.getDefault(turno, new TurnEntity());
	}
	
	public TurnEntity getResidente() {
		return turno;
	}

	public void setResidente(final ResidentEntity residente) {
		this.residente = ObjectHelper.getDefault(residente, new ResidentEntity());
	}
	
	
//	public static void main(String[] args) {
//		CountryDTO country = new CountryDTO();
//		country.setId(null);
//		
//		System.out.println(country.getId());
//		System.out.println(country.getName());
//	}
}