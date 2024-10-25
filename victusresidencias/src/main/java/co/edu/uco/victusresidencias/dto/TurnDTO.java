package co.edu.uco.victusresidencias.dto;

import java.time.LocalDateTime;

import co.edu.uco.crosscutting.helpers.DateHelper;
import co.edu.uco.crosscutting.helpers.ObjectHelper;
import co.edu.uco.crosscutting.helpers.TextHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;

public class TurnDTO extends DomainDTO {

    private String name;
    private String numeroTurno;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFin;
    private Boolean estado;
    private ScheduledDTO agenda;

    // Constructor privado
    private TurnDTO() {
        super(UUIDHelper.getDefaultAsString());
        setName(TextHelper.EMPTY);
        setNumeroTurno(TextHelper.EMPTY);
        setHoraInicio(DateHelper.DEFAULT_DATE_TIME);
        setHoraFin(DateHelper.DEFAULT_DATE_TIME);
        setEstado(true);
        setAgenda(ScheduledDTO.create());
    }

    // Método estático para crear una instancia
    public static TurnDTO create() {
        return new TurnDTO();
    }

    // Getters y Setters con return this para fluidez

    public String getName() {
        return name;
    }

    public TurnDTO setName(String name) {
        this.name = TextHelper.applyTrim(name);
        return this;
    }

    public String getNumeroTurno() {
        return numeroTurno;
    }

    public TurnDTO setNumeroTurno(String numeroTurno) {
        this.numeroTurno = TextHelper.applyTrim(numeroTurno);
        return this;
    }

    public LocalDateTime getHoraInicio() {
        return horaInicio;
    }

    public TurnDTO setHoraInicio(LocalDateTime horaInicio) {
        this.horaInicio = ObjectHelper.getDefault(horaInicio, DateHelper.DEFAULT_DATE_TIME);
        return this;
    }

    public LocalDateTime getHoraFin() {
        return horaFin;
    }

    public TurnDTO setHoraFin(LocalDateTime horaFin) {
        this.horaFin = ObjectHelper.getDefault(horaFin, DateHelper.DEFAULT_DATE_TIME);
        return this;
    }

    public Boolean getEstado() {
        return estado;
    }

    public TurnDTO setEstado(Boolean estado) {
        this.estado = ObjectHelper.getDefault(estado, true);
        return this;
    }

    public ScheduledDTO getAgenda() {
        return agenda;
    }

    public TurnDTO setAgenda(ScheduledDTO agenda) {
        this.agenda = ObjectHelper.getDefault(agenda, ScheduledDTO.create());
        return this;
    }

    // Métodos para el manejo del ID heredado de DomainDTO
    public TurnDTO setId(final String id) {
        super.setIdentifier(id);
        return this;
    }

    @Override
    public String getId() {
        return super.getId();
    }
}
