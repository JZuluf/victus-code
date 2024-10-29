package co.edu.uco.victusresidencias.domain;

import java.time.LocalDateTime;

import java.util.UUID;

import co.edu.uco.victusresidencias.crosscutting.helpers.DateHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.TextHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.UUIDHelper;

public class TurnDomain extends Domain {
    
    private String name;
    private String numeroTurno;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFin;
    private Boolean estado;
    private ScheduledDomain agenda;

    // Constructor privado
    private TurnDomain(final UUID id, final String name, final String numeroTurno, final LocalDateTime horaInicio,
                       final LocalDateTime horaFin, final Boolean estado, final ScheduledDomain agenda) {
        super(id);
        setName(name);
        setNumeroTurno(numeroTurno);
        setHoraInicio(horaInicio);
        setHoraFin(horaFin);
        setEstado(estado);
        setAgenda(agenda);
    }

    // Método estático para crear una instancia con parámetros
    public static TurnDomain create(final UUID id, final String name, final String numeroTurno, final LocalDateTime horaInicio, 
                                    final LocalDateTime horaFin, final Boolean estado, final ScheduledDomain agenda) {
        return new TurnDomain(id, name, numeroTurno, horaInicio, horaFin, estado, agenda);
    }

    // Método estático para crear una instancia vacía por defecto
    public static TurnDomain create() {
        return new TurnDomain(UUIDHelper.getDefault(), TextHelper.EMPTY, TextHelper.EMPTY, DateHelper.DEFAULT_DATE_TIME,
                              DateHelper.DEFAULT_DATE_TIME, true, ScheduledDomain.create());
    }

    // Getters y Setters

    public String getName() {
        return name;
    }

    private void setName(final String name) {
        this.name = TextHelper.applyTrim(name);
    }

    public String getNumeroTurno() {
        return numeroTurno;
    }

    private void setNumeroTurno(final String numeroTurno) {
        this.numeroTurno = TextHelper.applyTrim(numeroTurno);
    }

    public LocalDateTime getHoraInicio() {
        return horaInicio;
    }

    private void setHoraInicio(final LocalDateTime horaInicio) {
        this.horaInicio = (horaInicio != null) ? horaInicio : DateHelper.DEFAULT_DATE_TIME;
    }

    public LocalDateTime getHoraFin() {
        return horaFin;
    }

    private void setHoraFin(final LocalDateTime horaFin) {
        this.horaFin = (horaFin != null) ? horaFin : DateHelper.DEFAULT_DATE_TIME;
    }

    public Boolean getEstado() {
        return estado;
    }

    private void setEstado(final Boolean estado) {
        this.estado = (estado != null) ? estado : Boolean.TRUE;
    }

    public ScheduledDomain getAgenda() {
        return agenda;
    }

    private void setAgenda(final ScheduledDomain agenda) {
        this.agenda = (agenda != null) ? agenda : ScheduledDomain.create();
    }

    @Override
    public UUID getId() {
        return super.getId();
    }
}
