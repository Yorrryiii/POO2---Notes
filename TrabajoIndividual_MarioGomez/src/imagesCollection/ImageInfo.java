package imagesCollection;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.imageio.ImageIO;

public class ImageInfo {
    private String path;
    private LocalDateTime fechaCreacion;
    private int ancho;
    private int alto;

    public ImageInfo(String path) throws IOException {
        this.path = path;

        // Leemps la imagen y obtiene su ancho y alto
        BufferedImage image = ImageIO.read(new File(path));
        this.ancho = image.getWidth();
        this.alto = image.getHeight();

        // Obtenemos la fecha de creación del archivo (esto puede no ser preciso dependiendo del sistema operativo)
        File file = new File(path);
        this.fechaCreacion = convertMillisToLocalDateTime(file.lastModified());
    }

    public String getPath() {
        return path;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    // Convierte milisegundos a LocalDateTime
    private LocalDateTime convertMillisToLocalDateTime(long millis) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault());
    }
}
