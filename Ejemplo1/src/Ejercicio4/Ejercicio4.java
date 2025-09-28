/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio4;

import java.util.Scanner;

/**
 *
 * @author alexm
 */
public class Ejercicio4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.print("A continuación ingrese un número n");
        int numero = entrada.nextInt();
        
        

        int a = 0;
        int b = 1;
        int c;
        for (int i = 1; i <= numero; i++) {
            
            c = a + b;
            
            a = b;
            b = c;
     
            System.out.println("Secuencia: " + c);
            
            
            
            
        }
    }

}
