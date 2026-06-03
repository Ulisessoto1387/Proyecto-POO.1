import java.io.Serializable;

public class Servicio implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nicknameUsuario;
    private String nombreMascota;
    private String tipoMascota;
    private String detalles;
    private double precioFinal;
    private String fechaHora;

    public Servicio(String nicknameUsuario, String nombreMascota, String tipoMascota, String detalles, double precioFinal, String fechaHora) {
        this.nicknameUsuario = nicknameUsuario;
        this.nombreMascota = nombreMascota;
        this.tipoMascota = tipoMascota;
        this.detalles = detalles;
        this.precioFinal = precioFinal;
        this.fechaHora = fechaHora;
    }

    public String getNicknameUsuario() { return nicknameUsuario; }
    public double getPrecioFinal() { return precioFinal; }

    public void mostrarDetallesDelServicio() {
        System.out.println("Mascota: " + nombreMascota + " (" + tipoMascota + ")");
        System.out.println("Detalles: " + detalles);
        System.out.println("Fecha y Hora: " + fechaHora);
        System.out.println("Costo total liquidado: $" + precioFinal);
    }
}