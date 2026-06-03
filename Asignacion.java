import java.io.Serializable;

public class Asignacion implements Serializable {
    private static final long serialVersionUID = 1L;

    private Servicio servicio;
    private Trabajador trabajador;
    private String estadoCita; // "Pendiente de cobro" o "Finalizado"

    public Asignacion(Servicio servicio, Trabajador trabajador) {
        this.servicio = servicio;
        this.trabajador = trabajador;
        this.estadoCita = "Pendiente de cobro";
    }

    public Servicio getServicio() { return servicio; }
    public Trabajador getTrabajador() { return trabajador; }
    public String getEstadoCita() { return estadoCita; }
    public void setEstadoCita(String estadoCita) { this.estadoCita = estadoCita; }
}