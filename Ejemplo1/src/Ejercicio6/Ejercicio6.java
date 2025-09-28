/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio6;

import java.util.Scanner;

/**
 *
 * @author alexm
 */
public class Ejercicio6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Ingrese un número n para el ingreso de notas ");
        int n = entrada.nextInt();

        int notas[] = new int[n];
        int promedio = 0;

        for (int i = 0; i < n; i++) {

            System.out.print("Nota de estudiante " + (i + 1) + " : ");
            notas[i] = entrada.nextInt();

            promedio += notas[i];

        }

        int mayor = notas[0];
        int menor = notas[0];

        for (int i = 0; i < n; i++) {

            if (menor > notas[i]) {
                menor = notas[i];
            }

            if (mayor < notas[i]) {

                mayor = notas[i];
            }

        }

        promedio = promedio / n;

        System.out.println("Promedio : " + promedio);
        System.out.println("Nota mayor : " + mayor);
        System.out.println("Nota menor : " + menor);

    }

}
