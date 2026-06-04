import java.io.*;
import java.util.Scanner;

public class RegistroTrabajador {

    public static void pedirDatosTrabajador() {
        Scanner entrada = new Scanner(System.in);
        
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("==================================================");
        System.out.println("          7.1 REGISTRO DE NUEVO EMPLEADO          ");
        System.out.println("==================================================");
        
        System.out.print("» Nombre(s) y Apellidos completos: ");
        String nombreCompleto = entrada.nextLine().trim();
        
        System.out.print("» Fecha de nacimiento (DD/MM/AAAA): ");
        String fechaNac = entrada.nextLine().trim();
        
        System.out.print("» Correo electrónico: ");
        String correo = entrada.nextLine().trim();
        
        System.out.print("» Número de celular: ");
        String celular = entrada.nextLine().trim();
        
        System.out.print("» Dirección completa: ");
        String direccion = entrada.nextLine().trim();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("trabajadores.txt", true))) {
            bw.write(nombreCompleto + "|" + fechaNac + "|" + correo + "|" + celular + "|" + direccion + "|Disponible");
            bw.newLine();
            System.out.println("\n[SISTEMA] ¡Empleado registrado con éxito!");
        } catch (IOException e) {
            System.out.println("[!] Error al escribir en el archivo de trabajadores.");
        }
        
        System.out.println("\nRedireccionando al menú de administrador...");
        try { Thread.sleep(2500); } catch (Exception e) {}
    }
}