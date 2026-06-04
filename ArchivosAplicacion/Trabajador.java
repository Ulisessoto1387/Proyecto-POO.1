public class Trabajador extends Persona {
    private static final long serialVersionUID = 1L;

    private String especialidad; 
    private String estado; 
    public Trabajador(String nombre, String apellidop, String apellidom, int edad, String nickname, 
                      String contraseña, String contraseña2, String correo, String telefono, 
                      String direccion, String especialidad) {
        super(nombre, apellidop, apellidom, edad, nickname, contraseña, contraseña2, correo, telefono, direccion);
        this.especialidad = especialidad;
        this.estado = "Disponible";
    }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}