package co.edu.uco.victusresidencias.domain;

import java.time.LocalDateTime;

import java.util.UUID;

import co.edu.uco.victusresidencias.crosscutting.helpers.DateHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.TextHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.UUIDHelper;

public class ScheduledDomain extends Domain {
    
    private String name;
    private Boolean disponibilidad;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private ComunZoneDomain zonaComun;

    // Constructor privado
    private ScheduledDomain(final UUID id, final String name, final Boolean disponibilidad, final LocalDateTime fechaHoraInicio,
                            final LocalDateTime fechaHoraFin, final ComunZoneDomain zonaComun) {
        super(id);
        setName(name);
        setDisponibilidad(disponibilidad);
        setFechaHoraInicio(fechaHoraInicio);
        setFechaHoraFin(fechaHoraFin);
        setZonaComun(zonaComun);
    }

    // Método estático para crear una instancia con parámetros
    public static ScheduledDomain create(final UUID id, final String name, final Boolean disponibilidad, final LocalDateTime fechaHoraInicio, 
                                         final LocalDateTime fechaHoraFin, final ComunZoneDomain zonaComun) {
        return new ScheduledDomain(id, name, disponibilidad, fechaHoraInicio, fechaHoraFin, zonaComun);
    }

    // Método estático para crear una instancia vacía por defecto
    public static ScheduledDomain create() {
        return new ScheduledDomain(UUIDHelper.getDefault(), TextHelper.EMPTY, true, DateHelper.DEFAULT_DATE_TIME, 
                                   DateHelper.DEFAULT_DATE_TIME, ComunZoneDomain.create());
    }

    // Getters y Setters

    public String getName() {
        return name;
    }

    private void setName(final String name) {
        this.name = TextHelper.applyTrim(name);
    }

    public Boolean getDisponibilidad() {
        return disponibilidad;
    }

    private void setDisponibilidad(final Boolean disponibilidad) {
        this.disponibilidad = (disponibilidad != null) ? disponibilidad : Boolean.TRUE;
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    private void setFechaHoraInicio(final LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = (fechaHoraInicio != null) ? fechaHoraInicio : DateHelper.DEFAULT_DATE_TIME;
    }

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    private void setFechaHoraFin(final LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = (fechaHoraFin != null) ? fechaHoraFin : DateHelper.DEFAULT_DATE_TIME;
    }

    public ComunZoneDomain getZonaComun() {
        return zonaComun;
    }

    private void setZonaComun(final ComunZoneDomain zonaComun) {
        this.zonaComun = (zonaComun != null) ? zonaComun : ComunZoneDomain.create();
    }

    @Override
    public UUID getId() {
        return super.getId();
    }
}
