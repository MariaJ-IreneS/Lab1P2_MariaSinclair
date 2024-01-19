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
        Registrar();
    }

    public static void Registrar() throws ParseException {
        System.out.println("Ingrese su nombre (Nombre Apellido): ");
        String nombre = leer.next();
        leer.nextLine();

        System.out.println("Ingrese su fecha de nacimiento (dd/MM/yyyy): ");
        String fecha = leer.next();

        System.out.println("Ingrese su correo electronico: ");
        String correo = leer.next();

        System.out.println("Ingrese su contraseña: ");
        String contraseña = leer.next();

        Correos persona = new Correos(nombre, fecha, correo, contraseña);
        
        if (verificarEdad(fecha)) {
            System.out.println("Registro exitoso. ¡Bienvenido!");
        } else {
            System.out.println("Lo siento, debes tener al menos 13 años para registrarte.");
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

        long diferenciaEnMillis = fechaActual.getTime() - fechaNacimiento.getTime();

        int edad = (int) (diferenciaEnMillis / (365.25 * 24 * 60 * 60 * 1000));

        return edad > 13;
    }
}
