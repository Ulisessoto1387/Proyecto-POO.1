public class Cliente extends Usuario {
    private static final long serialVersionUID = 1L;

    public Cliente(String nombre, String apellidop, String apellidom, int edad, String nickname, String contraseña, String contraseña2, String correo, String telefono, String direccion) {
        super(nombre, apellidop, apellidom, edad, nickname, contraseña, contraseña2, correo, telefono, direccion);
    }
}