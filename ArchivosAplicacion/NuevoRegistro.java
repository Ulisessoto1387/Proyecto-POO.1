import java.io.*;
import java.util.Scanner;

public class NuevoRegistro {

    public void pedirDatos() {
        Scanner entrada = new Scanner(System.in);
        String nombre, apellidop, apellidom, nickname, contraseña, contraseña2, correo, direccion, telefono;
        int edad;
        boolean datosCorrectos = false;

        do {
            System.out.println("\n*****************************************");
            System.out.println("--- FORMULARIO DE NUEVO REGISTRO ---");
            System.out.print("Nombre: ");
            nombre = entrada.nextLine();
            System.out.print("Apellido paterno: ");
            apellidop = entrada.nextLine();
            System.out.print("Apellido materno: ");
            apellidom = entrada.nextLine();

            System.out.print("Edad: ");
            while (!entrada.hasNextInt()) {
                System.out.print("Por favor ingresa una edad numérica válida: ");
                entrada.nextLine();
            }
            edad = entrada.nextInt();
            entrada.nextLine(); // Limpiar buffer

            System.out.print("Nickname: ");
            nickname = entrada.nextLine();
            System.out.print("Contraseña: ");
            contraseña = entrada.nextLine();
            System.out.print("Confirma tu contraseña: ");
            contraseña2 = entrada.nextLine();
            System.out.print("Correo electrónico: ");
            correo = entrada.nextLine();
            System.out.print("Número de celular: ");
            telefono = entrada.nextLine();
            System.out.print("Dirección: ");
            direccion = entrada.nextLine();

            // Validación de contraseñas idénticas
            if (!contraseña.equals(contraseña2)) {
                System.out.println("\n[!] Las contraseñas ingresadas no coinciden. Se reiniciará el registro.");
                continue;
            }

            // Impresión en pantalla para confirmación del usuario
            System.out.println("\n--- DATOS INGRESADOS ---");
            System.out.println("Nombre Completo: " + nombre + " " + apellidop + " " + apellidom);
            System.out.println("Edad: " + edad);
            System.out.println("Nickname: " + nickname);
            System.out.println("Correo: " + correo);
            System.out.println("Celular: " + telefono);
            System.out.println("Dirección: " + direccion);
            
            System.out.print("\n¿Son correctos estos datos? (S/N): ");
            String respuesta = entrada.nextLine().trim().toUpperCase();
            if (respuesta.equals("S")) {
                datosCorrectos = true;
            } else {
                System.out.println("\n[!] Reiniciando formulario a petición del usuario...");
            }

        } while (!datosCorrectos);

        // Validación de Nickname Duplicado en archivo de TEXTO
        // CORRECCIÓN: Cambiar la ruta en NuevoRegistro.java
        File archivoTxt = new File("usuarios.txt");
        if (archivoTxt.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(archivoTxt))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] token = linea.split("\\|");
                    if (token.length > 4 && token[4].equalsIgnoreCase(nickname)) {
                        System.out.println("\n[!] Error: El Nickname ya corresponde a un usuario registrado previamente.");
                        return;
                    }
                }
            } catch (IOException e) {
                System.out.println("Error al validar duplicados en el archivo de texto.");
            }
        }

        // Guardado de datos en archivo de texto plano (delimitado por '|')
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTxt, true))) {
            bw.write(nombre + "|" + apellidop + "|" + apellidom + "|" + edad + "|" + nickname + "|" + contraseña + "|" + correo + "|" + telefono + "|" + direccion);
            bw.newLine();
            bw.flush();
            
            System.out.println("\n[SISTEMA] Procesando registro...");
            // Espera obligatoria de 5 segundos mostrando el estatus del registro
            Thread.sleep(5000);
            System.out.println("[SISTEMA] ¡Usuario registrado exitosamente!");
        } catch (Exception e) {
            System.out.println("Error al guardar el usuario en el archivo de texto.");
        }
    } 
}