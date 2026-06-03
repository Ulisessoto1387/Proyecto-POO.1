import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        IngresoAlSistema ingreso = new IngresoAlSistema();
        boolean appActiva = true;

        while (appActiva) {
            System.out.println("\n=========================================");
            System.out.println("    SISTEMA DE CONTROL VETERINARIO       ");
            System.out.println("=========================================");
            System.out.println("1) Iniciar Sesión (Cliente / Admin)");
            System.out.println("2) Registrarse como Nuevo Cliente");
            System.out.println("3) Cerrar Aplicación por Completo");
            System.out.print("Seleccione una opción: ");

            String opcionPrincipal = entrada.nextLine();

            switch (opcionPrincipal) {
                case "1" -> {
                    String resultadoSesion = ingreso.iniciarSesion();

                    if (resultadoSesion == null) {
                        System.out.println("\n[!] Falló el inicio de sesión. Credenciales incorrectas.");
                    } else if (resultadoSesion.equals("ADMIN")) {
                        System.out.println("\n[SISTEMA] Sesión de Administrador finalizada de forma segura.");
                    } else {
                        ejecutarFlujoCliente(resultadoSesion);
                    }
                }
                case "2" -> {
                    registrarClienteEnSistema(entrada);
                }
                case "3" -> {
                    System.out.println("\nApagando sistema de manera ordenada... ¡Hasta luego!");
                    appActiva = false;
                }
                default -> System.out.println("\nOpción no válida. Intente de nuevo.");
            }
        }
        entrada.close();
    }

    /**
     * Permite crear cuentas de cliente recolectando los datos requeridos
     * por el constructor de 10 parámetros de Usuario.java
     */
    private static void registrarClienteEnSistema(Scanner entrada) {
        System.out.println("\n--- REGISTRO DE NUEVO CLIENTE ---");
        System.out.print("Nombre: ");
        String nombre = entrada.nextLine();
        System.out.print("Apellido Paterno: ");
        String appPaterno = entrada.nextLine();
        System.out.print("Apellido Materno: ");
        String appMaterno = entrada.nextLine();
        System.out.print("Edad: ");
        int edad = entrada.nextInt();
        entrada.nextLine(); // Limpiar el buffer del scanner

        System.out.print("Dirección de residencia: ");
        String direccion = entrada.nextLine();
        System.out.print("Número de teléfono: ");
        String telefono = entrada.nextLine();
        System.out.print("Correo electrónico: ");
        String correo = entrada.nextLine();

        System.out.print("Defina su Nickname (Usuario): ");
        String nickname = entrada.nextLine();
        System.out.print("Defina su Contraseña: ");
        String contraseña = entrada.nextLine();

        // Si tu constructor requiere un décimo parámetro (un 7º String), dejamos este comodín.
        String datoExtra = "No especificado";

        File archivoUsuarios = new File("registrando.dat");
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();

        if (archivoUsuarios.exists() && archivoUsuarios.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoUsuarios))) {
                listaUsuarios = (ArrayList<Usuario>) ois.readObject();
            } catch (Exception e) {
                System.out.println("Error al leer la base de datos de usuarios.");
            }
        }

        for (Usuario u : listaUsuarios) {
            if (u.getNickname().equalsIgnoreCase(nickname)) {
                System.out.println("[!] El nickname '" + nickname + "' ya está registrado. Intente con otro.");
                return;
            }
        }

        // =====================================================================================
        // IMPORTANTE: Aquí se envían las variables al constructor de tu clase Usuario.
        // Si notas que al iniciar sesión confunde datos o el orden difiere de tu archivo Usuario.java,
        // simplemente reacomoda la posición de estas variables para que coincidan con tu declaración.
        // =====================================================================================
        Usuario nuevoUsuario = new Usuario(
            nombre,       // 1. String
            appPaterno,   // 2. String
            appMaterno,   // 3. String
            edad,         // 4. int
            nickname,     // 5. String
            contraseña,   // 6. String
            direccion,    // 7. String
            telefono,     // 8. String
            correo,       // 9. String
            datoExtra     // 10. String
        );

        listaUsuarios.add(nuevoUsuario);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoUsuarios))) {
            oos.writeObject(listaUsuarios);
            System.out.println("\n¡Cuenta creada con éxito! Ya puedes iniciar sesión como cliente.");
        } catch (Exception e) {
            System.out.println("Error crítico al guardar el nuevo cliente: " + e.getMessage());
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
                    HistorialServiciosAdquiridos.mostrarHistorial(nicknameUsuario);
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