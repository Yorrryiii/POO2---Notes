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

// Metadata
// import org.apache.commons.imaging.Imaging;
// import org.apache.commons.imaging.common.RationalNumber;
// import org.apache.commons.imaging.formats.jpeg.JpegImageMetadata;
// import org.apache.commons.imaging.formats.jpeg.exif.ExifRewriter;
// import org.apache.commons.imaging.formats.tiff.TiffImageMetadata;
// import org.apache.commons.imaging.formats.tiff.constants.ExifTagConstants;
// import org.apache.commons.imaging.formats.tiff.constants.GpsTagConstants;
// import org.apache.commons.imaging.formats.tiff.write.TiffOutputDirectory;
// import org.apache.commons.imaging.formats.tiff.write.TiffOutputSet;
// import org.apache.commons.imaging.formats.tiff.write.TiffOutputField;


public class ImageLibrary {

    // Creamos una instancia Random para generar números aleatorios
    private final Random random = new Random();

    // Constructor
    public ImageLibrary() {
    }

    // Método para generar la jerarquía de carpetas con un número aleatorio de carpetas en cada nivel
    public void generateFolderStructure(String rootPath, int maxFoldersPerLevel, int maxDepth, List<String> folderNames) throws IOException {
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

    // Método para editar los metadatos de una imagen
    // public void editImageMetadata(String inputImagePath, String outputImagePath, String[] captureDate, double latitude, double longitude) throws IOException {
    //     File inputFile = new File(inputImagePath);
    //     File outputFile = new File(outputImagePath);
    //     JpegImageMetadata metadata = (JpegImageMetadata) Imaging.getMetadata(inputFile);
    
    //     // Crea un TiffOutputSet basado en los metadatos existentes (si están disponibles)
    //     TiffOutputSet outputSet = metadata == null ? new TiffOutputSet() : metadata.getExif().getOutputSet();
    
    //     // Establece la fecha de captura
    //     TiffOutputDirectory exifDirectory = outputSet.getOrCreateExifDirectory();
    //     exifDirectory.removeField(ExifTagConstants.EXIF_TAG_DATE_TIME_ORIGINAL);
    //     exifDirectory.add(ExifTagConstants.EXIF_TAG_DATE_TIME_ORIGINAL, captureDate);
    
    //     // Establece la posición GPS
    //     TiffOutputDirectory gpsDirectory = outputSet.getOrCreateGPSDirectory();
    //     gpsDirectory.removeField(GpsTagConstants.GPS_TAG_GPS_LATITUDE);
    //     gpsDirectory.removeField(GpsTagConstants.GPS_TAG_GPS_LATITUDE_REF);
    //     gpsDirectory.removeField(GpsTagConstants.GPS_TAG_GPS_LONGITUDE);
    //     gpsDirectory.removeField(GpsTagConstants.GPS_TAG_GPS_LONGITUDE_REF);
    
    //     gpsDirectory.add(GpsTagConstants.GPS_TAG_GPS_LATITUDE, new RationalNumber[]{RationalNumber.valueOf(latitude)});
    //     gpsDirectory.add(GpsTagConstants.GPS_TAG_GPS_LATITUDE_REF, latitude >= 0 ? "N" : "S");
    //     gpsDirectory.add(GpsTagConstants.GPS_TAG_GPS_LONGITUDE, new RationalNumber[]{RationalNumber.valueOf(longitude)});
    //     gpsDirectory.add(GpsTagConstants.GPS_TAG_GPS_LONGITUDE_REF, longitude >= 0 ? "E" : "W");
    
    //     // Escribe los nuevos metadatos en el archivo de salida
    //     try (OutputStream os = new FileOutputStream(outputFile)) {
    //         new ExifRewriter().updateExifMetadataLossless(inputFile, os, outputSet);
    //     }
    // }
}
