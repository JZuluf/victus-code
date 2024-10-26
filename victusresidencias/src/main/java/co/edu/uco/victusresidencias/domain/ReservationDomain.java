package co.edu.uco.victusresidencias.domain;

import java.util.UUID;

import co.edu.uco.crosscutting.helpers.NumericHelper;
import co.edu.uco.crosscutting.helpers.UUIDHelper;

public class ReservationDomain extends Domain {

    private int numeroInvitado;
    private TurnDomain turno;
    private ResidentDomain residente;

    // Constructor privado
    private ReservationDomain(final UUID id, final int numeroInvitado, final TurnDomain turno, final ResidentDomain residente) {
        super(id);
        setNumeroInvitado(numeroInvitado);
        setTurno(turno);
        setResidente(residente);
    }

    // Método estático para crear una instancia con parámetros
    public static ReservationDomain create(final UUID id, final int numeroInvitado, final TurnDomain turno, final ResidentDomain residente) {
        return new ReservationDomain(id, numeroInvitado, turno, residente);
    }

    // Método estático para crear una instancia por defecto
    public static ReservationDomain create() {
        return new ReservationDomain(UUIDHelper.getDefault(), NumericHelper.CERO, TurnDomain.create(), ResidentDomain.create());
    }

    // Getters y Setters

    public int getNumeroInvitado() {
        return numeroInvitado;
    }

    private void setNumeroInvitado(final int numeroInvitado) {
        this.numeroInvitado = NumericHelper.getDefault(numeroInvitado, NumericHelper.CERO);
    }

    public TurnDomain getTurno() {
        return turno;
    }

    private void setTurno(final TurnDomain turno) {
        this.turno = (turno != null) ? turno : TurnDomain.create();
    }

    public ResidentDomain getResidente() {
        return residente;
    }

    private void setResidente(final ResidentDomain residente) {
        this.residente = (residente != null) ? residente : ResidentDomain.create();
    }

    @Override
    public UUID getId() {
        return super.getId();
    }
}
