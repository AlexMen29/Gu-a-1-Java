/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import util.ReservaException;
import model.*;
import util.Validable;
import model.enums.EstadoReserva;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
/**
 *
 * @author alexm
 */
public class GestorService implements Validable {
    private List<Aula> aulas = new ArrayList<>();
    private List<Reserva> reservas = new ArrayList<>();

    // Gestión de Aulas
    public void agregarAula(Aula aula) {
        aulas.add(aula);
    }

    public List<Aula> getAulas() {
        return aulas;
    }
    
    public Aula findAulaById(String id) {
        return aulas.stream()
                .filter(a -> a.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    // Gestión de Reservas
    public void agregarReserva(Reserva reserva) throws ReservaException {
        validar(reserva, this.reservas);
        reservas.add(reserva);
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public Reserva findReservaById(UUID id) {
        return reservas.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void cancelarReserva(UUID id) throws ReservaException {
        Reserva reserva = findReservaById(id);
        if (reserva == null) {
            throw new ReservaException("No se encontró una reserva con el ID proporcionado.");
        }
        reserva.setEstado(EstadoReserva.CANCELADA);
    }
    
    public List<Reserva> findReservasByResponsable(String texto) {
        return reservas.stream()
            .filter(r -> r.getResponsable().toLowerCase().contains(texto.toLowerCase()))
            .collect(Collectors.toList());
    }

    @Override
    public void validar(Reserva nuevaReserva, List<Reserva> reservasExistentes) throws ReservaException {
        // El aula debe existir
        if (findAulaById(nuevaReserva.getIdAula()) == null) {
            throw new ReservaException("El aula con ID " + nuevaReserva.getIdAula() + " no existe.");
        }

        // La hora de fin debe ser posterior a la de inicio
        if (nuevaReserva.getHoraFin().isBefore(nuevaReserva.getHoraInicio()) || nuevaReserva.getHoraFin().equals(nuevaReserva.getHoraInicio())) {
            throw new ReservaException("La hora de fin debe ser posterior a la hora de inicio.");
        }

        //  No debe haber conflictos de horario
        for (Reserva existente : reservasExistentes) {
            if (existente.getEstado() == EstadoReserva.ACTIVA &&
                existente.getIdAula().equalsIgnoreCase(nuevaReserva.getIdAula()) &&
                existente.getFecha().equals(nuevaReserva.getFecha())) {

                // Comprobar si hay solapamiento de tiempo
                boolean hayConflicto = nuevaReserva.getHoraInicio().isBefore(existente.getHoraFin()) &&
                                       nuevaReserva.getHoraFin().isAfter(existente.getHoraInicio());

                if (hayConflicto) {
                    throw new ReservaException("Conflicto de horario. El aula ya está reservada en ese rango de tiempo.");
                }
            }
        }
    }
    
    //  Reportes
    public String generarReporteTop3Aulas() {
        StringBuilder reporte = new StringBuilder("--- Top 3 Aulas con Más Horas Reservadas ---\n");
        
        Map<String, Double> horasPorAula = reservas.stream()
            .filter(r -> r.getEstado() == EstadoReserva.ACTIVA)
            .collect(Collectors.groupingBy(
                Reserva::getIdAula,
                Collectors.summingDouble(r -> Duration.between(r.getHoraInicio(), r.getHoraFin()).toMinutes() / 60.0)
            ));

        horasPorAula.entrySet().stream()
            .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
            .limit(3)
            .forEach(entry -> {
                Aula aula = findAulaById(entry.getKey());
                String nombreAula = (aula != null) ? aula.getNombre() : "ID: " + entry.getKey();
                reporte.append(String.format("1. %s - %.2f horas\n", nombreAula, entry.getValue()));
            });
            
        return reporte.toString();
    }
    
    // Setters para cargar datos desde archivos
    public void setAulas(List<Aula> aulas) {
        this.aulas = aulas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
}