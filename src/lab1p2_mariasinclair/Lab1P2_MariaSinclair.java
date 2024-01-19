package lab1p2_mariasinclair;

import java.util.Scanner;

public class Lab1P2_MariaSinclair {

    public static void main(String[] args) {
       Scanner leer = new Scanner(System.in);
       
       String nombre;
       
        System.out.println("Ingrese su nombre completo: ");
        nombre=leer.nextLine();
        
        System.out.println("Su nombre es: "+nombre);
    }
    
}
