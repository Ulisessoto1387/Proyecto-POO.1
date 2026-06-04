import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        VistaInicial vistaInicial = new VistaInicial();
        NuevoRegistro nuevoRegistro = new NuevoRegistro();
        IngresoAlSistema ingreso = new IngresoAlSistema();
        boolean appActiva = true;

        while (appActiva) {
            char opcion = Character.toUpperCase(vistaInicial.mostrarMenuInicial());

            switch (opcion) {
                case 'A' -> {
                    nuevoRegistro.pedirDatos();
                }
                case 'B' -> {
                    String resultadoSesion = ingreso.iniciarSesion();

                    if (resultadoSesion == null) {
                        System.out.println("\n[!] Credenciales incorrectas. Intente de nuevo.");
                    } else if (resultadoSesion.equals("ADMIN")) {
                        System.out.println("\n[SISTEMA] Sesión de Administrador finalizada.");
                    } else {
                        ejecutarFlujoCliente(resultadoSesion);
                    }
                }
                case 'C' -> {
                    System.out.println("\nSaliendo por completo de la aplicación. ¡Hasta luego!");
                    appActiva = false;
                }
                default -> System.out.println("\nOpción inválida en la pantalla inicial.");
            }
        }
    }

    private static void ejecutarFlujoCliente(String nicknameUsuario) {
        VistaMenuUsuario vistaMenu = new VistaMenuUsuario();
        boolean sesionActiva = true;

        while (sesionActiva) {
            char opcion = Character.toUpperCase(vistaMenu.mostrarMenuUsuario());

            switch (opcion) {
                case 'A' -> {
                    RegistroMascota rm = new RegistroMascota();
                    rm.registrarMascota(nicknameUsuario);
                }
                case 'B' -> {
                    ServicioEspecialMascota.reservarServicio(nicknameUsuario);
                }
                case 'C' -> {
                    VerHistorial.mostrarHistorial(nicknameUsuario);
                }
                case 'D' -> {
                    VerPreciosDeServicio.mostrarPreciosDelUsuario(nicknameUsuario, 0);
                }
                case 'E' -> {
                    CerrarSesion.SalirDeSesion();
                    sesionActiva = false;
                }
                default -> System.out.println("\nOpción inválida en el menú de usuario.");
            }
        }
    }
}