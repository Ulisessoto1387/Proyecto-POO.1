import java.io.*;
import java.util.Scanner; 

public class VerPreciosDeServicio {
    public static void mostrarPreciosDelUsuario(String nicknameUsuario, int serviciosAdquiridos) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("**********PRECIOS DE SERVICIOS (REPORTE TXT)**********");

        File unArchivo = new File("preciosPorPersona.txt");
        boolean registro = false;

        if (unArchivo.exists() && unArchivo.length() > 0) {
            try {
                FileReader flujoEntradaCaracter = new FileReader(unArchivo);
                BufferedReader bufferEntrada = new BufferedReader(flujoEntradaCaracter);
                String lineaLeida = bufferEntrada.readLine();
                int contador = 1;

                while (lineaLeida != null) {
                    if (lineaLeida.startsWith(nicknameUsuario)) {
                        String detalleDeServicio = lineaLeida.replace(nicknameUsuario + " - ", "");
                        System.out.println(contador + ". " + detalleDeServicio); // Corregido error de variable
                        contador++;
                        registro = true;
                    }
                    lineaLeida = bufferEntrada.readLine();
                }
                bufferEntrada.close();
            } catch (IOException e) {
                System.err.println("Error leyendo reporte físico de texto: " + e.toString());
            }
        }

        if (!registro) {
            System.out.println("No se encontraron tickets impresos para tu usuario.");
        }

        if (serviciosAdquiridos >= 3) {
            System.out.println("\n¡Felicidades! Posees el estatus de Cliente Frecuente (Descuento automático del 15% activo).");
        }
        System.out.println("\nPresiona Enter para regresar al Menú principal de Usuario...");
        entrada.nextLine(); 
    }
}