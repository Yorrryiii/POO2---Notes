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

    private static void createImagesInFolder(Path folderPath, ImageLibrary imageLibrary, int numberOfImages, List<Color> colors, int numberOfShapes) throws IOException {
        for (int i = 0; i < numberOfImages; i++) {
            String imageName = "image_" + i + ".png";
            String imagePath = folderPath.resolve(imageName).toString();
            imageLibrary.createImage(imagePath, 300, 300, "png", colors, numberOfShapes);
        }
    }

    public static void main(String[] args) {
        ImageLibrary imageLibrary = new ImageLibrary();
        List<String> folderNames = new ArrayList<>();
        folderNames.add("Vacaciones");
        folderNames.add("Familia");
        folderNames.add("Amigos");
        folderNames.add("Trabajo");
        folderNames.add("Eventos");

        List<Color> colors = Arrays.asList(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.ORANGE);

        try {
            imageLibrary.generateFolderStructure("images", 3, 3, folderNames);
            Files.walk(Paths.get("images"))
                .filter(Files::isDirectory)
                .forEach(folderPath -> {
                    try {
                        createImagesInFolder(folderPath, imageLibrary, 5, colors, 10);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

