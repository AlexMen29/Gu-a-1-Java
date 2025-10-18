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
public class ReservaClase extends Reserva {
    private String carrera;

    public ReservaClase(String idAula, String responsable, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, String carrera) {
        super(idAula, responsable, fecha, horaInicio, horaFin);
        this.carrera = carrera;
    }

    public String getCarrera() { return carrera; }
    public void setCarrera(String carrera) { this.carrera = carrera; }

    @Override
    public String toString() {
        return super.toString() + ", Tipo=Clase, Carrera='" + carrera + "']";
    }
}