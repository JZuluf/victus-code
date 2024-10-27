package co.edu.uco.victusresidencias.dto;

import java.time.LocalDateTime;


import co.edu.uco.victusresidencias.crosscutting.helpers.DateHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.ObjectHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.TextHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.UUIDHelper;

public class ScheduledDTO extends DomainDTO {

    private String name;
    private Boolean disponibilidad;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private ComunZoneDTO zonaComun;

    // Constructor privado
    private ScheduledDTO() {
        super(UUIDHelper.getDefaultAsString());
        setName(TextHelper.EMPTY);
        setDisponibilidad(true);
        setFechaHoraInicio(DateHelper.DEFAULT_DATE_TIME);
        setFechaHoraFin(DateHelper.DEFAULT_DATE_TIME);
        setZonaComun(ComunZoneDTO.create());
    }

    // Método estático para crear una instancia
    public static ScheduledDTO create() {
        return new ScheduledDTO();
    }

    // Getters y Setters con return this para fluidez

    public String getName() {
        return name;
    }

    public ScheduledDTO setName(String name) {
        this.name = TextHelper.applyTrim(name);
        return this;
    }

    public Boolean getDisponibilidad() {
        return disponibilidad;
    }

    public ScheduledDTO setDisponibilidad(Boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
        return this;
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public ScheduledDTO setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = ObjectHelper.getDefault(fechaHoraInicio, DateHelper.DEFAULT_DATE_TIME);
        return this;
    }

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public ScheduledDTO setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = ObjectHelper.getDefault(fechaHoraFin, DateHelper.DEFAULT_DATE_TIME);
        return this;
    }

    public ComunZoneDTO getZonaComun() {
        return zonaComun;
    }

    public ScheduledDTO setZonaComun(ComunZoneDTO zonaComun) {
        this.zonaComun = ObjectHelper.getDefault(zonaComun, ComunZoneDTO.create());
        return this;
    }

    // Métodos para el manejo del ID heredado de DomainDTO
    public ScheduledDTO setId(final String id) {
        super.setIdentifier(id);
        return this;
    }

    @Override
    public String getId() {
        return super.getId();
    }
}
