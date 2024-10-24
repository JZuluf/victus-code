package co.edu.uco.victusresidencias.entity;

import java.time.LocalDateTime;

import java.util.UUID;

import co.edu.uco.crosscutting.helpers.DateHelper;
import co.edu.uco.crosscutting.helpers.ObjectHelper;
import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;

public class ScheduledEntity extends DomainEntity {
	
	private String name;
	private Boolean disponibilidad;
	private LocalDateTime fechaHoraInicio;
	private LocalDateTime fechaHoraFin;
	private ComunZoneEntity zonaComun;
	
	
	public ScheduledEntity() {
		super(UUIDHelper.getDefault());
		setName(TextHelper.EMPTY);
		setDisponibilidad(true);
		setFechaHoraInicio(DateHelper.DEFAULT_DATE_TIME);
		setFechaHoraFin(DateHelper.DEFAULT_DATE_TIME);
		setComunZone(new ComunZoneEntity());
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = TextHelper.applyTrim(name);
	}
	
	
	public Boolean getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(Boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public LocalDateTime getFechaHoraInicio() {
		return fechaHoraInicio;
	}

	public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
		this.fechaHoraInicio = fechaHoraInicio;
	}

	public LocalDateTime getFechaHoraFin() {
		return fechaHoraFin;
	}

	public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
		this.fechaHoraFin = fechaHoraFin;
	}

	@Override
	public void setId(final UUID id) {
		super.setId(id);
	}
	@Override
	public UUID getId() {
		return super.getId();
	}

	public ComunZoneEntity getComunZone() {
		return zonaComun;
	}

	public void setComunZone(final ComunZoneEntity zonaComun) {
		this.zonaComun = ObjectHelper.getDefault(zonaComun, new ComunZoneEntity());
	}
	
}