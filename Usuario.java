import java.io.Serializable;

public class Usuario extends Persona implements Serializable {
    private static final long serialVersionUID = 1L;

    public Usuario(String nombre, String apellidop, String apellidom, int edad, String nickname, String contraseña, String contraseña2, String correo, String telefono, String direccion) {
        super(nombre, apellidop, apellidom, edad, nickname, contraseña, contraseña2, correo, telefono, direccion);
    }
}