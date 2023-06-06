// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Random;
// import java.util.Scanner;

// import java.awt.Color;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;
// import java.util.Arrays;

// import imagesCollection.ImageLibrary;

// public class Main {

//     // Método para crear un número determinado de imágenes en una carpeta específica
//     private static void createImagesInFolder(Path folderPath, ImageLibrary imageLibrary, int numberOfImages, List<Color> colors, int numberOfShapes, Random random, List<String> formats, int width, int height) throws IOException {
//         // Bucle para crear las imágenes
//         for (int i = 0; i < numberOfImages; i++) {
//             // Selecciona un formato de imagen aleatoriamente
//             String format = formats.get(random.nextInt(formats.size()));
//             // Se construye el nombre de la imagen con el formato "image_i.format"
//             String imageName = "image_" + i + "." + format;
//             // Se construye la ruta completa de la imagen
//             String imagePath = folderPath.resolve(imageName).toString();
//             // Se llama al método para crear la imagen
//             imageLibrary.createImage(imagePath, width, height, format, colors, numberOfShapes);
//         }
//     }

//     public static void main(String[] args) {
//         Random random = new Random();

//         int numFolders = random.nextInt(5) + 1; // Generar un número aleatorio entre 1 y 5 para las carpetas

//         // Creación de una instancia de ImageLibrary
//         ImageLibrary imageLibrary = new ImageLibrary();

//         // Creación de una lista de nombres de carpetas
//         List<String> folderNames = new ArrayList<>();
//         for (int i = 0; i < numFolders; i++) {
//             folderNames.add("Carpeta" + random.nextInt(1000)); // Generar un número aleatorio entre 0 y 1000 para el nombre de la carpeta
//         }

//         // Creación de una lista de colores
//         List<Color> colors = Arrays.asList(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.ORANGE);

//         // Creación de una lista de formatos de imagen
//         List<String> formats = Arrays.asList("png", "jpeg", "jpg");

//         Scanner scanner = new Scanner(System.in);

//         System.out.println("Enter image width:");
//         int width = scanner.nextInt();

//         System.out.println("Enter image height:");
//         int height = scanner.nextInt();

//         scanner.close();

//         int numSubfolders = 5;
//         try {
//             // Generación de la estructura de carpetas
//             for(String folderName : folderNames) {
//                 imageLibrary.generateFolderStructure("images/"+folderName, numSubfolders--, 1, Arrays.asList(folderName));
//             }

//             // Recorrido de todas las carpetas generadas
//             Files.walk(Paths.get("images"))
//                 .filter(Files::isDirectory)
//                 .forEach(folderPath -> {
//                     try {
//                         // Creación de imágenes en cada carpeta
//                         int numImages = random.nextInt(10) + 1; // Generar un número aleatorio entre 1 y 10 para las imágenes
//                         createImagesInFolder(folderPath, imageLibrary, numImages, colors, random.nextInt(20) + 1, random, formats, width, height);
//                     } catch (IOException e) {
//                         // Gestión de posibles errores
//                         e.printStackTrace();
//                     }
//                 });
//         } catch (IOException e) {
//             // Gestión de posibles errores
//             e.printStackTrace();
//         }
//     }
// }


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.SwingUtilities;

import java.awt.Color;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import imagesCollection.ImageAnalizator;
import imagesCollection.ImageInfo;
import imagesCollection.ImageLibrary;

public class Main {

    // Método para crear un número determinado de imágenes en una carpeta específica
    private static void createImagesInFolder(Path folderPath, ImageLibrary imageLibrary, int numberOfImages, List<Color> colors, int numberOfShapes, Random random, List<String> formats, int width, int height) throws IOException {
        // Bucle para crear las imágenes
        for (int i = 0; i < numberOfImages; i++) {
            // Selecciona un formato de imagen aleatoriamente
            String format = formats.get(random.nextInt(formats.size()));
            // Se construye el nombre de la imagen con el formato "image_i.format"
            String imageName = "image_" + i + "." + format;
            // Se construye la ruta completa de la imagen
            String imagePath = folderPath.resolve(imageName).toString();
            // Se llama al método para crear la imagen
            imageLibrary.createImage(imagePath, width, height, format, colors, numberOfShapes);
        }
    }

    public static void main(String[] args) {
        Random random = new Random();

        int numFolders = random.nextInt(7) + 2; // Generar un número aleatorio entre 2 y 8 para las carpetas

        // Creación de una instancia de ImageLibrary
        ImageLibrary imageLibrary = new ImageLibrary();

        // Creación de una lista de nombres de carpetas
        List<String> folderNames = new ArrayList<>();
        for (int i = 0; i < numFolders; i++) {
            folderNames.add("Carpeta" + random.nextInt(1000)); // Generar un número aleatorio entre 0 y 1000 para el nombre de la carpeta
        }

        // Creación de una lista de colores
        List<Color> colors = Arrays.asList(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.ORANGE);

        // Creación de una lista de formatos de imagen
        List<String> formats = Arrays.asList("png", "jpeg", "jpg");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce la anchura de las imagenes:");
        int width = scanner.nextInt();

        System.out.println("Introduce la altura de las imagenes:");
        int height = scanner.nextInt();

        scanner.close();

        int numSubfolders = 5;
        try {
            // Generación de la estructura de carpetas
            for(String folderName : folderNames) {
                imageLibrary.generateFolderStructure("images/"+folderName, numSubfolders--, 1, Arrays.asList(folderName));
            }

            // Recorrido de todas las carpetas generadas
            Files.walk(Paths.get("images"))
                .filter(Files::isDirectory)
                .forEach(folderPath -> {
                    try {
                        // Creación de imágenes en cada carpeta
                        int numImages = random.nextInt(10) + 1; // Generar un número aleatorio entre 1 y 10 para las imágenes
                        createImagesInFolder(folderPath, imageLibrary, numImages, colors, random.nextInt(20) + 1, random, formats, width, height);
                    } catch (IOException e) {
                        // Gestión de posibles errores
                        e.printStackTrace();
                    }
                });
        } catch (IOException e) {
            // Gestión de posibles errores
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            App app = new App();
            app.setVisible(true);
        });
    }
}