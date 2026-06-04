import java.io.*;
import java.util.Scanner;

public class VerHistorial {

    public static void mostrarHistorial(String nicknameUsuario) {
        Scanner entrada = new Scanner(System.in);
        String usuarioBuscado = nicknameUsuario.trim().toLowerCase();

        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("==================================================");
        System.out.println("          HISTORIAL DE SERVICIOS ADQUIRIDOS       ");
        System.out.println("==================================================");

        File archivoCitas = new File("citas.txt");

        if (!archivoCitas.exists()) {
            System.out.println("[i] No se encontraron órdenes registradas en el sistema.");
            System.out.println("\nPresione Enter para regresar...");
            entrada.nextLine();
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivoCitas))) {
            String linea;
            boolean encontrado = false;
            int contador = 1;

            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] datos = linea.split("\\|");

                if (datos.length >= 2 && datos[0].trim().toLowerCase().equals(usuarioBuscado)) {
                    encontrado = true;
                    System.out.println("--- ORDEN #" + contador + " ---");
                    
                    if (datos.length < 7) {
                        System.out.println("  • Mascota: " + datos[1]);
                        System.out.println("  • Estado actual: " + datos[datos.length - 1]);
                    } else {
                        String fechaRaw = datos[1].matches("\\d{12}") ? datos[1] : (datos[2].matches("\\d{12}") ? datos[2] : datos[1]);
                        String mascota = datos[1].matches("\\d{12}") ? datos[2] : datos[1];

                        System.out.println("  • Mascota: " + mascota);
                        System.out.println("  • Fecha: " + formatearFecha(fechaRaw));
                        System.out.println("  • Servicios: [" + datos[3] + "]");
                        System.out.println("  • Cargo Extra (15%): $" + datos[4]);
                        System.out.println("  • Descuento (10%): -$" + datos[5]);
                        System.out.println("  • TOTAL COBRADO: $" + datos[7]);
                    }
                    System.out.println("--------------------------------------------------");
                    contador++;
                }
            }

            if (!encontrado) {
                System.out.println("[i] No se encontraron registros de servicios para: " + nicknameUsuario);
            }

        } catch (Exception e) {
            System.out.println("[!] Error de lectura: " + e.getMessage());
        }

        System.out.println("\nPresione Enter para continuar...");
        entrada.nextLine();
    }

    private static String formatearFecha(String f) {
        if (f == null || f.trim().length() < 12) return f;
        try {
            String l = f.trim();
            return l.substring(6,8) + "/" + l.substring(4,6) + "/" + l.substring(0,4) + " a las " + l.substring(8,10) + ":" + l.substring(10,12) + " hrs";
        } catch (Exception e) {
            return f;
        }
    }
}