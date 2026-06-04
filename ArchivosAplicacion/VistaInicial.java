import java.util.Scanner;

public class VistaInicial {
    public char mostrarMenuInicial() {
        char opcion;
        Scanner sc = new Scanner(System.in);

        System.out.println("\n*** ESTÉTICA CANINA Y FELINA ***");
        System.out.println("A) Nuevo registro");
        System.out.println("B) Ingreso al sistema");
        System.out.println("C) Salir del programa");
        System.out.print("Elija una opción: ");

        opcion = sc.next().toUpperCase().charAt(0);
        return opcion;
    }
}