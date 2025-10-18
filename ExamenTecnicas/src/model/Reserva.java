/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import model.enums.EstadoReserva;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
/**
 *
 * @author alexm
 */
public class Reserva {
    
    protected UUID id;
    protected String idAula;
    protected String responsable;
    protected LocalDate fecha;
    protected LocalTime horaInicio;
    protected LocalTime horaFin;
    protected EstadoReserva estado;

    public Reserva(String idAula, String responsable, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin) {
        this.id = UUID.randomUUID(); 
        this.idAula = idAula;
        this.responsable = responsable;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estado = EstadoReserva.ACTIVA;
    }
    
    public UUID getId() { return id; }
    public String getIdAula() { return idAula; }
    public String getResponsable() { return responsable; }
    public LocalDate getFecha() { return fecha; }
    public LocalTime getHoraInicio() { return horaInicio; }
    public LocalTime getHoraFin() { return horaFin; }
    public EstadoReserva getEstado() { return estado; }
    public void setEstado(EstadoReserva estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "Reserva [ID=" + id + ", ID Aula=" + idAula + ", Responsable='" + responsable + 
               "', Fecha=" + fecha + ", Inicio=" + horaInicio + ", Fin=" + horaFin + ", Estado=" + estado;
    }
    
}
