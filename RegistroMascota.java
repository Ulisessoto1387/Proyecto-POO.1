import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class RegistroMascota {

    public void registrarMascota(String nicknameUsuario) {
        Scanner entrada = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("******** REGISTRO DE MASCOTA ********");
            System.out.println("1) Registrar perro");
            System.out.println("2) Registrar gato");
            System.out.print("Elija una opción: ");

            if (entrada.hasNextInt()) {
                opcion = entrada.nextInt();
                entrada.nextLine(); 
            } else {
                System.out.println("Por favor, ingrese un número válido.");
                entrada.nextLine(); 
                continue;
            }

            if (opcion != 1 && opcion != 2) {
                System.out.println("Opción no válida. Intente de nuevo.\n");
            }
        } while (opcion != 1 && opcion != 2);

        String nombre;
        int edad;
        String tipoPelo;

        System.out.println("Nombre de la mascota:");
        nombre = entrada.nextLine();

        System.out.println("Edad:");
        while (!entrada.hasNextInt()) {
            System.out.println("Introduce una edad numérica válida:");
            entrada.nextLine();
        }
        edad = entrada.nextInt();
        entrada.nextLine(); 

        System.out.println("Tipo de pelo (Corto/Mediano/Largo):");
        tipoPelo = entrada.nextLine();

        if (opcion == 1) {
            System.out.println("Talla (Chico/Mediano/Grande):");
            String talla = entrada.nextLine();

            Perro perro = new Perro(nombre, edad, tipoPelo, talla);
            perro.setNicknameDueno(nicknameUsuario); 
            guardarMascota(perro);

        } else if (opcion == 2) {
            Gato gato = new Gato(nombre, edad, tipoPelo);
            gato.setNicknameDueno(nicknameUsuario); 
            guardarMascota(gato);
        }
    }

    public void guardarMascota(Mascota mascota) {
        File archivo = new File("mascotas.dat");
        ArrayList<Mascota> mascotas = new ArrayList<>();

        try {
            if (archivo.exists() && archivo.length() > 0) {
                FileInputStream fis = new FileInputStream(archivo);
                ObjectInputStream ois = new ObjectInputStream(fis);
                mascotas = (ArrayList<Mascota>) ois.readObject();
                ois.close();
            }

            mascotas.add(mascota);

            FileOutputStream fos = new FileOutputStream(archivo);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(mascotas);
            oos.close();

            System.out.println("Mascota registrada exitosamente.");
            Thread.sleep(1500);
        } catch (Exception e) {
            System.out.println("Error al guardar la mascota: " + e.getMessage());
        }
    }
}