import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class IngresoAlSistema {

    // Retorna el nickname si la sesión es válida, "ADMIN" si es administrador, o null si falla.
    public String iniciarSesion() {
        Scanner entrada = new Scanner(System.in);
        String nickname, contraseña;
        Usuario usuarioEncontrado = null;
        boolean acceso = false;

        File unArchivo = new File("registrando.dat");
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();

        if (unArchivo.exists() && unArchivo.length() > 0) {
            try {
                FileInputStream flujoEntrada = new FileInputStream(unArchivo);
                ObjectInputStream lectorObjetos = new ObjectInputStream(flujoEntrada);
                listaUsuarios = (ArrayList<Usuario>) lectorObjetos.readObject();
                lectorObjetos.close();
            } catch (Exception e) {
                System.err.println("Error al cargar credenciales del sistema.");
            }
        }

        System.out.println("**************");
        System.out.println("Ingreso al sistema");
        System.out.println("Nickname: ");
        nickname = entrada.nextLine();
        System.out.println("Contraseña: ");
        contraseña = entrada.nextLine();

        // Cuenta de Administrador por defecto
        if (nickname.equals("admin") && contraseña.equals("1234")) {
            System.out.println("Acceso Administrador concedido.");
            ControlAdmin.ejecutarMenu(); // <--- Aquí queda agregada la modificación
            return "ADMIN";
        }

        for (Usuario u : listaUsuarios) {
            if (u.getNickname().equals(nickname) && u.getContraseña().equals(contraseña)) {
                acceso = true;
                usuarioEncontrado = u;
                break;
            }
        }

        if (acceso) {
            System.out.println("Acceso permitido. ¡Bienvenido, " + usuarioEncontrado.getNombre() + "!");
            return usuarioEncontrado.getNickname();
        } else {
            System.out.println("Los datos ingresados no son correctos.");
            return null;
        }
    }
}