import java.util.Scanner;

public class VistaMetodoPago {

  public int mostrarMenuDePago() {
    int opcion;
    Scanner sc = new Scanner(System.in);

    System.out.println("Selecciona el mètodo de pago");
    System.out.println();
    System.out.println("1) Pago en efectivo");
    System.out.println("2) Escaneo de còdigo");
    System.out.println("3) Transferencia interbancaria");
    System.out.println("4) Pago con tarjeta");

    opcion = sc.nextInt();

    return opcion;
  }
}