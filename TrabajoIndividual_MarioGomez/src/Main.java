import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.awt.Color;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import imagesCollection.ImageLibrary;

public class Main {

    // Método para crear un número determinado de imágenes en una carpeta específica
    private static void createImagesInFolder(Path folderPath, ImageLibrary imageLibrary, int numberOfImages, List<Color> colors, int numberOfShapes) throws IOException {
        // Bucle para crear las imágenes
        for (int i = 0; i < numberOfImages; i++) {
            // Se construye el nombre de la imagen con el formato "image_i.png"
            String imageName = "image_" + i + ".png";
            // Se construye la ruta completa de la imagen
            String imagePath = folderPath.resolve(imageName).toString();
            // Se llama al método para crear la imagen
            imageLibrary.createImage(imagePath, 300, 300, "png", colors, numberOfShapes);
        }
    }

    public static void main(String[] args) {
        // Creación de una instancia de ImageLibrary
        ImageLibrary imageLibrary = new ImageLibrary();

        // Creación de una lista de nombres de carpetas
        List<String> folderNames = new ArrayList<>();
        folderNames.add("Vacaciones");
        folderNames.add("Familia");
        folderNames.add("Amigos");
        folderNames.add("Trabajo");
        folderNames.add("Eventos");

        // Creación de una lista de colores
        List<Color> colors = Arrays.asList(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.ORANGE);

        try {
            // Generación de la estructura de carpetas
            imageLibrary.generateFolderStructure("images", 3, 3, folderNames);

            // Recorrido de todas las carpetas generadas
            Files.walk(Paths.get("images"))
                .filter(Files::isDirectory)
                .forEach(folderPath -> {
                    try {
                        // Creación de imágenes en cada carpeta
                        createImagesInFolder(folderPath, imageLibrary, 5, colors, 10);
                    } catch (IOException e) {
                        // Gestión de posibles errores
                        e.printStackTrace();
                    }
                });
        } catch (IOException e) {
            // Gestión de posibles errores
            e.printStackTrace();
        }
    }
}
