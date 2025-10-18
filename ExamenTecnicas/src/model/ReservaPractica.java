/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.time.LocalDate;
import java.time.LocalTime;
/**
 *
 * @author alexm
 */
public class ReservaPractica extends Reserva {
    private String equipoRequerido;

    public ReservaPractica(String idAula, String responsable, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, String equipoRequerido) {
        super(idAula, responsable, fecha, horaInicio, horaFin);
        this.equipoRequerido = equipoRequerido;
    }

    public String getEquipoRequerido() { return equipoRequerido; }
    public void setEquipoRequerido(String equipoRequerido) { this.equipoRequerido = equipoRequerido; }

    @Override
    public String toString() {
        return super.toString() + ", Tipo=Práctica, Equipo='" + equipoRequerido + "']";
    }
}