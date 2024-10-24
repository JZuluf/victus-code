package co.edu.uco.victusresidencias.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import co.edu.uco.crosscutting.helpers.DateHelper;
import co.edu.uco.crosscutting.helpers.ObjectHelper;
import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;

public class TurnEntity extends DomainEntity {
	
	private String name;
	private String numeroTurno;
	private LocalDateTime horaInicio;
	private LocalDateTime horaFin;
	private Boolean estado;
	private ScheduledEntity agenda;
	
	
	public TurnEntity() {
		super(UUIDHelper.getDefault());
		setName(TextHelper.EMPTY);
		setNumeroTurno(TextHelper.EMPTY);
		setHoraInicio(DateHelper.DEFAULT_DATE_TIME);
		setHoraFin(DateHelper.DEFAULT_DATE_TIME);
		setEstado(true);
		setAgenda(new ScheduledEntity());
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = TextHelper.applyTrim(name);
	}
	
	public String getNumeroTurno() {
		return numeroTurno;
	}

	public void setNumeroTurno(String numeroTurno) {
		this.numeroTurno = numeroTurno;
	}

	public LocalDateTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalDateTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalDateTime getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(LocalDateTime horaFin) {
		this.horaFin = horaFin;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	@Override
	public void setId(final UUID id) {
		super.setId(id);
	}
	@Override
	public UUID getId() {
		return super.getId();
	}

	public ScheduledEntity getAgenda() {
		return agenda;
	}

	public void setAgenda(final ScheduledEntity agenda) {
		this.agenda = ObjectHelper.getDefault(agenda, new ScheduledEntity());
	}
}