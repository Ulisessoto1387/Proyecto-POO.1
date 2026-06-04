import java.io.*;
import java.util.ArrayList;

public class HistorialServiciosAdquiridos {
    @SuppressWarnings("unchecked")
    public static void mostrarHistorial(String nicknameUsuario) {
        System.out.println("\n--- HISTORIAL DE SERVICIOS DE: " + nicknameUsuario + " ---");
        File archivo = new File("HistorialServiciosAdquiridos.dat");
        if (!archivo.exists() || archivo.length() == 0) {
            System.out.println("No cuentas con servicios registrados en tu historial.");
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            ArrayList<Servicio> lista = (ArrayList<Servicio>) ois.readObject();
            boolean tieneServicios = false;
            for (Servicio s : lista) {
                if (s.getNicknameUsuario().equalsIgnoreCase(nicknameUsuario)) {
                    System.out.println("- Mascota: " + s.getTipoMascota() + " | Total pagado: $" + s.getPrecioFinal());
                    tieneServicios = true;
                }
            }
            if (!tieneServicios) System.out.println("No se encontraron servicios.");
        } catch (Exception e) {
            System.out.println("Error al leer el historial.");
        }
    }
}

