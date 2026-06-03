import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AsignarServicio {

    public static void asignar() {
        Scanner entrada = new Scanner(System.in);

        // 1. Cargar Órdenes de Servicios desde el archivo de los clientes
        File archivoServicios = new File("HistorialServiciosAdquiridos.dat");
        ArrayList<Servicio> listaServicios = new ArrayList<>();
        if (archivoServicios.exists() && archivoServicios.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoServicios))) {
                listaServicios = (ArrayList<Servicio>) ois.readObject();
            } catch (Exception e) {
                System.out.println("Error al cargar las órdenes de servicios.");
            }
        }

        if (listaServicios.isEmpty()) {
            System.out.println("\nNo hay servicios registrados por clientes pendientes en el sistema.");
            return;
        }

        // 2. Cargar asignaciones previas (para persistencia)
        File archivoAsignaciones = new File("asignaciones.dat");
        ArrayList<Asignacion> listaAsignaciones = new ArrayList<>();
        if (archivoAsignaciones.exists() && archivoAsignaciones.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoAsignaciones))) {
                listaAsignaciones = (ArrayList<Asignacion>) ois.readObject();
            } catch (Exception e) {}
        }

        // Mostrar menú de servicios disponibles
        System.out.println("\n--- SELECCIÓN DE SERVICIO PENDIENTE ---");
        for (int i = 0; i < listaServicios.size(); i++) {
            System.out.println((i + 1) + ".- Cliente: " + listaServicios.get(i).getNicknameUsuario() + 
                               " | Total a pagar: $" + listaServicios.get(i).getPrecioFinal());
        }
        System.out.print("Elija el número de servicio a asignar: ");
        int opServicio = entrada.nextInt() - 1;

        if (opServicio < 0 || opServicio >= listaServicios.size()) {
            System.out.println("Opción de servicio inválida.");
            return;
        }
        Servicio servicioSeleccionado = listaServicios.get(opServicio);

        // 3. Cargar Trabajadores y filtrar únicamente los que tengan estado "Disponible"
        File archivoTrabajadores = new File("trabajadores.dat");
        ArrayList<Trabajador> listaTrabajadores = new ArrayList<>();
        if (archivoTrabajadores.exists() && archivoTrabajadores.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoTrabajadores))) {
                listaTrabajadores = (ArrayList<Trabajador>) ois.readObject();
            } catch (Exception e) {
                System.out.println("Error al cargar la lista de trabajadores.");
            }
        }

        ArrayList<Trabajador> trabajadoresLibres = new ArrayList<>();
        for (Trabajador t : listaTrabajadores) {
            if (t.getEstado().equalsIgnoreCase("Disponible")) {
                trabajadoresLibres.add(t);
            }
        }

        if (trabajadoresLibres.isEmpty()) {
            System.out.println("\nNo hay ningún trabajador con estado 'Disponible' en este momento.");
            return;
        }

        // Mostrar menú de trabajadores libres
        System.out.println("\n--- SELECCIÓN DE TRABAJADOR DISPONIBLE ---");
        for (int i = 0; i < trabajadoresLibres.size(); i++) {
            System.out.println((i + 1) + ".- " + trabajadoresLibres.get(i).getNombre() + 
                               " (Especialidad: " + trabajadoresLibres.get(i).getEspecialidad() + ")");
        }
        System.out.print("Elija el número de trabajador a asignar: ");
        int opTrabajador = entrada.nextInt() - 1;

        if (opTrabajador < 0 || opTrabajador >= trabajadoresLibres.size()) {
            System.out.println("Opción de trabajador inválida.");
            return;
        }
        Trabajador trabajadorSeleccionado = trabajadoresLibres.get(opTrabajador);

        // 4. Cambiar el estado del trabajador seleccionado a "Ocupado" en la lista general
        for (Trabajador t : listaTrabajadores) {
            if (t.getNickname().equals(trabajadorSeleccionado.getNickname())) {
                t.setEstado("Ocupado");
                break;
            }
        }

        // Guardar la actualización de los trabajadores
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoTrabajadores))) {
            oos.writeObject(listaTrabajadores);
        } catch (Exception e) {
            System.out.println("Error al actualizar la agenda del trabajador.");
        }

        // 5. Crear el registro de asignación y guardarlo en asignaciones.dat
        Asignacion nuevaAsignacion = new Asignacion(servicioSeleccionado, trabajadorSeleccionado);
        listaAsignaciones.add(nuevaAsignacion);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoAsignaciones))) {
            oos.writeObject(listaAsignaciones);
            System.out.println("\n¡Asignación guardada con éxito!");
            System.out.println("El empleado '" + trabajadorSeleccionado.getNombre() + 
                               "' ahora está atendiendo la orden de '" + servicioSeleccionado.getNicknameUsuario() + "'.");
            Thread.sleep(2500);
        } catch (Exception e) {
            System.out.println("Error al escribir el archivo de asignaciones.");
        }
    }
}