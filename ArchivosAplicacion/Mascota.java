import java.io.Serializable;

public class Mascota implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private int edad;
    private String tipoPelo;
    private String nicknameDueno;

    public Mascota(String nombre, int edad, String tipoPelo) {
        this.nombre = nombre;
        this.edad = edad;
        this.tipoPelo = tipoPelo;
    }

    public void setNombre(String nombre) { 
        this.nombre = nombre; }
    public String getNombre() { 
        return nombre; }
    public void setEdad(int edad) { 
        this.edad = edad; }
    public int getEdad() { 
        return edad; }
    public void setTipoPelo(String tipoPelo) { 
        this.tipoPelo = tipoPelo; }
    public String getTipoPelo() { 
        return tipoPelo; }
    public void setNicknameDueno(String nicknameDueno) { 
        this.nicknameDueno = nicknameDueno; }
    public String getNicknameDueno() { 
        return nicknameDueno; }
}