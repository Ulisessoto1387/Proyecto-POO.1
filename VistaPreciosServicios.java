import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class VistaPreciosServicios {

    public static void mostrarPrecios() {

        try {
            BufferedWriter escritor =
                    new BufferedWriter(new FileWriter("precios.txt"));

            escritor.write("\t\t************ Baño para perros ***********");
            escritor.newLine();
            escritor.newLine();

            escritor.write("Talla\t\tPrecio base\tSin pelo\tPelo corto\tPelo medio\tPelo largo");
            escritor.newLine();
            escritor.write("Miniatura\t$80.00\t\t$30.00\t\t$20.00\t\t$30.00\t\t$40.00");
            escritor.newLine();
            escritor.write("Pequeño\t\t$130.00\t$30.00\t\t$20.00\t\t$30.00\t\t$40.00");
            escritor.newLine();
            escritor.write("Mediano\t\t$180.00\t$40.00\t\t$30.00\t\t$40.00\t\t$60.00");
            escritor.newLine();
            escritor.write("Grande\t\t$230.00\t$40.00\t\t$30.00\t\t$40.00\t\t$60.00");
            escritor.newLine();
            escritor.write("Gigante\t\t$280.00\t$60.00\t\t$50.00\t\t$60.00\t\t$80.00");
            escritor.newLine();
            escritor.newLine();

            escritor.write("\t\t************ Corte de pelo para perros ***********");
            escritor.newLine();
            escritor.newLine();

            escritor.write("Talla\t\tSin pelo\tPelo corto\tPelo medio\tPelo largo");
            escritor.newLine();
            escritor.write("Miniatura\t$0.00\t\t$40.00\t\t$50.00\t\t$60.00");
            escritor.newLine();
            escritor.write("Pequeño\t\t$0.00\t\t$50.00\t\t$60.00\t\t$70.00");
            escritor.newLine();
            escritor.write("Mediano\t\t$0.00\t\t$60.00\t\t$70.00\t\t$80.00");
            escritor.newLine();
            escritor.write("Grande\t\t$0.00\t\t$70.00\t\t$80.00\t\t$90.00");
            escritor.newLine();
            escritor.write("Gigante\t\t$0.00\t\t$80.00\t\t$90.00\t\t$100.00");
            escritor.newLine();
            escritor.newLine();

            escritor.write("\t\t************ Tratamiento para perros ***********");
            escritor.newLine();
            escritor.newLine();

            escritor.write("Talla\t\tCorte de uñas\tCepillado dental");
            escritor.newLine();
            escritor.write("Miniatura\t$30.00\t\t$25.00");
            escritor.newLine();
            escritor.write("Pequeño\t\t$30.00\t\t$25.00");
            escritor.newLine();
            escritor.write("Mediano\t\t$45.00\t\t$40.00");
            escritor.newLine();
            escritor.write("Grande\t\t$45.00\t\t$40.00");
            escritor.newLine();
            escritor.write("Gigante\t\t$60.00\t\t$55.00");
            escritor.newLine();
            escritor.newLine();

            escritor.write("\t\t************ Baño para gatos ***********");
            escritor.newLine();
            escritor.newLine();

            escritor.write("Precio base\tSin pelo\tPelo corto\tPelo largo");
            escritor.newLine();
            escritor.write("$150.00\t$30.00\t\t$20.00\t\t$40.00");
            escritor.newLine();
            escritor.newLine();

            escritor.write("\t\t************ Tratamiento para gatos ***********");
            escritor.newLine();
            escritor.newLine();

            escritor.write("Corte de uñas\tCepillado dental");
            escritor.newLine();
            escritor.write("$30.00\t\t$25.00");
            escritor.newLine();

            escritor.close();

            BufferedReader lector =
                    new BufferedReader(new FileReader("precios.txt"));

            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }
            lector.close();

        } catch (IOException e) {
            System.out.println("Error al manejar el archivo.");
        }
    }
}