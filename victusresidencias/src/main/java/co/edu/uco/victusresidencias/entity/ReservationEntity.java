package co.edu.uco.victusresidencias.entity;

import java.util.UUID;


import co.edu.uco.victusresidencias.crosscutting.helpers.NumericHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.ObjectHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.UUIDHelper;

public class ReservationEntity extends DomainEntity {
	
	private int numeroInvitado;
	private TurnEntity turno;
	private ResidentEntity residente;


	public ReservationEntity() {
		super(UUIDHelper.getDefault());
		setNumeroInvitado(NumericHelper.CERO);
		setTurno(new TurnEntity());
		setResidente(new ResidentEntity());
	}
	
	public int getNumeroInvitado() {
		return numeroInvitado;
	}

	public void setNumeroInvitado(int numeroInvitado) {
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
	
	public ResidentEntity getResidente() {
		return residente;
	}

	public void setResidente(final ResidentEntity residente) {
		this.residente = ObjectHelper.getDefault(residente, new ResidentEntity());
	}
	

}