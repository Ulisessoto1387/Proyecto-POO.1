import java.io.*;
import java.util.ArrayList;

public class HistorialServiciosAdquiridos {
    public static void mostrarHistorial(String nicknameUsuario) {
        System.out.println("********Historial de Servicios Adquiridos********");

        File unArchivo = new File("HistorialServiciosAdquiridos.dat");
        ArrayList<Servicio> todosLosServicios = new ArrayList<>();
        ArrayList<Servicio> serviciosDelUsuario = new ArrayList<>();

        if (unArchivo.exists() && unArchivo.length() > 0) {
            try {
                FileInputStream flujoEntrada = new FileInputStream(unArchivo);
                ObjectInputStream lectorObjetos = new ObjectInputStream(flujoEntrada);
                todosLosServicios = (ArrayList<Servicio>) lectorObjetos.readObject();
                lectorObjetos.close();
            } catch (EOFException e) {
                // Archivo vacío interpretado con seguridad
            } catch (Exception e) {
                System.err.println("Error al leer el historial: " + e.getMessage());
            }
        }

        for (Servicio servicio : todosLosServicios) {
            if (servicio.getNicknameUsuario().equalsIgnoreCase(nicknameUsuario)) {
                serviciosDelUsuario.add(servicio);
            }
        }

        if (serviciosDelUsuario.isEmpty()) {
            System.out.println("No tienes ningún servicio registrado en la base de datos.");
        } else {
            int contador = 1;
            for (Servicio servicio : serviciosDelUsuario) {
                System.out.println("\n Orden # " + contador);
                servicio.mostrarDetallesDelServicio();
                contador++;
            }
        }
    }
}