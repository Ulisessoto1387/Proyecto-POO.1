import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CobrarServicio {

    public static void procesarCobro() {
        Scanner entrada = new Scanner(System.in);

        // 1. Cargar las asignaciones existentes
        File archivoAsignaciones = new File("asignaciones.dat");
        ArrayList<Asignacion> listaAsignaciones = new ArrayList<>();
        if (archivoAsignaciones.exists() && archivoAsignaciones.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoAsignaciones))) {
                listaAsignaciones = (ArrayList<Asignacion>) ois.readObject();
            } catch (Exception e) {
                System.out.println("Error al cargar el registro de asignaciones.");
                return;
            }
        }

        // Filtrar únicamente las asignaciones que estén en estado "Pendiente de cobro"
        ArrayList<Asignacion> pendientes = new ArrayList<>();
        for (Asignacion a : listaAsignaciones) {
            if (a.getEstadoCita().equalsIgnoreCase("Pendiente de cobro")) {
                pendientes.add(a);
            }
        }

        if (pendientes.isEmpty()) {
            System.out.println("\nNo hay órdenes de servicio pendientes de cobro en este momento.");
            return;
        }

        // Mostrar lista de servicios por cobrar
        System.out.println("\n--- ÓRDENES PENDIENTES DE COBRO ---");
        for (int i = 0; i < pendientes.size(); i++) {
            Asignacion asig = pendientes.get(i);
            System.out.println((i + 1) + ".- Cliente: " + asig.getServicio().getNicknameUsuario() +
                               " | Atendido por: " + asig.getTrabajador().getNombre() +
                               " | Total a pagar: $" + asig.getServicio().getPrecioFinal());
        }
        System.out.print("Seleccione el número de orden a liquidar: ");
        int op = entrada.nextInt() - 1;

        if (op < 0 || op >= pendientes.size()) {
            System.out.println("Opción de orden inválida.");
            return;
        }

        Asignacion asignacionSeleccionada = pendientes.get(op);

        // 2. Integración con tu archivo VistaMetodoPago
        System.out.println("\n=========================================");
        System.out.println("Monto total de la cuenta: $" + asignacionSeleccionada.getServicio().getPrecioFinal());
        VistaMetodoPago vistaPago = new VistaMetodoPago();
        int metodoSeleccionado = vistaPago.mostrarMenuDePago();

        if (metodoSeleccionado >= 1 && metodoSeleccionado <= 4) {
            System.out.println("\nProcesando transacción... ¡Pago aprobado con éxito!");
        } else {
            System.out.println("Opción de pago inválida. Cobro cancelado.");
            return;
        }

        // 3. Cambiar el estado de la cita a "Finalizado" en la lista general de asignaciones
        for (Asignacion a : listaAsignaciones) {
            if (a.getServicio().getNicknameUsuario().equals(asignacionSeleccionada.getServicio().getNicknameUsuario()) &&
                a.getEstadoCita().equals("Pendiente de cobro")) {
                a.setEstadoCita("Finalizado");
                break;
            }
        }

        // 4. Cargar la lista de trabajadores para regresar al empleado a estado "Disponible"
        File archivoTrabajadores = new File("trabajadores.dat");
        ArrayList<Trabajador> listaTrabajadores = new ArrayList<>();
        if (archivoTrabajadores.exists() && archivoTrabajadores.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoTrabajadores))) {
                listaTrabajadores = (ArrayList<Trabajador>) ois.readObject();
            } catch (Exception e) {}
        }

        for (Trabajador t : listaTrabajadores) {
            if (t.getNickname().equals(asignacionSeleccionada.getTrabajador().getNickname())) {
                t.setEstado("Disponible");
                break;
            }
        }

        // 5. Persistir los datos actualizados de forma binaria en ambos archivos
        try (ObjectOutputStream oosAsig = new ObjectOutputStream(new FileOutputStream(archivoAsignaciones));
             ObjectOutputStream oosTrab = new ObjectOutputStream(new FileOutputStream(archivoTrabajadores))) {

            oosAsig.writeObject(listaAsignaciones);
            oosTrab.writeObject(listaTrabajadores);

            System.out.println("\n¡Orden de servicio cerrada y archivada!");
            System.out.println("El empleado '" + asignacionSeleccionada.getTrabajador().getNombre() + 
                               "' ha quedado LIBRE y DISPONIBLE nuevamente.");
            Thread.sleep(2500);
        } catch (Exception e) {
            System.out.println("Error al guardar los cambios en la base de datos.");
        }
    }
}