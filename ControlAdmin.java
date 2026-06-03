public class ControlAdmin {

    public static void ejecutarMenu() {
        VistaMenuAdmin vista = new VistaMenuAdmin();
        char opcion;
        boolean salir = false;

        while (!salir) {
            opcion = Character.toUpperCase(vista.mostrarMenuAdmin());

            switch (opcion) {
                case 'A' -> {
                    RegistroTrabajador rt = new RegistroTrabajador();
                    rt.pedirDatosTrabajador();
                }
                case 'B' -> {
                    AsignarServicio.asignar();
                }
                case 'C' -> {
                    CobrarServicio.procesarCobro();
                }
                case 'D' -> {
                    CerrarSesion.SalirDeSesion();
                    salir = true;
                }
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
}