/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio7;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author alexm
 */
public class ejercicio7 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        Random aleatorio = new Random();
        int numeroX = aleatorio.nextInt(100) + 1;  

        int numIntentos = 1;

        while (true) {
            System.out.print("Adivina adivinador\nIngrese un número entre 1 a 100:  ");
            int n = entrada.nextInt();

            if (n == numeroX) {
                System.out.println("Felicidades, encontraste el numero en " + numIntentos + " intentos");
                break;
            } else if (n > numeroX) {
                System.out.println("Demasiado Alto");
            } else {
                System.out.println("Demasiado bajo");
            }

            numIntentos += 1;

        }

        System.err.println("Numero generado " + numeroX);
    }

}
