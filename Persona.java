import java.io.Serializable;

public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre; 
    private String apellidop;
    private String apellidom;
    private int edad; 
    private String nickname;
    private String contraseña;
    private String contraseña2; 
    private String correo; 
    private String telefono; 
    private String direccion; 

    public Persona(String nombre, String apellidop, String apellidom, int edad, String nickname, String contraseña, String contraseña2, String correo, String telefono, String direccion) {
        this.nombre = nombre; 
        this.apellidop = apellidop; 
        this.apellidom = apellidom; 
        this.edad = edad; 
        this.nickname = nickname; 
        this.contraseña = contraseña; 
        this.contraseña2 = contraseña2;
        this.correo = correo; 
        this.telefono = telefono; 
        this.direccion = direccion; 
    }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getNombre() { return nombre; }
    public void setApellidop(String apellidop) { this.apellidop = apellidop; }
    public String getApellidop() { return apellidop; }
    public void setApellidom(String apellidom) { this.apellidom = apellidom; }
    public String getApellidom() { return apellidom; }
    public void setEdad(int edad) { this.edad = edad; }
    public int getEdad() { return edad; }
    public void setNickname(String nickname) { this.nickname = nickname; }
    public String getNickname() { return nickname; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }
    public String getContraseña() { return contraseña; }
    public void setContraseña2(String contraseña2) { this.contraseña2 = contraseña2; }
    public String getContraseña2() { return contraseña2; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getCorreo() { return correo; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getTelefono() { return telefono; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getDireccion() { return direccion; }
}