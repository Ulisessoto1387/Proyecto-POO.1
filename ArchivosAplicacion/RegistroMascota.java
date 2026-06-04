import java.io.*;
import java.util.Scanner;

public class RegistroMascota {

    public static void registrarMascota(String nicknameDueño) {
        Scanner sc = new Scanner(System.in);
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("==================================================");
        System.out.println("               REGISTRO DE MASCOTA                ");
        System.out.println("==================================================");
        
        System.out.println("¿Qué tipo de mascota deseas registrar?");
        System.out.println("1) Perro");
        System.out.println("2) Gato");
        System.out.print("Seleccione una opción: ");
        String opTipo = sc.nextLine().trim();

        if (!opTipo.equals("1") && !opTipo.equals("2")) {
            System.out.println("[!] Opción inválida. Regresando al menú...");
            return;
        }

        String tipo = opTipo.equals("1") ? "Perro" : "Gato";
        
        System.out.print("\nIntroduce el nombre de la mascota: ");
        String nombre = sc.nextLine().trim();
        
        System.out.print("Introduce la edad (en años): ");
        int edad = 0;
        try {
            edad = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("[!] Edad no válida. Se registrará con 0 años.");
        }

        String altura = "N/A"; 
        String pelo = "";

        if (tipo.equals("Perro")) {
            // Selección de Altura para Perros
            System.out.println("\nSelecciona la altura del perro:");
            System.out.println("1) Miniatura – 25 cm o menos");
            System.out.println("2) Pequeño – 25 a 40 cm");
            System.out.println("3) Mediano – 40 a 60 cm");
            System.out.println("4) Grande – 60 a 80 cm");
            System.out.println("5) Gigante – Más de 80 cm");
            System.out.print("Opción: ");
            String opAlt = sc.nextLine().trim();
            switch(opAlt) {
                case "1" -> altura = "Miniatura";
                case "2" -> altura = "Pequeño";
                case "3" -> altura = "Mediano";
                case "4" -> altura = "Grande";
                case "5" -> altura = "Gigante";
                default -> altura = "Mediano";
            }

            // Selección de Pelo para Perros
            System.out.println("\nSelecciona el tipo de pelo:");
            System.out.println("1) Sin pelo (Ej. Xoloitzcuintle, Terrier americano)");
            System.out.println("2) Pelo corto (Ej. Dóberman, Pit bull, Bulldog, Beagle)");
            System.out.println("3) Pelo medio (Ej. Maltés, Husky, Pastor alemán, Border collie)");
            System.out.println("4) Pelo largo (Ej. Collie de pelo largo, Galgo albano, Yorkshire, Komondor)");
            System.out.print("Opción: ");
            String opPelo = sc.nextLine().trim();
            switch(opPelo) {
                case "1" -> pelo = "Sin pelo";
                case "2" -> pelo = "Pelo corto";
                case "3" -> pelo = "Pelo medio";
                case "4" -> pelo = "Pelo largo";
                default -> pelo = "Pelo corto";
            }
        } else {
            // Selección de Pelo para Gatos
            System.out.println("\nSelecciona el tipo de pelo del gato:");
            System.out.println("1) Sin pelo (Ej. Esfinge, Elfo, Bambino)");
            System.out.println("2) Pelo medio (Ej. Gato doméstico americano, Siamés, Escocés)");
            System.out.println("3) Pelo largo (Ej. Persa, Angora, Him)");
            System.out.print("Opción: ");
            String opPeloG = sc.nextLine().trim();
            switch(opPeloG) {
                case "1" -> pelo = "Sin pelo";
                case "2" -> pelo = "Pelo medio";
                case "3" -> pelo = "Pelo largo";
                default -> pelo = "Pelo medio";
            }
        }

        // Guardado en archivo de texto local
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("mascotas.txt", true))) {
            bw.write(nicknameDueño.trim().toLowerCase() + "|" + tipo + "|" + nombre + "|" + edad + "|" + pelo + "|" + altura);
            bw.newLine();
            bw.flush();
            
            System.out.println("\n[SISTEMA] Guardando datos de la mascota...");
            Thread.sleep(3000);
            System.out.println("[SISTEMA] ¡El registro ha sido exitoso!");
        } catch (Exception e) {
            System.out.println("[!] Error al almacenar la mascota: " + e.getMessage());
        }
    }
}