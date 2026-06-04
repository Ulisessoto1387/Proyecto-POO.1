import java.io.Serializable;

public class Servicio implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String nicknameUsuario;
    private String tipoMascota;
    private String tamano;
    private String tipoPelo;
    private double precioFinal;

    public Servicio(String nicknameUsuario, String tipoMascota, String tamano, String tipoPelo, double precioFinal) {
        this.nicknameUsuario = nicknameUsuario;
        this.tipoMascota = tipoMascota;
        this.tamano = tamano;
        this.tipoPelo = tipoPelo;
        this.precioFinal = precioFinal;
    }

    public String getNicknameUsuario() {
        return nicknameUsuario;
    }

    public double getPrecioFinal() {
        return precioFinal;
    }

    public String getTipoMascota() {
        return tipoMascota;
    }

    public String getTamano() {
        return tamano;
    }

    public String getTipoPelo() {
        return tipoPelo;
    }
}