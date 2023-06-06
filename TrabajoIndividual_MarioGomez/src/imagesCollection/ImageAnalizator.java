package imagesCollection;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ImageAnalizator {
    private List<ImageInfo> imagenes;

    public ImageAnalizator() {
        this.imagenes = new ArrayList<>();
    }

    // Método para recorrer las carpetas y obtener la información de las imágenes
    public void analizarCarpeta(String path) throws IOException {
        File folder = new File(path);

        // Comprueba si el archivo es una imagen y si es así, recoge su información
        if (folder.isFile() && esImagen(folder)) {
            imagenes.add(new ImageInfo(folder.getAbsolutePath()));
        } else if (folder.isDirectory()) {
            // Si es una carpeta, recorre todos sus archivos y subcarpetas
            for (File file : folder.listFiles()) {
                analizarCarpeta(file.getAbsolutePath());
            }
        }
    }

    // Método para comprobar si un archivo es una imagen
    private boolean esImagen(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg");
    }

    // Método para ordenar las imágenes por fecha de creación
    public void ordenarPorFecha(boolean ascendente) {
        Comparator<ImageInfo> comparator = Comparator.comparing(ImageInfo::getFechaCreacion);
        if (!ascendente) {
            comparator = comparator.reversed();
        }
        imagenes.sort(comparator);
    }

    // Método para filtrar las imágenes por ancho
    public List<ImageInfo> filtrarPorAncho(int anchoMinimo) {
        return imagenes.stream().filter(imagen -> imagen.getAncho() >= anchoMinimo).collect(Collectors.toList());
    }

    // Función para obtener la lista de imágenes
    public List<ImageInfo> getImagenes() {
        return imagenes;
    }
}
