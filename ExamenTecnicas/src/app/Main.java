/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import util.ReservaException;
import model.*;
import model.enums.*;
import service.GestorService;
import util.GestorArchivos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.UUID;

/**
 *
 * @author alexm
 */
public class Main {

    private static final GestorService gestor = new GestorService();
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Cargar datos al iniciar
        gestor.setAulas(GestorArchivos.cargarAulas());


        System.out.println("¡Bienvenido al Gestor de Reservas de Aulas ITCA!");

        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = leerOpcion();
            switch (opcion) {
                case 1:
                    gestionarAulas();
                    break;
                case 2:
                    gestionarReservas();
                    break;
                case 3:
                    gestionarReportes();
                    break;
                case 4:
                    GestorArchivos.guardarDatos(gestor.getAulas(), gestor.getReservas());
                    System.out.println("¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 4);

        scanner.close();
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Gestionar Aulas");
        System.out.println("2. Gestionar Reservas");
        System.out.println("3. Ver Reportes");
        System.out.println("4. Guardar y Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void gestionarAulas() {
        System.out.println("\n--- GESTIÓN DE AULAS ---");
        System.out.println("1. Registrar nueva aula");
        System.out.println("2. Listar todas las aulas");
        System.out.print("Seleccione una opción: ");
        int opcion = leerOpcion();

        if (opcion == 1) {
            registrarAula();
        } else if (opcion == 2) {
            listarAulas();
        } else {
            System.out.println("Opción no válida.");
        }
    }

    private static void registrarAula() {
        try {
            System.out.print("ID del aula (ej. A101): ");
            String id = scanner.nextLine();
            System.out.print("Nombre del aula (ej. Salón de Cómputo 1): ");
            String nombre = scanner.nextLine();
            System.out.print("Capacidad: ");
            int capacidad = Integer.parseInt(scanner.nextLine());
            System.out.print("Tipo de Aula (TEORICA, LABORATORIO, AUDITORIO): ");
            TipoAula tipo = TipoAula.valueOf(scanner.nextLine().toUpperCase());

            gestor.agregarAula(new Aula(id, nombre, capacidad, tipo));
            System.out.println("✅ Aula registrada con éxito.");
        } catch (Exception e) {
            System.err.println("❌ Error en el registro. Verifique los datos: " + e.getMessage());
        }
    }

    private static void listarAulas() {
        System.out.println("\n--- LISTA DE AULAS ---");
        if (gestor.getAulas().isEmpty()) {
            System.out.println("No hay aulas registradas.");
        } else {
            gestor.getAulas().forEach(System.out::println);
        }
    }

    private static void gestionarReservas() {
        System.out.println("\n--- GESTIÓN DE RESERVAS ---");
        System.out.println("1. Crear nueva reserva");
        System.out.println("2. Cancelar reserva");
        System.out.println("3. Listar todas las reservas");
        System.out.print("Seleccione una opción: ");
        int opcion = leerOpcion();

        switch (opcion) {
            case 1:
                crearReserva();
                break;
            case 2:
                cancelarReserva();
                break;
            case 3:
                System.out.println("\n--- LISTADO DE RESERVAS ---");
                gestor.getReservas().forEach(System.out::println);
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private static void crearReserva() {
        try {
            System.out.print("ID del Aula a reservar: ");
            String idAula = scanner.nextLine();
            System.out.print("Nombre del responsable: ");
            String responsable = scanner.nextLine();
            System.out.print("Fecha (YYYY-MM-DD): ");
            LocalDate fecha = LocalDate.parse(scanner.nextLine());
            System.out.print("Hora de inicio (HH:MM): ");
            LocalTime inicio = LocalTime.parse(scanner.nextLine());
            System.out.print("Hora de fin (HH:MM): ");
            LocalTime fin = LocalTime.parse(scanner.nextLine());

            System.out.print("Tipo de reserva (1:Clase, 2:Práctica, 3:Evento): ");
            int tipo = leerOpcion();

            Reserva nuevaReserva = null;

            // Usamos un switch para que el código sea más limpio y cubra todas las opciones
            switch (tipo) {
                case 1:
                    System.out.print("Carrera: ");
                    String carrera = scanner.nextLine();
                    nuevaReserva = new ReservaClase(idAula, responsable, fecha, inicio, fin, carrera);
                    break;
                case 2:
                    System.out.print("Equipo requerido: ");
                    String equipo = scanner.nextLine();
                    nuevaReserva = new ReservaPractica(idAula, responsable, fecha, inicio, fin, equipo);
                    break;
                case 3:
                    System.out.print("Nombre del Evento: ");
                    String nombreEvento = scanner.nextLine();
                    System.out.print("Tipo de Evento (CONFERENCIA, TALLER, REUNION): ");
                    TipoEvento tipoEvento = TipoEvento.valueOf(scanner.nextLine().toUpperCase());
                    nuevaReserva = new ReservaEvento(idAula, responsable, fecha, inicio, fin, nombreEvento, tipoEvento);
                    break;
                default:
                    System.out.println("❌ Tipo de reserva no válido.");
                    return; // Salimos si la opción no es 1, 2 o 3
            }

            // Si se creó una reserva (nuevaReserva no es null), la agregamos
            if (nuevaReserva != null) {
                gestor.agregarReserva(nuevaReserva);
                System.out.println("✅ Reserva creada con éxito. ID: " + nuevaReserva.getId());
            }

        } catch (DateTimeParseException e) {
            System.err.println("❌ Formato de fecha u hora incorrecto. Use YYYY-MM-DD y HH:MM.");
        } catch (IllegalArgumentException e) {
            System.err.println("❌ El tipo de evento no es válido. Use uno de la lista.");
        } catch (ReservaException e) {
            System.err.println("❌ Error al crear la reserva: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    private static void cancelarReserva() {
        try {
            System.out.print("Ingrese el ID de la reserva a cancelar: ");
            UUID id = UUID.fromString(scanner.nextLine());
            gestor.cancelarReserva(id);
            System.out.println("✅ Reserva cancelada con éxito.");
        } catch (IllegalArgumentException e) {
            System.err.println("❌ ID no válido.");
        } catch (ReservaException e) {
            System.err.println("❌ Error al cancelar: " + e.getMessage());
        }
    }

    private static void gestionarReportes() {
        String reporte = gestor.generarReporteTop3Aulas();
        System.out.println(reporte);

        System.out.print("¿Desea exportar este reporte a un archivo? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            GestorArchivos.exportarReporte(reporte, "reporte_aulas.txt");
        }
    }

    private static int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Devuelve un valor inválido
        }
    }

}
