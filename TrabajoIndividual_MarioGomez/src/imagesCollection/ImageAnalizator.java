package imagesCollection;

// Importación de las bibliotecas necesarias para operaciones con archivos, generación de números aleatorios y gestión de excepciones.
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ImageAnalizator {
    private List<ImageInfo> imagenes;

    // Constructor
    public ImageAnalizator() {
        this.imagenes = new ArrayList<>();
    }

    // Método para recorrer las carpetas y obtener la información de las imágenes
    public void analizarCarpeta(String path) throws IOException {
        // Crea un objeto File con la ruta de la carpeta
        File folder = new File(path);

        // Comprueba si el archivo es una imagen y si es así, recoge su información
        if (folder.isFile() && esImagen(folder)) {
            // Añade la imagen a la lista
            imagenes.add(new ImageInfo(folder.getAbsolutePath()));
        } else if (folder.isDirectory()) {
            // Si es una carpeta, recorre todos sus archivos y subcarpetas
            for (File file : folder.listFiles()) {
                // Llama recursivamente al método para analizar la carpeta
                analizarCarpeta(file.getAbsolutePath());
            }
        }
    }

    // Método para comprobar si un archivo es una imagen
    private boolean esImagen(File file) {
        // Obtiene el nombre del archivo y lo convierte a minúsculas
        String name = file.getName().toLowerCase();
        // Comprueba si el nombre del archivo termina con ".png", ".jpg" o ".jpeg"
        return name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg");
    }

    // Método para ordenar las imágenes por fecha de creación
    public void ordenarPorFecha(boolean ascendente) {
        // Se crea un comparador para comparar las fechas de creación
        Comparator<ImageInfo> comparator = Comparator.comparing(ImageInfo::getFechaCreacion);
        // Si se quiere ordenar de forma descendente, se invierte el comparador
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
