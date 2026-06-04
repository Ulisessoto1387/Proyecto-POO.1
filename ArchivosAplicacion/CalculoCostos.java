import java.io.*;
import java.util.Properties;

public class CalculoCostos {

    private static final String ARCHIVO_CONFIG = "config_porcentajes.txt";

    // Este método revisa si existe el archivo. Si no existe, lo crea solito con valores base.
    private static void verificarOCrearArchivo() {
        File archivo = new File(ARCHIVO_CONFIG);
        if (!archivo.exists()) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
                pw.println("cargo_horario_especial=0.20");
                pw.println("descuento_combo=0.10");
                pw.flush();
                System.out.println("[SISTEMA] Archivo config_porcentajes.txt generado automáticamente.");
            } catch (IOException e) {
                // Si no se puede crear, no pasa nada, el try-catch de abajo nos salvará
            }
        }
    }

    public static double obtenerPorcentajeCargoExtra() {
        verificarOCrearArchivo(); // Intentamos crearlo si no existe
        File archivo = new File(ARCHIVO_CONFIG);

        // Ponemos TODO dentro de un try-catch general para que NUNCA vuelva a lanzar FileNotFoundException
        try (InputStream input = new FileInputStream(archivo)) {
            Properties prop = new Properties();
            prop.load(input);
            String valor = prop.getProperty("cargo_horario_especial");
            if (valor != null) {
                return Double.parseDouble(valor);
            }
        } catch (Exception e) {
            // Si el archivo no se leyó o no existía, regresamos el 20% por defecto y el programa NO se rompe
            return 0.20; 
        }
        return 0.20;
    }

    public static double obtenerPorcentajeDescuento() {
        verificarOCrearArchivo();
        File archivo = new File(ARCHIVO_CONFIG);

        try (InputStream input = new FileInputStream(archivo)) {
            Properties prop = new Properties();
            prop.load(input);
            String valor = prop.getProperty("descuento_combo");
            if (valor != null) {
                return Double.parseDouble(valor);
            }
        } catch (Exception e) {
            // Si falla, regresamos el 15% o 10% por defecto
            return 0.15;
        }
        return 0.15;
    }
}