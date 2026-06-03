public class Perro extends Mascota {
    private static final long serialVersionUID = 1L;
    private String talla;

    public Perro(String nombre, int edad, String tipoPelo, String talla) {
        super(nombre, edad, tipoPelo);
        this.talla = talla;
    }

    public void setTalla(String talla) { this.talla = talla; }
    public String getTalla() { return talla; }
}