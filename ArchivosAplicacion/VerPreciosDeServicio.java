import java.io.*;

public class VerPreciosDeServicio {
    
    public static void mostrarPreciosDelUsuario(String nicknameUsuario, int tipo) {
        // Limpieza de pantalla compatible con la terminal de VS Code / Linux
        System.out.print("\033[H\033[2J"); 
        System.out.flush();
        
        System.out.println("=======================================================================");
        System.out.println("        TABULADOR DE PRECIOS EN TIEMPO REAL - ESTÉTICA CANINA Y FELINA ");
        System.out.println("=======================================================================");
        System.out.println(" Hola " + nicknameUsuario + ", este es nuestro catálogo vigente:\n");

        // 1. LEER Y PROCESAR EL ARCHIVO DE PERROS
        System.out.println("--- 🐶 CATÁLOGO DE PERROS ---");
        File archivoPerros = new File("precios_perros.txt");
        
        if (!archivoPerros.exists()) {
            System.out.println("[!] Archivo 'precios_perros.txt' no encontrado en el directorio actual.");
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader(archivoPerros))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (linea.trim().isEmpty()) continue;
                    
                    String[] datos = linea.split("\\|");
                    if (datos.length >= 3) {
                        String servicio = datos[0];
                        String talla = datos[1];
                        String precioBase = datos[2];
                        System.out.printf(" • %-12s (%-9s) -> Base: $%s", servicio, talla, precioBase);
                        
                        // Si el archivo incluye los cargos extras por tipo de pelo
                        if (datos.length >= 6) {
                            System.out.printf(" | Extras Pelo: Corto +$%s / Mediano +$%s / Largo +$%s", datos[3], datos[4], datos[5]);
                        }
                        System.out.println();
                    } else if (datos.length == 2) {
                        System.out.printf(" • %-12s -> Costo: $%s\n", datos[0], datos[1]);
                    }
                }
            } catch (IOException e) {
                System.out.println("[!] Error al leer el catálogo de perros de forma dinámica.");
            }
        }

        System.out.println("\n-----------------------------------------------------------------------");
        
        // 2. LEER Y PROCESAR EL ARCHIVO DE GATOS
        System.out.println("--- 🐱 CATÁLOGO DE GATOS ---");
        File archivoGatos = new File("precios_gatos.txt");
        
        if (!archivoGatos.exists()) {
            System.out.println("[!] Archivo 'precios_gatos.txt' no encontrado en el directorio actual.");
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader(archivoGatos))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (linea.trim().isEmpty()) continue;
                    
                    String[] datos = linea.split("\\|");
                    if (datos.length >= 2) {
                        String servicio = datos[0];
                        String precioBase = datos[1];
                        System.out.printf(" • %-15s -> Costo Base: $%s", servicio, precioBase);
                        
                        if (datos.length >= 5) {
                            System.out.printf(" (Extras Pelo: +$%s / +$%s / +$%s)", datos[2], datos[3], datos[4]);
                        }
                        System.out.println();
                    }
                }
            } catch (IOException e) {
                System.out.println("[!] Error al leer el catálogo de gatos de forma dinámica.");
            }
        }

        System.out.println("=======================================================================");
        System.out.println("\nPresione Enter para regresar al menú de usuario...");
        try { 
            System.in.read(); 
        } catch(Exception e) {}
    }
}