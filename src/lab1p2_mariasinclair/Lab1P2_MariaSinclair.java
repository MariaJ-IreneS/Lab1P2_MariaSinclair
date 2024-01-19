package lab1p2_mariasinclair;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Lab1P2_MariaSinclair {

    static Scanner leer = new Scanner(System.in);
    static ArrayList<Correos> lista = new ArrayList<>();

    public static void main(String[] args) throws ParseException {
        int caso = 0;
        boolean repetir = true;
        while (repetir) {
            System.out.println("-------MENU-------");
            System.out.println("1. Registrar: ");
            System.out.println("2. Listar. ");
            System.out.println("3.  ");
            System.out.println("4. Salir");
            System.out.print(" Elige una opcion: ");
            caso = leer.nextInt();

            switch (caso) {
                case 1:
                    Registrar();
                    break;
                case 2:
                    Listar();
                    break;
                case 3:
                    System.out.println("Hola 3");
                    break;
                default:
                    System.out.println("Finalizo.");
                    repetir = false;

            }//Fin de los casos.
        }//Fin del repetidor.
    }//Fin del main.

    public static void Registrar() throws ParseException {
        System.out.println("Ingrese su nombre (Nombre Apellido): ");
        String nombre = leer.next();
        leer.nextLine();

        System.out.println("Ingrese su fecha de nacimiento (dd/MM/yyyy): ");
        String fecha = leer.next();

        if (verificarEdad(fecha)) {

            System.out.println("Ingrese su correo electronico: ");
            String correo = leer.next();

            System.out.println("Ingrese su contraseña: ");
            String contraseña = leer.next();

            Correos persona = new Correos(nombre, fecha, correo, contraseña);
            lista.add(persona);

        } else {
            System.out.println("Lo siento, debes tener al menos 13 años para registrarte😭");
            ;
        }
    }

    public static void FormatoFecha(String fecha) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date fechas = sdf.parse(fecha);
        System.out.println("Fecha formateada: " + sdf.format(fechas));
    }

    public static boolean verificarEdad(String fechaN) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaNacimiento = sdf.parse(fechaN);

        Date fechaActual = new Date();

        //Calcular Milisegundo
        long diferencia = fechaActual.getTime() - fechaNacimiento.getTime();

        //Diferencia en años.
        int edad = (int) (diferencia / (365 * 24 * 60 * 60 * 1000));

        return edad > 13;
    }

    public static void Listar() throws ParseException {
        System.out.println("Correos:");

        for (int i = 0; i < lista.size(); i++) {
            Correos persona = lista.get(i);
            System.out.println("Posición: " + (i + 1));
            System.out.println("Nombre: " + persona.getNcompleto());
            System.out.println("Edad: " + calcularEdad(persona.getNacimiento())); // Corregido aquí
            System.out.println("Correo: " + persona.getCorreo());
            System.out.println("Contraseña: " + persona.getContraseña());
            System.out.println("\n");
        }
    }

    public static String calcularEdad(String fechaN) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaNacimiento = sdf.parse(fechaN);
        Date fechaActual = new Date();

        // Calcular la diferencia en milisegundos, segundos, minutos, horas, días y años.
        long diferencia = fechaActual.getTime() - fechaNacimiento.getTime();
        long segundos = diferencia / 1000;
        long minutos = segundos / 60;
        long horas = minutos / 60;
        long dias = horas / 24;
        long años = dias / 365;

        return años + " años, " + (dias % 365) + " días";
    }
}
