package lab1p2_mariasinclair;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                    ListarPorDominio();
                    break;
                default:
                    System.out.println("Finalizo su programa.");
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
            String correo;
            do {
                System.out.println("Ingrese su correo electrónico: ");
                correo = leer.next();
                if (!verificarCorreo(correo)) {
                    System.out.println("Correo electrónico no válido. Inténtelo de nuevo.");
                } else if (existeCorreoConMismoDominio(correo)) {
                    System.out.println("Ya existe un correo con el mismo dominio. Inténtelo de nuevo.");
                }
            } while (!verificarCorreo(correo) || existeCorreoConMismoDominio(correo));

            String contraseña;
            do {
                System.out.println("Ingrese su contraseña: ");
                contraseña = leer.next();
                if (!contraseña(contraseña)) {
                    System.out.println("Contraseña no válida. Debe tener al menos 8 caracteres, incluir una letra mayúscula, una letra minúscula, un número y un símbolo.");
                }
            } while (!contraseña(contraseña));

            Correos persona = new Correos(nombre, fecha, correo, contraseña);
            lista.add(persona);
        } else {
            System.out.println("Lo siento, debes tener al menos 13 años para registrarte😭");
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

        // Calcular la diferencia en milisegundos.
        long diferencia = fechaActual.getTime() - fechaNacimiento.getTime();
        long segundos = diferencia / 1000;
        long minutos = segundos / 60;
        long horas = minutos / 60;
        long dias = horas / 24;

        // Convertir la diferencia total de días a años, meses y días.
        long años = dias / 365;
        long meses = (dias % 365) / 30;
        long diasRestantes = (dias % 365) % 30;

        return años + " años, " + meses + " meses, " + diasRestantes + " días";
    }

    public static boolean verificarCorreo(String email) {
        String regex = "^[a-zA-Z0-9._%&$+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean existeCorreoConMismoDominio(String correo) {
        // Obtener el dominio del correo ingresado
        String[] partesCorreo = correo.split("@");
        String dominio = partesCorreo[1];

        // Verificar si ya existe un correo igual.
        for (int i = 0; i < lista.size(); i++) {
            Correos persona = lista.get(i);
            String correoExistente = persona.getCorreo();
            String[] partesCorreoExistente = correoExistente.split("@");
            String dominioExistente = partesCorreoExistente[1];
            if (dominioExistente.equalsIgnoreCase(dominio)) {
                return true;
            }
        }
        return false;
    }

    public static boolean contraseña(String contraseña) {
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!\\?<>$%]).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(contraseña);
        return matcher.matches();
    }

    public static void ListarPorDominio() throws ParseException {
        System.out.print("Ingrese el dominio a filtrar: ");
        String dominio = leer.next();

        System.out.println("Correos del dominio @" + dominio + ":");

        for (Correos persona : lista) {
            if (persona.getCorreo().toLowerCase().endsWith("@" + dominio.toLowerCase())) {
                System.out.println("Nombre: " + persona.getNcompleto());
                System.out.println("Edad: " + calcularEdad(persona.getNacimiento()));
                System.out.println("Correo: " + persona.getCorreo());
                System.out.println("Contraseña: " + persona.getContraseña());
                System.out.println();
            }
        }
    }

}
