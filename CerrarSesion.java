public class CerrarSesion {
    public static void SalirDeSesion() {
        System.out.println("\nGracias por visitar la Estética Canina y Felina, vuelva pronto.");
        System.out.println("    CERRANDO SESIÓN INTERACTIVA...   ");

        try {
            Thread.sleep(2000);
        } catch(Exception e) {
            // Manejo silencioso controlado
        }
    }
}