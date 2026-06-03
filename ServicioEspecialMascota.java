import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class ServicioEspecialMascota {

    public static void reservarServicio(String nicknameUsuario) {
        Scanner entrada = new Scanner(System.in);

        File archivoMascotas = new File("mascotas.dat");
        ArrayList<Mascota> todasLasMascotas = new ArrayList<>();
        ArrayList<Mascota> misMascotas = new ArrayList<>();

        if (archivoMascotas.exists() && archivoMascotas.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoMascotas))) {
                todasLasMascotas = (ArrayList<Mascota>) ois.readObject();
            } catch (Exception e) {
                System.out.println("Error al cargar mascotas.");
            }
        }

        for (Mascota m : todasLasMascotas) {
            if (m.getNicknameDueno() != null && m.getNicknameDueno().equalsIgnoreCase(nicknameUsuario)) {
                misMascotas.add(m);
            }
        }

        System.out.println("--- Solicitud de servicio para mascota ---");
        if (misMascotas.isEmpty()) {
            System.out.println("No tienes ninguna mascota registrada. Registra una primero.");
            try { Thread.sleep(2000); } catch(Exception e) {}
            return;
        }

        int opcionMascota;
        do {
            System.out.println("Selecciona el número de la mascota:");
            for (int i = 0; i < misMascotas.size(); i++) {
                System.out.println((i + 1) + ".- " + misMascotas.get(i).getNombre());
            }
            opcionMascota = entrada.nextInt();
            entrada.nextLine();

            if (opcionMascota < 1 || opcionMascota > misMascotas.size()) {
                System.out.println("Opción inválida. Vuelve a elegir...");
            }
        } while (opcionMascota < 1 || opcionMascota > misMascotas.size());

        Mascota mascotaSeleccionada = misMascotas.get(opcionMascota - 1);

        System.out.println("Ingresa la fecha y hora del servicio (Ej: 04/06/2026 15:00):");
        String fechaHora = entrada.nextLine();

        double costoNormalServicios = 400.0;
        double cargoExtra = 50.0; 
        double precioTotalSinDescuento = costoNormalServicios + cargoExtra;
        double descuentoAplicado = 0.0;

        // Validar historial para descuentos
        int serviciosPrevios = 0;
        File unArchivo = new File("HistorialServiciosAdquiridos.dat");
        ArrayList<Servicio> todosLosServicios = new ArrayList<>();

        if (unArchivo.exists() && unArchivo.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(unArchivo))) {
                todosLosServicios = (ArrayList<Servicio>) ois.readObject();
                for (Servicio s : todosLosServicios) {
                    if (s.getNicknameUsuario().equalsIgnoreCase(nicknameUsuario)) {
                        serviciosPrevios++;
                    }
                }
            } catch (Exception e) {}
        }

        if (serviciosPrevios >= 3) {
            double porcentajeDesc = CalculoCostos.obtenerPorcentajeDescuento();
            descuentoAplicado = precioTotalSinDescuento * porcentajeDesc;
        }

        double precioFinal = precioTotalSinDescuento - descuentoAplicado;

        System.out.println("\n--- Detalle del servicio ---");
        System.out.println("Nombre de la mascota: " + mascotaSeleccionada.getNombre());
        if (mascotaSeleccionada instanceof Perro) {
            System.out.println("Talla de la mascota: " + ((Perro) mascotaSeleccionada).getTalla());
        }
        System.out.println("Largo de pelo: " + mascotaSeleccionada.getTipoPelo());
        System.out.println("Desagregado de precios:");
        System.out.println("Costo base: $" + costoNormalServicios);
        System.out.println("Cargo extra por horario: $" + cargoExtra);
        System.out.println("Precio sin descuento: $" + precioTotalSinDescuento);
        System.out.println("Descuento aplicado: $" + descuentoAplicado);
        System.out.println("Precio final liquidable: $" + precioFinal);

        System.out.println("\nEscriba 'Acepto' para confirmar la reservación o 'Cancelo' para abortar:");
        String confirmacion = entrada.nextLine();

        if (confirmacion.equalsIgnoreCase("Acepto")) {
            String tipoMascota = (mascotaSeleccionada instanceof Perro) ? "Perro" : "Gato";
            String detalles = "Pelo: " + mascotaSeleccionada.getTipoPelo();
            if (mascotaSeleccionada instanceof Perro) detalles += ", Talla: " + ((Perro)mascotaSeleccionada).getTalla();

            Servicio nuevo = new Servicio(nicknameUsuario, mascotaSeleccionada.getNombre(), tipoMascota, detalles, precioFinal, fechaHora);
            todosLosServicios.add(nuevo);

            // 1. Guardar archivo binario (.dat)
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(unArchivo))) {
                oos.writeObject(todosLosServicios);
            } catch (Exception e) {
                System.out.println("Error guardando reporte binario.");
            }

            // 2. Guardar en archivo de texto plano (.txt) para VerPreciosDeServicio
            try (FileWriter fw = new FileWriter("preciosPorPersona.txt", true);
                 PrintWriter pw = new PrintWriter(fw)) {
                pw.println(nicknameUsuario + " - Mascota: " + mascotaSeleccionada.getNombre() + " | Total: $" + precioFinal + " | Cita: " + fechaHora);
            } catch (Exception e) {
                System.out.println("Error guardando reporte de texto.");
            }

            System.out.println("¡Servicio agendado exitosamente!");
        } else {
            System.out.println("Operación cancelada.");
        }
        try { Thread.sleep(2000); } catch(Exception e) {}
    }
}