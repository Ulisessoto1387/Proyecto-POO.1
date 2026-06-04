import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CobrarServicio {

    public static void procesarCobro() {
        Scanner entrada = new Scanner(System.in);
        
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("==================================================");
        System.out.println("          7.3 COBRO DE SERVICIO EN CAJA           ");
        System.out.println("==================================================");

        File archivoCitas = new File("citas.txt");
        List<String[]> listaCitas = new ArrayList<>();

        if (archivoCitas.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(archivoCitas))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (linea.trim().isEmpty()) continue;
                    String[] datos = linea.split("\\|");
                    if (datos.length >= 8) listaCitas.add(datos);
                }
            } catch (IOException e) {}
        }

        if (listaCitas.isEmpty()) {
            System.out.println("[i] No hay servicios programados pendientes para cobro.");
            System.out.println("Redireccionando al menú de administrador en 5 segundos...");
            try { Thread.sleep(5000); } catch (Exception e) {}
            return;
        }

        System.out.println("Órdenes pendientes por liquidar:");
        for (int i = 0; i < listaCitas.size(); i++) {
            System.out.println((i + 1) + ") Cliente: " + listaCitas.get(i)[0] + " | Mascota: " + listaCitas.get(i)[2]);
        }

        System.out.print("\nSeleccione el número de orden a cobrar: ");
        int index = -1;
        try { index = Integer.parseInt(entrada.nextLine().trim()) - 1; } catch (Exception e){}

        if (index < 0 || index >= listaCitas.size()) {
            System.out.println("[!] Selección incorrecta.");
            return;
        }

        String[] cita = listaCitas.get(index);
        
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("==================================================");
        System.out.println("              DESAGREGADO DE PRECIOS              ");
        System.out.println("==================================================");
        System.out.println(" • Mascota: " + cita[2]);
        System.out.println(" • Servicios incluidos: [" + cita[3] + "]");
        System.out.println("--------------------------------------------------");
        System.out.println("  Costo base acumulado:        $" + cita[6]);
        System.out.println("  Cargo extra por horario:    +$" + cita[4]);
        System.out.println("  Descuento por volumen (>3): -$" + cita[5]);
        System.out.println("--------------------------------------------------");
        System.out.println("  PRECIO FINAL A PAGAR:        $" + cita[7]);
        System.out.println("==================================================");

        System.out.print("Escriba 'Acepto' para proceder al cobro o 'Cancelo' para abortar: ");
        String confirmacion = entrada.nextLine().trim();

        if (!confirmacion.equalsIgnoreCase("Acepto")) {
            System.out.println("\n[SISTEMA] Cobro cancelado. Regresando al menú...");
            try { Thread.sleep(2000); } catch (Exception e) {}
            return;
        }

        // Menú de las 4 opciones de pago exigidas por la rúbrica
        System.out.println("\nSeleccione el método de pago:");
        System.out.println("1. Pago en efectivo");
        System.out.println("2. Escaneo de código");
        System.out.println("3. Transferencia interbancaria");
        System.out.println("4. Pago con tarjeta");
        System.out.print("Opción: ");
        String metodo = entrada.nextLine().trim();

        if (metodo.equals("4")) {
            System.out.print("Ingrese la clave o NIP de la tarjeta: ");
            String nip = entrada.nextLine().trim();
            System.out.println("\n[Procesando transacción bancaria...]");
            try { Thread.sleep(1500); } catch (Exception e) {}
        }

        // Mensaje de éxito de la rúbrica
        System.out.println("\n==================================================");
        System.out.println("        ¡Gracias por su pago, vuelva pronto!      ");
        System.out.println("==================================================");

        // Quitar la cita cobrada del archivo citas.txt
        listaCitas.remove(index);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoCitas, false))) {
            for (String[] c : listaCitas) {
                bw.write(String.join("|", c));
                bw.newLine();
            }
        } catch (Exception e) {}

        System.out.println("\nPresione Enter para continuar...");
        entrada.nextLine();
    }
}