import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import imagesCollection.ImageAnalizator;
import imagesCollection.ImageInfo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class App extends JFrame {
    private JTable table; // Tabla para mostrar la información de las imágenes
    private DefaultTableModel tableModel; // Modelo de la tabla
    private JLabel imageLabel; // Etiqueta para mostrar la imagen seleccionada

    private List<ImageInfo> imageList; // Lista de imágenes analizadas
    private ImageAnalizator imageAnalyzer; // Analizador de imágenes

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App app = new App();
            app.setVisible(true);
        });
    }

    public App() {
        setTitle("Trabajo Individual (POO II): Mario Gómez (alu.124956@usj.es)"); // Título de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar la aplicación al cerrar la ventana
        setSize(800, 600); // Tamaño de la ventana
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        // Crear la tabla
        tableModel = new DefaultTableModel(new Object[]{"Ruta", "Fecha de creación", "Anchura", "Altura"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table); // Agregar la tabla a un panel de desplazamiento
        table.getSelectionModel().addListSelectionListener(new TableSelectionListener()); // Agregar un escucha de selección a la tabla

        // Crear la etiqueta de la imagen
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER); // Alinear la imagen al centro de la etiqueta

        // Crear los botones
        JButton openButton = new JButton("Abrir carpeta"); // Botón para abrir una carpeta
        JButton analyzeButton = new JButton("Ver solo imágenes"); // Botón para ver únicamente las imagenes en vez de las carpetas
        openButton.addActionListener(new OpenButtonListener()); // Agregar un escucha de eventos al botón "Abrir carpeta"
        analyzeButton.addActionListener(new AnalyzeButtonListener()); // Agregar un escucha de eventos al botón "Ver solo imagenes"

        // Crear el panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openButton);
        buttonPanel.add(analyzeButton);

        // Crear el panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.WEST); // Agregar el panel de desplazamiento a la izquierda
        mainPanel.add(imageLabel, BorderLayout.CENTER); // Agregar la etiqueta de imagen al centro
        mainPanel.add(buttonPanel, BorderLayout.SOUTH); // Agregar el panel de botones en la parte inferior

        // Agregar el panel principal al frame
        add(mainPanel);
    }

    // Método para abrir una carpeta y analizar las imágenes en ella
    private void openFolder() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Permitir seleccionar solo carpetas
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = fileChooser.getSelectedFile(); // Carpeta seleccionada
            imageAnalyzer = new ImageAnalizator(); // Crear una instancia del analizador de imágenes
            imageList = new ArrayList<>(); // Crear una nueva lista de imágenes
            try {
                imageAnalyzer.analizarCarpeta(selectedFolder.getAbsolutePath()); // Analizar la carpeta seleccionada
                imageList.addAll(imageAnalyzer.getImagenes()); // Obtener la lista de imágenes analizadas
                updateTable(); // Actualizar la tabla con la información de las imágenes

                // Mostrar las subcarpetas dentro de la carpeta seleccionada en la tabla
                File[] subFolders = selectedFolder.listFiles(File::isDirectory);
                if (subFolders != null) {
                    for (File folder : subFolders) {
                        tableModel.addRow(new Object[]{folder.getAbsolutePath(), "", "", ""}); // Agregar una fila vacía con la ruta de la subcarpeta
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para analizar las imágenes y ordenarlas por fecha de creación
    private void analyzeImages() {
        if (imageAnalyzer != null) {
            imageAnalyzer.ordenarPorFecha(true); // Ordenar las imágenes por fecha de creación
            imageList = imageAnalyzer.getImagenes(); // Obtener la lista de imágenes ordenadas
            updateTable(); // Actualizar la tabla con la información de las imágenes

            // Obtener la ruta de la carpeta actualmente seleccionada en la tabla
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0 && selectedRow < imageList.size()) {
                String folderPath = (String) table.getValueAt(selectedRow, 0);
                if (folderPath != null && !folderPath.isEmpty()) {
                    File folder = new File(folderPath);
                    if (folder.exists() && folder.isDirectory()) {
                        openFolderInSystemExplorer(folder); // Abrir la carpeta seleccionada en el explorador del sistema
                    }
                }
            }
        }
    }

    // Método para actualizar la tabla con la información de las imágenes
    private void updateTable() {
        tableModel.setRowCount(0); // Borrar todas las filas existentes en la tabla
        for (ImageInfo imageInfo : imageList) {
            String path = imageInfo.getPath();
            LocalDateTime creationDate = imageInfo.getFechaCreacion();
            int width = imageInfo.getAncho();
            int height = imageInfo.getAlto();
            tableModel.addRow(new Object[]{path, creationDate, width, height}); // Agregar una nueva fila con la información de la imagen
        }
    }

    // Escucha de selección de la tabla
    private class TableSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0 && selectedRow < imageList.size()) {
                    ImageInfo imageInfo = imageList.get(selectedRow);
                    String path = imageInfo.getPath();
                    ImageIcon imageIcon = new ImageIcon(path);
                    Image scaledImage = imageIcon.getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
                    imageLabel.setIcon(new ImageIcon(scaledImage)); // Mostrar la imagen seleccionada en la etiqueta
                }
            }
        }
    }

    // Escucha del botón "Abrir carpeta"
    private class OpenButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            openFolder();
        }
    }

    // Escucha del botón "Reset selección"
    private class AnalyzeButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            analyzeImages();
        }
    }

    // Método para abrir una carpeta en el explorador del sistema
    private void openFolderInSystemExplorer(File folder) {
        try {
            Desktop.getDesktop().open(folder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
