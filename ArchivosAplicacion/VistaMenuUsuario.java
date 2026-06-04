import java.util.Scanner;

public class VistaMenuUsuario {
  public char mostrarMenuUsuario() {
    char opcion;
    Scanner sc = new Scanner(System.in);

    System.out.println("A) Registrar una Mascota");
    System.out.println("B) Reservar servicios (combos disponibles)");
    System.out.println("C) Ver historial de servicios adquiridos");
    System.out.println("D) Ver precio de servicios");
    System.out.println("E) Salir de sesiòn");

    opcion = sc.next().charAt(0);

    return opcion;
  }
}