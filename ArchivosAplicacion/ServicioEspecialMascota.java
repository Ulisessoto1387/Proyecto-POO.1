import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServicioEspecialMascota {

    public static void reservarServicio(String nicknameUsuario) {
        Scanner entrada = new Scanner(System.in);
        String usuarioActivo = nicknameUsuario.trim().toLowerCase();
        
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("==================================================");
        System.out.println("          SOLICITUD DE SERVICIO PARA MASCOTA      ");
        System.out.println("==================================================");

        // Leer mascotas registradas desde el archivo txt
        File archivoMascotas = new File("mascotas.txt");
        List<String[]> misMascotas = new ArrayList<>();

        if (archivoMascotas.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(archivoMascotas))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (linea.trim().isEmpty()) continue;
                    String[] datos = linea.split("\\|");
                    if (datos.length >= 6 && datos[0].trim().toLowerCase().equals(usuarioActivo)) {
                        misMascotas.add(datos);
                    }
                }
            } catch (IOException e) {
                System.out.println("[!] Error al leer el archivo de mascotas.");
            }
        }

        if (misMascotas.isEmpty()) {
            System.out.println("\n[!] El usuario no tiene mascotas registradas.");
            System.out.println("Por favor, registre una mascota previamente en el menú.");
            try { Thread.sleep(3000); } catch (Exception e) {}
            return;
        }

        System.out.println("Seleccione una de sus mascotas registradas:");
        for (int i = 0; i < misMascotas.size(); i++) {
            System.out.println((i + 1) + ") " + misMascotas.get(i)[2] + " (" + misMascotas.get(i)[1] + ")");
        }

        System.out.print("\nElija algún número de la lista: ");
        int seleccion = -1;
        try {
            seleccion = Integer.parseInt(entrada.nextLine().trim()) - 1;
        } catch (Exception e) {}

        if (seleccion < 0 || seleccion >= misMascotas.size()) {
            System.out.println("[!] Opción inválida. Cancelando...");
            return;
        }

        String[] mascota = misMascotas.get(seleccion);
        String tipoMascota = mascota[1].trim();     
        String nombreMascota = mascota[2].trim();
        String peloMascota = mascota[4].trim();       
        String alturaMascota = mascota[5].trim();     

        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("==================================================");
        System.out.println("          SOLICITUD DE SERVICIO PARA MASCOTA      ");
        System.out.println("==================================================");
        System.out.println("» Datos registrados de la mascota:");
        System.out.println("  • Nombre: " + nombreMascota);
        System.out.println("  • Tipo: " + tipoMascota);
        if (tipoMascota.equalsIgnoreCase("Perro")) {
            System.out.println("  • Altura/Tamaño: " + alturaMascota);
        }
        System.out.println("  • Tipo de Pelo: " + peloMascota);
        System.out.println("--------------------------------------------------");

        // TABLA ASIGNADA DE PRECIOS MATEMÁTICOS DE TU PDF
        double costoBano = 0, costoCortePelo = 0, costoCorteUnas = 0, costoDental = 0;

        if (tipoMascota.equalsIgnoreCase("Perro")) {
            // Matriz de Baño Perros
            switch (alturaMascota.toLowerCase()) {
                case "miniatura" -> { costoBano = 80.0;  if(peloMascota.equalsIgnoreCase("Sin pelo")) costoBano+=30; else if(peloMascota.equalsIgnoreCase("Pelo corto")) costoBano+=20; else if(peloMascota.equalsIgnoreCase("Pelo medio")) costoBano+=30; else costoBano+=40; }
                case "pequeño"   -> { costoBano = 130.0; if(peloMascota.equalsIgnoreCase("Sin pelo")) costoBano+=30; else if(peloMascota.equalsIgnoreCase("Pelo corto")) costoBano+=20; else if(peloMascota.equalsIgnoreCase("Pelo medio")) costoBano+=30; else costoBano+=40; }
                case "mediano"   -> { costoBano = 180.0; if(peloMascota.equalsIgnoreCase("Sin pelo")) costoBano+=40; else if(peloMascota.equalsIgnoreCase("Pelo corto")) costoBano+=30; else if(peloMascota.equalsIgnoreCase("Pelo medio")) costoBano+=40; else costoBano+=60; }
                case "grande"    -> { costoBano = 230.0; if(peloMascota.equalsIgnoreCase("Sin pelo")) costoBano+=40; else if(peloMascota.equalsIgnoreCase("Pelo corto")) costoBano+=30; else if(peloMascota.equalsIgnoreCase("Pelo medio")) costoBano+=40; else costoBano+=60; }
                case "gigante"   -> { costoBano = 280.0; if(peloMascota.equalsIgnoreCase("Sin pelo")) costoBano+=60; else if(peloMascota.equalsIgnoreCase("Pelo corto")) costoBano+=50; else if(peloMascota.equalsIgnoreCase("Pelo medio")) costoBano+=60; else costoBano+=80; }
            }
            // Matriz de Corte de Pelo Perros
            switch (alturaMascota.toLowerCase()) {
                case "miniatura" -> { if(peloMascota.equalsIgnoreCase("Pelo corto")) costoCortePelo=40; else if(peloMascota.equalsIgnoreCase("Pelo medio")) costoCortePelo=50; else if(peloMascota.equalsIgnoreCase("Pelo largo")) costoCortePelo=60; }
                case "pequeño"   -> { if(peloMascota.equalsIgnoreCase("Pelo corto")) costoCortePelo=50; else if(peloMascota.equalsIgnoreCase("Pelo medio")) costoCortePelo=60; else if(peloMascota.equalsIgnoreCase("Pelo largo")) costoCortePelo=70; }
                case "mediano"   -> { if(peloMascota.equalsIgnoreCase("Pelo corto")) costoCortePelo=60; else if(peloMascota.equalsIgnoreCase("Pelo medio")) costoCortePelo=70; else if(peloMascota.equalsIgnoreCase("Pelo largo")) costoCortePelo=80; }
                case "grande"    -> { if(peloMascota.equalsIgnoreCase("Pelo corto")) costoCortePelo=70; else if(peloMascota.equalsIgnoreCase("Pelo medio")) costoCortePelo=80; else if(peloMascota.equalsIgnoreCase("Pelo largo")) costoCortePelo=90; }
                case "gigante"   -> { if(peloMascota.equalsIgnoreCase("Pelo corto")) costoCortePelo=80; else if(peloMascota.equalsIgnoreCase("Pelo medio")) costoCortePelo=90; else if(peloMascota.equalsIgnoreCase("Pelo largo")) costoCortePelo=100; }
            }
            // Corte de uñas y Cepillado dental Perros
            switch (alturaMascota.toLowerCase()) {
                case "miniatura", "pequeño" -> { costoCorteUnas = 30.0; costoDental = 25.0; }
                case "mediano", "grande"   -> { costoCorteUnas = 45.0; costoDental = 40.0; }
                case "gigante"             -> { costoCorteUnas = 60.0; costoDental = 55.0; }
            }
        } else { 
            // Matriz Gatos
            costoBano = 150.0;
            if(peloMascota.equalsIgnoreCase("Sin pelo")) costoBano += 30;
            else if(peloMascota.equalsIgnoreCase("Pelo corto")) costoBano += 20;
            else if(peloMascota.equalsIgnoreCase("Pelo largo")) costoBano += 40;
            
            costoCorteUnas = 30.0;
            costoDental = 25.0;
            costoCortePelo = 0.0; 
        }

        System.out.println("» Lista de precios calculada para " + nombreMascota + ":");
        System.out.println("  a) Baño: $" + costoBano);
        System.out.println("  b) Corte de pelo: $" + costoCortePelo);
        System.out.println("  c) Corte de uñas: $" + costoCorteUnas);
        System.out.println("  d) Cepillado dental: $" + costoDental);
        System.out.println("--------------------------------------------------");

        System.out.print("Indique los servicios que quiere incluir (ej: ab o abc): ");
        String seleccionServicios = entrada.nextLine().trim().toLowerCase();

        int numServicios = 0;
        double costoNormalAcumulado = 0;
        StringBuilder detalleTicket = new StringBuilder();

        if (seleccionServicios.contains("a")) { costoNormalAcumulado += costoBano; numServicios++; detalleTicket.append("Baño, "); }
        if (seleccionServicios.contains("b")) { costoNormalAcumulado += costoCortePelo; numServicios++; detalleTicket.append("Corte de pelo, "); }
        if (seleccionServicios.contains("c")) { costoNormalAcumulado += costoCorteUnas; numServicios++; detalleTicket.append("Corte de uñas, "); }
        if (seleccionServicios.contains("d")) { costoNormalAcumulado += costoDental; numServicios++; detalleTicket.append("Cepillado dental, "); }

        if (numServicios == 0) {
            System.out.println("[!] No se seleccionó ningún servicio.");
            return;
        }
        if (detalleTicket.length() > 0) detalleTicket.setLength(detalleTicket.length() - 2);

        // Captura de Fecha (12 dígitos estrictos)
        System.out.println("\nHorarios normales: Lun-Vie (9 a 18 hrs), Sáb (9 a 15 hrs).");
        String fechaRaw = "";
        while (true) {
            System.out.print("Ingrese fecha y hora de la cita (AAAAMMDDHHMM): ");
            fechaRaw = entrada.nextLine().trim();
            if (fechaRaw.length() == 12 && fechaRaw.matches("\\d+")) break;
            System.out.println("[!] Error: El formato debe ser de exactamente 12 dígitos numéricos.");
        }

        int hora = Integer.parseInt(fechaRaw.substring(8, 10));
        int minutos = Integer.parseInt(fechaRaw.substring(10, 12));

        // Condición de Horario Especial (Pasa de las 17:30 o fuera de rango)
        boolean fueraHorario = (hora < 9 || hora > 18 || (hora == 17 && minutos >= 30));

        // Porcentajes fijos establecidos por ti
        double porcentajeRecargo = 0.15; // 15% por horario especial
        double porcentajeDesc = 0.10;    // 10% por volumen de servicios

        double cargoExtra = fueraHorario ? (costoNormalAcumulado * porcentajeRecargo) : 0.0;
        double precioTotal = costoNormalAcumulado + cargoExtra;

        // Descuento estricto: Sólo si el número de servicios es mayor que 3 (> 3)
        double descuento = (numServicios > 3) ? (precioTotal * porcentajeDesc) : 0.0;
        double precioFinal = precioTotal - descuento;

        // Pantalla Oficial: "Detalle del servicio"
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("==================================================");
        System.out.println("                DETALLE DEL SERVICIO              ");
        System.out.println("==================================================");
        System.out.println("• Nombre de la mascota: " + nombreMascota);
        if (tipoMascota.equalsIgnoreCase("Perro")) {
            System.out.println("• Altura de la mascota: " + alturaMascota);
        }
        System.out.println("• Largo de pelo de la mascota: " + peloMascota);
        System.out.println("--------------------------------------------------");
        System.out.println("DESAGREGADO DE PRECIOS DE LA ORDEN:");
        System.out.println("  • Costo normal por servicios: $" + costoNormalAcumulado);
        System.out.println("  • Cargo extra por horario (15%): $" + cargoExtra);
        System.out.println("  • Precio total de la orden: $" + precioTotal);
        System.out.println("  • Descuento por volumen aplicado (10%): -$" + descuento);
        System.out.println("==================================================");
        System.out.println("  • PRECIO FINAL DE LA ORDEN: $" + precioFinal);
        System.out.println("==================================================");

        System.out.print("Escriba 'Acepto' para confirmar o 'Cancelo' para abortar: ");
        String confirmacion = entrada.nextLine().trim();

        if (confirmacion.equalsIgnoreCase("Acepto")) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("citas.txt", true))) {
                bw.write(usuarioActivo + "|" + fechaRaw + "|" + nombreMascota + "|" + detalleTicket.toString() + "|" + cargoExtra + "|" + descuento + "|" + precioTotal + "|" + precioFinal);
                bw.newLine();
                bw.flush();
            } catch (IOException e) {
                System.out.println("[!] Error al guardar la cita.");
            }
            System.out.println("\n[SISTEMA] ¡Aceptaste el servicio!");
        } else {
            System.out.println("\n[SISTEMA] Cancelaste el servicio.");
        }

        try { Thread.sleep(3000); } catch (Exception e) {}
    }
}