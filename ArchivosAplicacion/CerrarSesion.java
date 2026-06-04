public class CerrarSesion {

    public static void SalirDeSesion() {
        System.out.println("\nCerrando sesión de forma segura...");
        try {
            Thread.sleep(1500);
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } catch (Exception e) {
            System.out.println("Sesión finalizada.");
        }
    }
}