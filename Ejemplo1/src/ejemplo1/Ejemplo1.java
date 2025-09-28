/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo1;

/**
 *
 * @author alexm
 */
import java.util.Scanner;

public class Ejemplo1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        String nombre;
        
        System.out.println("Ingrese el nombre del estudiante");
        nombre = entrada.nextLine();
        
        double suma = 0;
        //bucle que pide 3 notas
        
        int contadora = 0;
        
        for (int i= 1; i<=3; i++){
            
            System.out.print("Ingrese la nota " + i + ": ");
            suma += entrada.nextDouble();
            
            contadora++;
      
        }
        
        double promedio = suma / contadora;
        
        System.out.printf("Pomedio de nota: %.2f%n ", promedio);
        
        if (promedio >= 7){
            System.out.println(nombre+" Esta aprobado " );
        }
        else
        {
            System.out.println(nombre+" Esta reprobado " );
        }
    }

}
