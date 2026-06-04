import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AsignarServicio {

    public static void asignar() {
        Scanner entrada = new Scanner(System.in);
    
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("==================================================");
        System.out.println("          7.2 ASIGNACIÓN DE SERVICIOS             ");
        System.out.println("==================================================");

        File archivoCitas = new File("citas.txt");
        List<String[]> listaCitas = new ArrayList<>();
        if (archivoCitas.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(archivoCitas))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (linea.trim().isEmpty()) continue;
                    String[] datos = linea.split("\\|");
                    if (datos.length >= 4) listaCitas.add(datos);
                }
            } catch (Exception e) {}
        }

        if (listaCitas.isEmpty()) {
            System.out.println("[i] No hay servicios programados actualmente.");
            System.out.println("Redireccionando al menú de administrador en 5 segundos...");
            try { Thread.sleep(5000); } catch (Exception e) {}
            return;
        }

        System.out.println("Servicios en espera de asignación:");
        for (int i = 0; i < listaCitas.size(); i++) {
            System.out.println((i + 1) + ") Cliente: " + listaCitas.get(i)[0] + " | Mascota: " + listaCitas.get(i)[2]);
        }

        System.out.print("\nSeleccione un número de la lista para iniciar el servicio: ");
        int selCita = -1;
        try { selCita = Integer.parseInt(entrada.nextLine().trim()) - 1; } catch (Exception e) {}

        if (selCita < 0 || selCita >= listaCitas.size()) {
            System.out.println("[!] Opción inválida. Cancelando...");
            try { Thread.sleep(2000); } catch (Exception e) {}
            return;
        }

        String[] citaSeleccionada = listaCitas.get(selCita);
        String dueño = citaSeleccionada[0];
        String mascota = citaSeleccionada[2];
        String serviciosStr = citaSeleccionada[3];

        File archivoTrab = new File("trabajadores.txt");
        List<String[]> listaTrab = new ArrayList<>();
        if (archivoTrab.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(archivoTrab))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (linea.trim().isEmpty()) continue;
                    String[] datos = linea.split("\\|");
                    if (datos.length >= 6 && datos[5].equalsIgnoreCase("Disponible")) {
                        listaTrab.add(datos);
                    }
                }
            } catch (Exception e) {}
        }

        if (listaTrab.isEmpty()) {
            System.out.println("\n[!] No hay empleados disponibles en este momento para realizar el servicio.");
            System.out.println("Presione Enter para regresar...");
            entrada.nextLine();
            return;
        }

        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("==================================================");
        System.out.println("» Datos del Servicio Seleccionado:");
        System.out.println("  • Nombre del cliente: " + dueño);
        System.out.println("  • Nombre de la mascota: " + mascota);
        System.out.println("  • Detalles de atención: [" + serviciosStr + "]");
        System.out.println("--------------------------------------------------");
        
        System.out.println("Seleccione al empleado que se hará cargo:");
        for (int i = 0; i < listaTrab.size(); i++) {
            System.out.println((i + 1) + ") " + listaTrab.get(i)[0]);
        }

        System.out.print("\nNúmero de empleado: ");
        int selTrab = -1;
        try { selTrab = Integer.parseInt(entrada.nextLine().trim()) - 1; } catch (Exception e) {}

        if (selTrab < 0 || selTrab >= listaTrab.size()) {
            System.out.println("[!] Empleado inválido.");
            return;
        }

        String nombreEmpleado = listaTrab.get(selTrab)[0];

        System.out.println("\n[SISTEMA] Iniciando labores de atención...\n");
        String[] serviciosArreglo = serviciosStr.split(",");

        for (String serv : serviciosArreglo) {
            String s = serv.trim();
            System.out.println("» Empleado: Estoy aplicando [" + s + "] a " + mascota);
            try { Thread.sleep(3000); } catch (Exception e) {}

            System.out.println("» Mascota (" + mascota + "): ¡Guau / Miau feliz!");
            System.out.println("--------------------------------------------------");
            try { Thread.sleep(3000); } catch (Exception e) {}
        }

        System.out.println("\n==================================================");
        System.out.println(" [✓] ¡TODOS LOS SERVICIOS ESTÁN LISTOS! ");
        System.out.println(" Mascota atendida: " + mascota);
        System.out.println(" Dueño de la mascota: " + dueño);
        System.out.println("==================================================");

        System.out.println("\nPresione Enter para volver al menú de administrador...");
        entrada.nextLine();
    }
}