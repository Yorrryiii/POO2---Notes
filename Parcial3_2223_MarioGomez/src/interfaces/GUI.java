package interfaces;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import huerto.Agua;
import huerto.Cliente;
import huerto.Cultivo;
import huerto.HuertoHelper;
import huerto.HuertoUrbano;
import huerto.Parcela;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

public class GUI {

    private JFrame frame;
    private JLabel statusLabel;
    private JTree tree;
    private JTable table;

    private MyDataModel dataModel;

    public GUI(HuertoUrbano huerto) {
        frame = new JFrame("Huerto Urbano");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        statusLabel = new JLabel("Status: ");
        frame.add(statusLabel, BorderLayout.SOUTH);

        dataModel = new MyDataModel(huerto);
        tree = new JTree(dataModel);
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                TreePath path = e.getPath();
                Object lastPathComponent = path.getLastPathComponent();
                if (lastPathComponent instanceof Parcela) {
                    Parcela selectedParcela = (Parcela) lastPathComponent;
                    Cliente cliente = selectedParcela.getCliente();
                    int clienteId = cliente.getID();
                    int numCultivos = selectedParcela.getCultivos().size();
                    setStatusMessage("Cliente ID: " + clienteId + ", Número de cultivos: " + numCultivos);
                    dataModel.setParcelaSeleccionada(selectedParcela);
                    table.setModel(dataModel);
                } else {
                    setStatusMessage("");
                    dataModel.setParcelaSeleccionada(null);
                    table.setModel(dataModel);
                }
            }
        });

        JScrollPane treeScrollPane = new JScrollPane(tree);

        table = new JTable(dataModel);
        JScrollPane tableScrollPane = new JScrollPane(table);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeScrollPane, tableScrollPane);
        splitPane.setDividerLocation(0.3);

        frame.add(splitPane, BorderLayout.CENTER);

        createMenuBar();

        frame.setVisible(true);
    }

    public void setStatusMessage(String message) {
        statusLabel.setText("Status: " + message);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu optionsMenu = new JMenu("Options");
        JMenuItem resetItem = new JMenuItem("Reset");
        JMenuItem addCultivoItem = new JMenuItem("AddCultivo");

        resetItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HuertoUrbano newHuerto = HuertoHelper.createHuertoExamen();
                dataModel.setHuertoSeleccionado(newHuerto);
                tree.setModel(new MyDataModel(newHuerto));
                table.setModel(new MyDataModel(newHuerto));
                setStatusMessage("Huerto reiniciado");
            }
        });

        addCultivoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddCultivoDialog();
            }
        });

        optionsMenu.add(resetItem);
        optionsMenu.add(addCultivoItem);
        menuBar.add(optionsMenu);

        frame.setJMenuBar(menuBar);
    }

    private void showAddCultivoDialog() {
        JDialog dialog = new JDialog(frame, "Add Cultivo", true);
        dialog.setSize(300, 200);
        dialog.setLayout(new BorderLayout());
    
        JPanel formPanel = new JPanel(new GridLayout(4, 2));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
        JLabel nameLabel = new JLabel("Nombre:");
        JTextField nameField = new JTextField();
    
        JLabel aguaLabel = new JLabel("Agua:");
        ButtonGroup aguaGroup = new ButtonGroup();
        JRadioButton altaRadioButton = new JRadioButton("Alta");
        JRadioButton mediaRadioButton = new JRadioButton("Media");
        JRadioButton bajaRadioButton = new JRadioButton("Baja");
        aguaGroup.add(altaRadioButton);
        aguaGroup.add(mediaRadioButton);
        aguaGroup.add(bajaRadioButton);
    
        JLabel plantasLabel = new JLabel("Número de Plantas:");
        JSlider plantasSlider = new JSlider(0, 100, 0);
        plantasSlider.setMajorTickSpacing(10);
        plantasSlider.setPaintTicks(true);
        plantasSlider.setSnapToTicks(true);
    
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(aguaLabel);
        formPanel.add(altaRadioButton);
        formPanel.add(new JLabel());
        formPanel.add(mediaRadioButton);
        formPanel.add(new JLabel());
        formPanel.add(bajaRadioButton);
        formPanel.add(plantasLabel);
        formPanel.add(plantasSlider);
    
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
        JButton acceptButton = new JButton("Aceptar");
        JButton cancelButton = new JButton("Cancelar");
    
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nameField.getText();
                Agua agua = null;
                if (altaRadioButton.isSelected()) {
                    agua = Agua.alta;
                } else if (mediaRadioButton.isSelected()) {
                    agua = Agua.media;
                } else if (bajaRadioButton.isSelected()) {
                    agua = Agua.baja;
                }
                int numPlantas = plantasSlider.getValue();
    
                if (nombre.isEmpty() || agua == null) {
                    JOptionPane.showMessageDialog(dialog, "Por favor, complete todos los campos.");
                } else {
                    Cultivo cultivo = new Cultivo(nombre, agua, numPlantas);
                    HuertoUrbano huerto = dataModel.getHuertoSeleccionado();
                    if (huerto != null) {
                        List<Parcela> parcelas = huerto.getParcelas();
                        if (!parcelas.isEmpty()) {
                            Random rand = new Random();
                            Parcela randomParcela = parcelas.get(rand.nextInt(parcelas.size()));
                            randomParcela.addCultivo(cultivo);
                            setStatusMessage("Cultivo añadido a la parcela: " + randomParcela.toString());
                            dataModel.setParcelaSeleccionada(randomParcela);
                            table.setModel(dataModel);
                        } else {
                            JOptionPane.showMessageDialog(dialog, "No hay parcelas disponibles en el huerto.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(dialog, "No hay huerto seleccionado.");
                    }
                    dialog.dispose();
                }
            }
        });
    
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
    
        buttonPanel.add(acceptButton);
        buttonPanel.add(cancelButton);
    
        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
    
        dialog.setVisible(true);
    }

  

    public static void main(String[] args) {
        HuertoUrbano huerto = HuertoHelper.createHuertoExamen();
        SwingUtilities.invokeLater(() -> new GUI(huerto));
    }
}
