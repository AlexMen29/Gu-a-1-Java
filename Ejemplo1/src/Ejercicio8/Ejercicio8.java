/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio8;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author alexm
 */
public class Ejercicio8 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        ArrayList<String> nombres = new ArrayList<>();
        ArrayList<Double> precios = new ArrayList<>();
        ArrayList<Integer> cantidades = new ArrayList<>();

        while (true) {
            System.out.println("\n=== SISTEMA DE INVENTARIO ===");
            System.out.println("1. Registrar producto");
            System.out.println("2. Mostrar productos");
            System.out.println("3. Calcular valor total del inventario");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = entrada.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = entrada.next();

                    System.out.print("Precio: $");
                    double precio = entrada.nextDouble();

                    System.out.print("Cantidad: ");
                    int cantidad = entrada.nextInt();

                    nombres.add(nombre);
                    precios.add(precio);
                    cantidades.add(cantidad);

                    System.out.println("¡Producto registrado exitosamente!");
                    break;

                case 2:
                    if (nombres.isEmpty()) {
                        System.out.println("No hay productos registrados.");
                    } else {
                        System.out.println("\n=== PRODUCTOS REGISTRADOS ===");
                        System.out.printf("%-20s %-10s %-10s%n", "Nombre", "Precio", "Cantidad");
                        System.out.println("----------------------------------------");

                        for (int i = 0; i < nombres.size(); i++) {
                            System.out.printf("%-20s $%-9.2f %-10d%n",
                                    nombres.get(i), precios.get(i), cantidades.get(i));
                        }
                    }
                    break;

                case 3:
                    if (nombres.isEmpty()) {
                        System.out.println("No hay productos registrados.");
                    } else {
                        double valorTotal = 0;
                        for (int i = 0; i < nombres.size(); i++) {
                            valorTotal += precios.get(i) * cantidades.get(i);
                        }
                        System.out.printf("Valor total del inventario: $%.2f%n", valorTotal);
                    }
                    break;

                case 4:
                    System.out.println("Ejecucion exitosa");
                    entrada.close();
                    return;

                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }

    }

}
