import java.io.*;
import java.util.Properties;

public class CalculoCostos {

    private static final String ARCHIVO_CONFIG = "config_porcentajes.txt";

    private static void verificarOCrearArchivo() {
        File archivo = new File(ARCHIVO_CONFIG);
        if (!archivo.exists()) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
                pw.println("cargo_horario_especial=0.20");
                pw.println("descuento_combo=0.10");
                pw.flush();
                System.out.println("[SISTEMA] Archivo config_porcentajes.txt generado automáticamente.");
            } catch (IOException e) {
            }
        }
    }

    public static double obtenerPorcentajeCargoExtra() {
        verificarOCrearArchivo(); 
        File archivo = new File(ARCHIVO_CONFIG);

        try (InputStream input = new FileInputStream(archivo)) {
            Properties prop = new Properties();
            prop.load(input);
            String valor = prop.getProperty("cargo_horario_especial");
            if (valor != null) {
                return Double.parseDouble(valor);
            }
        } catch (Exception e) {
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
            return 0.15;
        }
        return 0.15;
    }
}