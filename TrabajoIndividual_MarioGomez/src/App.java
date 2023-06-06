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
    // Tabla para mostrar los detalles de las imagenes
    private JTable table;
    // Modelo de la tabla
    private DefaultTableModel tableModel;
    // Etiqueta para mostrar la imagen seleccionada en grande
    private JLabel imageLabel;
    // Panel para mostrar las miniaturas de las imagenes
    private JPanel thumbnailPanel;
    // Scroll pane para el panel de miniaturas (Scroll horizontal)
    private JScrollPane thumbnailScrollPane;

    // Lista de imagenes
    private List<ImageInfo> imageList;
    // Analizador de imagenes
    private ImageAnalizator imageAnalyzer;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App app = new App();
            app.setVisible(true);
        });
    }

    public App() {
        // Título de la ventana
        setTitle("POO 2 - Trabajo Individual - Mario Gómez (alu.124956@usj.es)");
        // Cerrar la ventana al pulsar la X
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Tamaño de la ventana
        setSize(800, 600);
        // Centrar la ventana
        setLocationRelativeTo(null);

        // Crear la tabla
        tableModel = new DefaultTableModel(new Object[]{"Ruta", "Fecha de creación", "Anchura", "Altura"}, 0);
        // Creamos la tabla con los parametros que le hemos pasado a tableModel
        table = new JTable(tableModel);
        // Generamos el Scroll vertical para la tabla
        JScrollPane scrollPane = new JScrollPane(table);
        // Añadimos el listener para cuando se seleccione una imagen
        table.getSelectionModel().addListSelectionListener(new TableSelectionListener());

        // Crear la etiqueta de la imagen
        imageLabel = new JLabel();
        // Centrar la imagen en la etiqueta
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Crear el panel de miniaturas
        thumbnailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        // Generamos el Scroll horizontal para las miniaturas
        thumbnailScrollPane = new JScrollPane(thumbnailPanel);
        // Establecemos la política (SIEMPRE) de Scroll horizontal
        thumbnailScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        // Crear los botones
        JButton openButton = new JButton("Abrir carpeta");
        JButton analyzeButton = new JButton("Reset selección");
        // Añadir los listeners a los botones (Abrir carpeta)
        openButton.addActionListener(new OpenButtonListener());
        // Añadir los listeners a los botones (Reset selección)
        analyzeButton.addActionListener(new AnalyzeButtonListener());

        // Crear el panel de botones
        JPanel buttonPanel = new JPanel();
        // Añadimos los botones al panel
        buttonPanel.add(openButton);
        buttonPanel.add(analyzeButton);

        // Crear el panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        // Añadir los paneles al panel principal
        mainPanel.add(scrollPane, BorderLayout.WEST);
        mainPanel.add(imageLabel, BorderLayout.CENTER);
        mainPanel.add(thumbnailScrollPane, BorderLayout.SOUTH);
        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        // Agregar el panel principal al frame
        add(mainPanel);
    }

    private void openFolder() {
        // Creamos un JFileChooser para seleccionar la carpeta
        JFileChooser fileChooser = new JFileChooser();
        // Establecemos que solo se puedan seleccionar carpetas
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        // Mostramos el JFileChooser
        int result = fileChooser.showOpenDialog(this);
        // Si se ha seleccionado una carpeta
        if (result == JFileChooser.APPROVE_OPTION) {
            // Obtenemos la carpeta seleccionada
            File selectedFolder = fileChooser.getSelectedFile();
            // Creamos una instancia de ImageAnalizator
            imageAnalyzer = new ImageAnalizator();
            // Creamos una lista de imagenes
            imageList = new ArrayList<>();
            // Analizamos la carpeta seleccionada
            try {
                // Obtenemos la lista de imagenes
                imageAnalyzer.analizarCarpeta(selectedFolder.getAbsolutePath());
                // Añadimos las imagenes a la lista
                imageList.addAll(imageAnalyzer.getImagenes());
                // Actualizamos la tabla y las miniaturas
                updateTable();
                // Actualizamos la tabla y las miniaturas
                updateThumbnails();
            // Si se produce una excepción 
            } catch (IOException e) {
                // Imprimimos el error
                e.printStackTrace();
            }
        }
    }

    // Método para ordenar las imagenes por fecha de creación
    private void analyzeImages() {
        // Si el analizador de imagenes no es nulo
        if (imageAnalyzer != null) {
            // Ordenamos las imagenes por fecha de creación
            imageAnalyzer.ordenarPorFecha(true);
            // Actualizamos la lista de imagenes
            imageList = imageAnalyzer.getImagenes();
            // Actualizamos la tabla y las miniaturas
            updateTable();
            updateThumbnails();
        }
    }

    // Método para actualizar la tabla
    private void updateTable() {
        // Eliminamos todas las filas de la tabla
        tableModel.setRowCount(0);
        // Recorremos la lista de imagenes
        for (ImageInfo imageInfo : imageList) {
            // Obtenemos los datos de la imagen
            String path = imageInfo.getPath();
            // Obtenemos la fecha de creación de la imagen
            LocalDateTime creationDate = imageInfo.getFechaCreacion();
            // Obtenemos la anchura de la imagen
            int width = imageInfo.getAncho();
            // Obtenemos la altura de la imagen
            int height = imageInfo.getAlto();
            // Añadimos una nueva fila a la tabla con los datos de la imagen
            tableModel.addRow(new Object[]{path, creationDate, width, height});
        }
    }

    // Método para actualizar las miniaturas
    private void updateThumbnails() {
        // Eliminamos todas las miniaturas
        thumbnailPanel.removeAll();
        // Recorremos la lista de imagenes
        for (ImageInfo imageInfo : imageList) {
            // Obtenemos la miniatura de la imagen
            ImageIcon thumbnail = new ImageIcon(imageInfo.getPath());
            // Escalamos la miniatura a 100x100
            Image scaledImage = thumbnail.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            // Creamos una nueva miniatura con el tamaño escalado
            ImageIcon scaledThumbnail = new ImageIcon(scaledImage);
            // Creamos una etiqueta para mostrar la miniatura
            JLabel thumbnailLabel = new JLabel(scaledThumbnail);
            // Añadimos la miniatura al panel
            thumbnailPanel.add(thumbnailLabel);
        }
        // Actualizamos el panel de miniaturas
        thumbnailPanel.revalidate();
        // Repintamos el panel de miniaturas
        thumbnailPanel.repaint();
    }

    // Listener para cuando se selecciona una imagen de la tabla
    private class TableSelectionListener implements ListSelectionListener {
        // Método que se ejecuta cuando se selecciona una imagen de la tabla
        @Override
        public void valueChanged(ListSelectionEvent e) {
            // Si la selección no está cambiando
            if (!e.getValueIsAdjusting()) {
                // Obtenemos la fila seleccionada
                int selectedRow = table.getSelectedRow();
                // Si la fila seleccionada está dentro del rango de la lista de imagenes
                if (selectedRow >= 0 && selectedRow < imageList.size()) {
                    // Obtenemos la imagen seleccionada
                    ImageInfo imageInfo = imageList.get(selectedRow);
                    // Obtenemos la ruta de la imagen
                    String path = imageInfo.getPath();
                    // Creamos un ImageIcon con la imagen seleccionada
                    ImageIcon imageIcon = new ImageIcon(path);
                    // Escalamos la imagen para que se ajuste al tamaño de la etiqueta
                    Image scaledImage = imageIcon.getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
                    // Creamos un nuevo ImageIcon con la imagen escalada
                    imageLabel.setIcon(new ImageIcon(scaledImage));
                }
            }
        }
    }

    // Listener para el botón de abrir carpeta
    private class OpenButtonListener implements ActionListener {
        // Método que se ejecuta cuando se pulsa el botón de abrir carpeta
        @Override
        public void actionPerformed(ActionEvent e) {
            // Abrimos la carpeta
            openFolder();
        }
    }

    // Listener para el botón de reset selección
    private class AnalyzeButtonListener implements ActionListener {
        // Método que se ejecuta cuando se pulsa el botón de reset selección
        @Override
        public void actionPerformed(ActionEvent e) {
            // Ordenamos las imagenes por fecha de creación
            analyzeImages();
        }
    }
}