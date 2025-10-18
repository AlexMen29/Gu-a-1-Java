/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import model.*;
import model.enums.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author alexm
 */
public class GestorArchivos {
    private static final String AULAS_FILE = "aulas.csv";
    private static final String RESERVAS_FILE = "reservas.csv";

    // Guardado 
    public static void guardarDatos(List<Aula> aulas, List<Reserva> reservas) {
        guardarAulas(aulas);
        guardarReservas(reservas);
        System.out.println("Datos guardados exitosamente.");
    }
    
    private static void guardarAulas(List<Aula> aulas) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(AULAS_FILE))) {
            for (Aula aula : aulas) {
                writer.println(String.join(",", aula.getId(), aula.getNombre(), String.valueOf(aula.getCapacidad()), aula.getTipoAula().name()));
            }
        } catch (IOException e) {
            System.err.println("Error al guardar las aulas: " + e.getMessage());
        }
    }
    
    private static void guardarReservas(List<Reserva> reservas) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(RESERVAS_FILE))) {
            for (Reserva r : reservas) {
                String commonData = String.join(",",
                    r.getId().toString(), r.getIdAula(), r.getResponsable(), r.getFecha().toString(),
                    r.getHoraInicio().toString(), r.getHoraFin().toString(), r.getEstado().name());
                
                if (r instanceof ReservaClase) {
                    ReservaClase rc = (ReservaClase) r;
                    writer.println("CLASE," + commonData + "," + rc.getCarrera());
                } else if (r instanceof ReservaPractica) {
                    ReservaPractica rp = (ReservaPractica) r;
                    writer.println("PRACTICA," + commonData + "," + rp.getEquipoRequerido());
                } else if (r instanceof ReservaEvento) {
                    ReservaEvento re = (ReservaEvento) r;
                    writer.println("EVENTO," + commonData + "," + re.getNombreEvento() + "," + re.getTipoEvento().name());
                }
            }
        } catch (IOException e) {
            System.err.println("Error al guardar las reservas: " + e.getMessage());
        }
    }

    // --- Carga ---
    public static List<Aula> cargarAulas() {
        List<Aula> aulas = new ArrayList<>();
        File file = new File(AULAS_FILE);
        if (!file.exists()) return aulas;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                aulas.add(new Aula(parts[0], parts[1], Integer.parseInt(parts[2]), TipoAula.valueOf(parts[3])));
            }
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Error al cargar las aulas: " + e.getMessage());
        }
        return aulas;
    }
 
    
    public static void exportarReporte(String reporte, String nombreArchivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            writer.println("--- REPORTE GENERADO EL " + LocalDate.now() + " ---");
            writer.println(reporte);
            System.out.println("Reporte exportado exitosamente a " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al exportar el reporte: " + e.getMessage());
        }
    }
    
}
