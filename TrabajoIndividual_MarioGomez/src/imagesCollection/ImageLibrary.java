package imagesCollection;

// Importación de las bibliotecas necesarias para operaciones con archivos, generación de números aleatorios y gestión de excepciones.
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Random;

// Bibliotecas para la creación de imágenes
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
public class ImageLibrary {

    // Creamos una instancia Random para generar números aleatorios
    private final Random random = new Random();

    // Constructor
    public ImageLibrary() {
    }

    // Método para generar la jerarquía de carpetas con un número aleatorio de carpetas en cada nivel
    public void generateFolderStructure(String rootPath, int maxFoldersPerLevel, int maxDepth, List<String> folderNames) throws IOException {
        // Creo la carpeta raíz
        generateFolderStructure(rootPath, maxFoldersPerLevel, maxDepth, folderNames, 0);
    }

    // Método que simula el trabajo real de crear una jerarquía de carpetas
    private void generateFolderStructure(String parentPath, int maxFoldersPerLevel, int maxDepth, List<String> folderNames, int currentDepth) throws IOException {
        // Si se alcanza la profundidad máxima, no se crean más carpetas
        if (currentDepth >= maxDepth) {
            return;
        }

        // Decido aleatoriamente cuántas carpetas se crearán en este nivel
        int numberOfFolders = random.nextInt(maxFoldersPerLevel) + 1;

        for (int i = 0; i < numberOfFolders; i++) {
            // Elijo el nombre que le voy a poner a las carpetas
            String folderName = folderNames.get(random.nextInt(folderNames.size()));
            // Genero la ruta para la nueva carpeta
            String newPath = parentPath + File.separator + folderName + "_" + currentDepth + "_" + i;
            // Creo la carpeta
            Files.createDirectories(Paths.get(newPath));
            // Llamo recursivamente al método para crear las carpetas de los niveles inferiores
            generateFolderStructure(newPath, maxFoldersPerLevel, maxDepth, folderNames, currentDepth + 1);
        }
    }

    // Método para generar imágenes aleatorias dado un número determinado de formas
    public void createImage(String path, int width, int height, String format, List<Color> colors, int numberOfShapes) throws IOException {
        // Creo un nuevo contexto de imagen y gráficos
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
    
        // Dibuja el fondo blanco
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);
    
        // Dibuja el número especificado de formas
        for (int i = 0; i < numberOfShapes; i++) {
            // Elijo un color aleatorio
            Color color = colors.get(random.nextInt(colors.size()));
            graphics.setColor(color);
    
            // Elijo aleatoriamente si dibujaré un rectángulo o un círculo
            int shapeType = random.nextInt(2);

            if (shapeType == 0) {
                // Dibuja un rectángulo
                double x = random.nextDouble() * width;
                double y = random.nextDouble() * height;
                double w = random.nextDouble() * (width - x);
                double h = random.nextDouble() * (height - y);
                Rectangle2D rectangle = new Rectangle2D.Double(x, y, w, h);
                graphics.draw(rectangle);
            } else {
                // Dibuja un círculo
                double x = random.nextDouble() * width;
                double y = random.nextDouble() * height;
                double d = random.nextDouble() * Math.min(width - x, height - y);
                Ellipse2D circle = new Ellipse2D.Double(x, y, d, d);
                graphics.draw(circle);
            }
        }
    
        // Guarda la imagen en el archivo
        graphics.dispose();
        ImageIO.write(image, format, new File(path));
    }
}
