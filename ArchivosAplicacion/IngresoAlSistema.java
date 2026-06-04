import java.io.*;
import java.util.Scanner;

public class IngresoAlSistema {

    public String iniciarSesion() {
        Scanner entrada = new Scanner(System.in);
        String nickname, contraseña;
        boolean accesoConcedido = false;
        String nombreUsuario = "";

        System.out.println("\n*****************************************");
        System.out.println("--- INGRESO AL SISTEMA ---");
        System.out.print("Nickname: ");
        nickname = entrada.nextLine().trim();
        System.out.print("Contraseña: ");
        contraseña = entrada.nextLine().trim();

        // Cuenta de Administrador por defecto reglamentaria
        if (nickname.equals("admin") && contraseña.equals("1234")) {
            System.out.println("\nAcceso Administrador concedido.");
            ControlAdmin.ejecutarMenu(); 
            return "ADMIN";
        }

        // Validación leyendo desde archivo de TEXTO plano
        File archivoTxt = new File("usuarios.txt");
        if (archivoTxt.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(archivoTxt))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split("\\|");
                    if (datos.length > 5) {
                        String fileNickname = datos[4].trim();
                        String filePassword = datos[5].trim();

                        if (fileNickname.equals(nickname) && filePassword.equals(contraseña)) {
                            accesoConcedido = true;
                            nombreUsuario = datos[0]; // Guarda el nombre de pila
                            break;
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("Error al leer el archivo de autenticación de texto.");
            }
        }

        if (accesoConcedido) {
            System.out.println("\nAcceso permitido. ¡Bienvenido, " + nombreUsuario + "!");
            return nickname; // Retorna el nickname del cliente activo
        } else {
            System.out.println("\n[!] Los datos ingresados no son correctos.");
            return null;
        }
    }
}