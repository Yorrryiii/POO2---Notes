import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import otrasClases.Indice;

public class Ejercicio3 {
    public static void main(String[] args) {
        String[] extensiones = Indice.generarExtensiones(1000);

        Path carpeta = Paths.get("p3");
        int numeroArchivos = 5000;

        Indice.generarArchivos(carpeta, numeroArchivos, extensiones);

        Map<String, Integer> indice = Indice.generarIndice(carpeta);

        System.out.println("Indice de extensiones de la carpeta:");
        for (Map.Entry<String, Integer> entry : indice.entrySet()) {
            String extension = entry.getKey();
            int cantidadArchivos = entry.getValue();
            System.out.println(extension + ": " + cantidadArchivos);
        }
    }
}
