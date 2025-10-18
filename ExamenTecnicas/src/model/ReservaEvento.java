/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import model.enums.TipoEvento;
import java.time.LocalDate;
import java.time.LocalTime;
/**
 *
 * @author alexm
 */
public class ReservaEvento extends Reserva {
    private String nombreEvento;
    private TipoEvento tipoEvento;

    public ReservaEvento(String idAula, String responsable, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, String nombreEvento, TipoEvento tipoEvento) {
        super(idAula, responsable, fecha, horaInicio, horaFin);
        this.nombreEvento = nombreEvento;
        this.tipoEvento = tipoEvento;
    }

    public String getNombreEvento() { return nombreEvento; }
    public TipoEvento getTipoEvento() { return tipoEvento; }
    
    @Override
    public String toString() {
        return super.toString() + ", Tipo=Evento, Nombre Evento='" + nombreEvento + "', Tipo Evento=" + tipoEvento + "]";
    }
}
