package otrasClases;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import java.io.IOException;
import java.nio.file.*;

public class Indice {
    public static String[] generarExtensiones(int numeroExtensiones) {
        String[] extensiones = new String[numeroExtensiones];
        Set<String> extensionesSet = new HashSet<>();

        Random random = new Random();

        while (extensionesSet.size() < numeroExtensiones) {
            StringBuilder extension = new StringBuilder();

            // Generar tres letras aleatorias
            for (int i = 0; i < 3; i++) {
                char letra = (char) (random.nextInt(26) + 'a');
                extension.append(letra);
            }

            String extensionString = "." + extension.toString();

            extensionesSet.add(extensionString);
        }

        return extensionesSet.toArray(extensiones);
    }

    public static void generarArchivos(Path carpeta, int numeroArchivos, String[] extensiones) {
        // Verificar si la carpeta existe, si no existe, crearla
        if (!Files.exists(carpeta)) {
            try {
                Files.createDirectories(carpeta);
            } catch (IOException e) {
                System.out.println("Error al crear la carpeta: " + e.getMessage());
                return;
            }
        }

        // Generar los archivos en la carpeta
        for (int i = 0; i < numeroArchivos; i++) {
            String nombreArchivo = Integer.toString(i + 1);
            String extension = extensiones[(int) (Math.random() * extensiones.length)];
            String nombreCompletoArchivo = nombreArchivo + extension;

            Path archivo = carpeta.resolve(nombreCompletoArchivo);

            try {
                Files.createFile(archivo);
            } catch (IOException e) {
                System.out.println("Error al crear el archivo: " + e.getMessage());
            }
        }
    }

    public static Map<String, Integer> generarIndice(Path carpeta) {
        Map<String, Integer> indice = new HashMap<>();

        try (DirectoryStream<Path> directorio = Files.newDirectoryStream(carpeta)) {
            for (Path archivo : directorio) {
                if (Files.isRegularFile(archivo)) {
                    String nombreArchivo = archivo.getFileName().toString();
                    int indicePunto = nombreArchivo.lastIndexOf('.');
                    if (indicePunto > 0) {
                        String extension = nombreArchivo.substring(indicePunto);
                        indice.put(extension, indice.getOrDefault(extension, 0) + 1);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al acceder a la carpeta: " + e.getMessage());
        }

        return indice;
    }
}
