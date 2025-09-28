/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo1.ejercicioCajero;

import java.util.Scanner;

/**
 *
 * @author alexm
 */
public class ejercicioCajero {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        double saldo = 500.00;
        int opcion;

        do {

            System.out.println("====================================");
            System.out.println("            CAJERO AUTOMATICO");
            System.out.println("====================================");
            System.out.println("1. Consultar saldo ");
            System.out.println("2. Depositar dinero ");
            System.out.println("3. Retirar dinero");
            System.out.println("4. Salir");
            System.out.println("====================================");
            System.out.print("Selecione una opcion");
            opcion = entrada.nextInt();

            switch (opcion) {
                
                case 1:
                    System.out.println("Tu saldo actual es : "+saldo);
                    break;
                case 2:
                    System.out.println("Ingrese la cantidad a depositar");
                    double deposito = entrada.nextDouble();

                    if (deposito > 0) {
                        saldo += deposito;
                        System.out.printf("Deposito exitoso. Nuevo actual: %.2f%n" , saldo);
                    } else {
                        System.out.println("Cantidad invalida");
                    }
                    break;
                case 3:
                    System.out.println("Ingrese la cantidad a retirar");
                    double retiro = entrada.nextDouble();

                    if (retiro > 0) {
                        saldo -= retiro;
                        System.out.printf("Retiro exitoso. Nuevo actual: %.2f%n" , saldo);
                    } else {
                        System.out.println("Cantidad invalida o fondos insuficientes");
                    }

                    break;
                case 4:
                    System.out.println("Gracias por usar nuestros servicios");
                    break;
                default:
                    System.out.println("Opcion invalida");

            }
            System.out.println(" ");

        } while (opcion != 4);

        System.out.println("Bienvenido al proyecto 2");
    }

}
