import java.util.Scanner;

public class VistaMenuAdmin {
    public char mostrarMenuAdmin() {
        char opcion;
        Scanner sc = new Scanner(System.in);

        System.out.println("\n--- MENÚ ADMINISTRADOR ---");
        System.out.println("A) Registrar un Trabajador");
        System.out.println("B) Asignar un servicio a trabajador");
        System.out.println("C) Cobrar orden de servicio");
        System.out.println("D) Salir de sesión");
        System.out.print("Elija una opción: ");

        opcion = sc.next().toUpperCase().charAt(0);
        return opcion;
    }
}