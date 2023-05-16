package imagesCollection;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

// Metadata
import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.common.RationalNumber;
import org.apache.commons.imaging.formats.jpeg.JpegImageMetadata;
import org.apache.commons.imaging.formats.jpeg.exif.ExifRewriter;
import org.apache.commons.imaging.formats.tiff.TiffImageMetadata;
import org.apache.commons.imaging.formats.tiff.constants.ExifTagConstants;
import org.apache.commons.imaging.formats.tiff.constants.GpsTagConstants;
import org.apache.commons.imaging.formats.tiff.write.TiffOutputDirectory;
import org.apache.commons.imaging.formats.tiff.write.TiffOutputSet;
import org.apache.commons.imaging.formats.tiff.write.TiffOutputField;

public class ImageLibrary {

    private final Random random = new Random();

    // Constructor
    public ImageLibrary() {
    }

    // Método para generar la jerarquía de carpetas aleatoria
    public void generateFolderStructure(String rootPath, int maxFoldersPerLevel, int maxDepth, List<String> folderNames) throws IOException {
        generateFolderStructure(rootPath, maxFoldersPerLevel, maxDepth, folderNames, 0);
    }

    private void generateFolderStructure(String parentPath, int maxFoldersPerLevel, int maxDepth, List<String> folderNames, int currentDepth) throws IOException {
        if (currentDepth >= maxDepth) {
            return;
        }

        int numberOfFolders = random.nextInt(maxFoldersPerLevel) + 1;
        for (int i = 0; i < numberOfFolders; i++) {
            String folderName = folderNames.get(random.nextInt(folderNames.size()));
            String newPath = parentPath + File.separator + folderName + "_" + currentDepth + "_" + i;
            Files.createDirectories(Paths.get(newPath));
            generateFolderStructure(newPath, maxFoldersPerLevel, maxDepth, folderNames, currentDepth + 1);
        }
    }

    // Método para generar imágenes aleatorias
    public void createImage(String path, int width, int height, String format, List<Color> colors, int numberOfShapes) throws IOException {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
    
        // Dibuja el fondo blanco
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);
    
        for (int i = 0; i < numberOfShapes; i++) {
            Color color = colors.get(random.nextInt(colors.size()));
            graphics.setColor(color);
    
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
    
        graphics.dispose();
        ImageIO.write(image, format, new File(path));
    }

    // Método para editar los metadatos de una imagen
    public void editImageMetadata(String inputImagePath, String outputImagePath, Date captureDate, double latitude, double longitude) throws IOException {
        File inputFile = new File(inputImagePath);
        File outputFile = new File(outputImagePath);
        JpegImageMetadata metadata = (JpegImageMetadata) Imaging.getMetadata(inputFile);
    
        // Crea un TiffOutputSet basado en los metadatos existentes (si están disponibles)
        TiffOutputSet outputSet = metadata == null ? new TiffOutputSet() : metadata.getExif().getOutputSet();
    
        // Establece la fecha de captura
        TiffOutputDirectory exifDirectory = outputSet.getOrCreateExifDirectory();
        exifDirectory.removeField(ExifTagConstants.EXIF_TAG_DATE_TIME_ORIGINAL);
        exifDirectory.add(ExifTagConstants.EXIF_TAG_DATE_TIME_ORIGINAL, captureDate);
    
        // Establece la posición GPS
        TiffOutputDirectory gpsDirectory = outputSet.getOrCreateGPSDirectory();
        gpsDirectory.removeField(GpsTagConstants.GPS_TAG_GPS_LATITUDE);
        gpsDirectory.removeField(GpsTagConstants.GPS_TAG_GPS_LATITUDE_REF);
        gpsDirectory.removeField(GpsTagConstants.GPS_TAG_GPS_LONGITUDE);
        gpsDirectory.removeField(GpsTagConstants.GPS_TAG_GPS_LONGITUDE_REF);
    
        gpsDirectory.add(GpsTagConstants.GPS_TAG_GPS_LATITUDE, new RationalNumber[]{RationalNumber.valueOf(latitude)});
        gpsDirectory.add(GpsTagConstants.GPS_TAG_GPS_LATITUDE_REF, latitude >= 0 ? "N" : "S");
        gpsDirectory.add(GpsTagConstants.GPS_TAG_GPS_LONGITUDE, new RationalNumber[]{RationalNumber.valueOf(longitude)});
        gpsDirectory.add(GpsTagConstants.GPS_TAG_GPS_LONGITUDE_REF, longitude >= 0 ? "E" : "W");
    
        // Escribe los nuevos metadatos en el archivo de salida
        try (OutputStream os = new FileOutputStream(outputFile)) {
            new ExifRewriter().updateExifMetadataLossless(inputFile, os, outputSet);
        }
    }
    
    
}

