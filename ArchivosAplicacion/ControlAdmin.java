import java.util.Scanner;

public class ControlAdmin {

    public static void ejecutarMenu() {
        VistaMenuAdmin vista = new VistaMenuAdmin();
        boolean salir = false;

        while (!salir) {
            char opcion = Character.toUpperCase(vista.mostrarMenuAdmin());

            switch (opcion) {
                case 'A' -> RegistroTrabajador.pedirDatosTrabajador();
                case 'B' -> AsignarServicio.asignar();
                case 'C' -> CobrarServicio.procesarCobro();
                case 'D' -> {
                    CerrarSesion.SalirDeSesion();
                    salir = true;
                }
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
}