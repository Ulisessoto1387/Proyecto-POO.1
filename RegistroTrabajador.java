import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class RegistroTrabajador {

    public void pedirDatosTrabajador() {
        Scanner entrada = new Scanner(System.in);
        String nombre, apellidop, apellidom, nickname, contraseña, contraseña2, correo, direccion, telefono, especialidad;
        int edad;

        System.out.println("\n*****************************************");
        System.out.println("--- REGISTRO DE NUEVO TRABAJADOR ---");
        System.out.println("Nombre:");
        nombre = entrada.nextLine();
        System.out.println("Apellido paterno:");
        apellidop = entrada.nextLine();
        System.out.println("Apellido materno:");
        apellidom = entrada.nextLine();

        System.out.println("Edad:");
        while (!entrada.hasNextInt()) {
            System.out.println("Por favor ingresa una edad válida:");
            entrada.nextLine();
        }
        edad = entrada.nextInt();
        entrada.nextLine(); 

        System.out.println("Nickname / ID de empleado:");
        nickname = entrada.nextLine();
        System.out.println("Contraseña de acceso:");
        contraseña = entrada.nextLine();
        System.out.println("Confirma la contraseña:");
        contraseña2 = entrada.nextLine();
        System.out.println("Correo electrónico:");
        correo = entrada.nextLine();
        System.out.println("Teléfono:");
        telefono = entrada.nextLine();
        System.out.println("Dirección:");
        direccion = entrada.nextLine();
        System.out.println("Especialidad (Perros / Gatos / Ambos):");
        especialidad = entrada.nextLine();

        if (!contraseña.equals(contraseña2)) {
            System.out.println("Las contraseñas no coinciden. Registro cancelado.");
            return;
        }

        Trabajador nuevoEmpleado = new Trabajador(nombre, apellidop, apellidom, edad, nickname, contraseña, contraseña2, correo, telefono, direccion, especialidad);

        File archivo = new File("trabajadores.dat");
        ArrayList<Trabajador> listaTrabajadores = new ArrayList<>();

        if (archivo.exists() && archivo.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
                listaTrabajadores = (ArrayList<Trabajador>) ois.readObject();
            } catch (Exception e) {
                System.err.println("Error al leer la base de trabajadores.");
            }
        }

        boolean empleadoDuplicado = false;
        for (Trabajador t : listaTrabajadores) {
            if (t.getNickname().equalsIgnoreCase(nuevoEmpleado.getNickname())) {
                empleadoDuplicado = true;
                break;
            }
        }

        if (empleadoDuplicado) {
            System.out.println("\nError: Ya existe un trabajador registrado con ese Nickname/ID.");
            return;
        }

        listaTrabajadores.add(nuevoEmpleado);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(listaTrabajadores);
            System.out.println("\n¡Trabajador registrado exitosamente en el sistema!");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Error al escribir en el archivo de trabajadores.");
        }
    }
}