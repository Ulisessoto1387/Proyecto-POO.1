import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class NuevoRegistro {

    public void pedirDatos() {
        Scanner entrada = new Scanner(System.in);
        String nombre, apellidop, apellidom, nickname, contraseña, contraseña2, correo, direccion, telefono;
        int edad;

        System.out.println("******************");
        System.out.println("Te pediremos tus datos a continuación...");
        System.out.println("Nombre:");
        nombre = entrada.nextLine();
        System.out.println("Apellido paterno:");
        apellidop = entrada.nextLine();
        System.out.println("Apellido materno:");
        apellidom = entrada.nextLine();

        System.out.println("Edad:");
        while (!entrada.hasNextInt()) {
            System.out.println("Por favor ingresa una edad numérica válida:");
            entrada.nextLine();
        }
        edad = entrada.nextInt();
        entrada.nextLine(); // Limpiar buffer

        System.out.println("Nickname:");
        nickname = entrada.nextLine();
        System.out.println("Contraseña:");
        contraseña = entrada.nextLine();
        System.out.println("Confirma tu contraseña:");
        contraseña2 = entrada.nextLine();
        System.out.println("Correo:");
        correo = entrada.nextLine();
        System.out.println("Telefono:");
        telefono = entrada.nextLine();
        System.out.println("Direccion:");
        direccion = entrada.nextLine();

        if (!contraseña.equals(contraseña2)) {
            System.out.println("Las contraseñas no coinciden. Registro cancelado.");
            return;
        }

        Usuario usuario01 = new Usuario(nombre, apellidop, apellidom, edad, nickname, contraseña, contraseña2, correo, telefono, direccion);
        File unArchivo = new File("registrando.dat");
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();

        if (unArchivo.exists() && unArchivo.length() > 0) {
            try {
                FileInputStream flujoEntrada = new FileInputStream(unArchivo);
                ObjectInputStream lectorObjetos = new ObjectInputStream(flujoEntrada);
                listaUsuarios = (ArrayList<Usuario>) lectorObjetos.readObject();
                lectorObjetos.close();
            } catch (Exception arr) {
                System.err.println("Error al leer la base de usuarios.");
            }
        }

        boolean usuarioDuplicado = false;
        for (Usuario u : listaUsuarios) {
            if (u.getNickname().equalsIgnoreCase(usuario01.getNickname())) {
                usuarioDuplicado = true;
                break;
            }
        }

        if (usuarioDuplicado) {
            System.out.println("\nError: Ya existe un usuario registrado con ese Nickname.");
            return;
        }

        listaUsuarios.add(usuario01);
        try {
            FileOutputStream flujoSalida = new FileOutputStream(unArchivo);
            ObjectOutputStream flujoSalidaObjetos = new ObjectOutputStream(flujoSalida);
            flujoSalidaObjetos.writeObject(listaUsuarios);
            flujoSalidaObjetos.flush();
            flujoSalidaObjetos.close();
            System.out.println("\n¡Registro hecho exitosamente!");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Error al escribir el archivo.");
        }
    } 
}