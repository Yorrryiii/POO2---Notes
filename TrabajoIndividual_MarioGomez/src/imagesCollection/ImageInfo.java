package imagesCollection;

// Importación de las bibliotecas necesarias para operaciones con archivos, generación de números aleatorios y gestión de excepciones.
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

// Bibliotecas para la creación de imágenes
import javax.imageio.ImageIO;

public class ImageInfo {
    // Atributos
    private String path;
    private LocalDateTime fechaCreacion;
    private int ancho;
    private int alto;

    public ImageInfo(String path) throws IOException {
        this.path = path;

        // Leemos la imagen y obtiene su ancho y alto
        BufferedImage image = ImageIO.read(new File(path));
        this.ancho = image.getWidth();
        this.alto = image.getHeight();

        // Obtenemos la fecha de creación del archivo 
        File file = new File(path);
        // Convertimos los milisegundos a LocalDateTime
        this.fechaCreacion = convertMillisToLocalDateTime(file.lastModified());
    }

    // Método para obtener la ruta
    public String getPath() {
        return path;
    }

    // Método para obtener la fecha de creación
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    // Método para obtener el ancho
    public int getAncho() {
        return ancho;
    }

    // Método para obtener el alto
    public int getAlto() {
        return alto;
    }

    // Convierte milisegundos a LocalDateTime
    private LocalDateTime convertMillisToLocalDateTime(long millis) {
        // Obtenemos la zona horaria del sistema
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault());
    }
}
