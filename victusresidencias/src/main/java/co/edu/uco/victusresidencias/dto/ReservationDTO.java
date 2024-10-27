package co.edu.uco.victusresidencias.dto;

import co.edu.uco.victusresidencias.crosscutting.helpers.NumericHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.ObjectHelper;
import co.edu.uco.victusresidencias.crosscutting.helpers.UUIDHelper;

public class ReservationDTO extends DomainDTO {

    private int numeroInvitado;
    private TurnDTO turno;
    private ResidentDTO residente;

    // Constructor privado
    private ReservationDTO() {
        super(UUIDHelper.getDefaultAsString());
        setNumeroInvitado(NumericHelper.CERO);
        setTurno(TurnDTO.create());
        setResidente(ResidentDTO.create());
    }

    // Método estático para crear una instancia
    public static ReservationDTO create() {
        return new ReservationDTO();
    }

    // Getters y Setters con return this para fluidez

    public int getNumeroInvitado() {
        return numeroInvitado;
    }

    public ReservationDTO setNumeroInvitado(int numeroInvitado) {
        this.numeroInvitado = NumericHelper.getDefault(numeroInvitado, NumericHelper.CERO);
        return this;
    }

    public TurnDTO getTurno() {
        return turno;
    }

    public ReservationDTO setTurno(TurnDTO turno) {
        this.turno = ObjectHelper.getDefault(turno, TurnDTO.create());
        return this;
    }

    public ResidentDTO getResidente() {
        return residente;
    }

    public ReservationDTO setResidente(ResidentDTO residente) {
        this.residente = ObjectHelper.getDefault(residente, ResidentDTO.create());
        return this;
    }

    // Métodos para el manejo del ID heredado de DomainDTO
    public ReservationDTO setId(final String id) {
        super.setIdentifier(id);
        return this;
    }

    @Override
    public String getId() {
        return super.getId();
    }
}
