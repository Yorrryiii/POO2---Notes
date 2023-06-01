package Interfaces;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import Universidad.Alumno;
import Universidad.Curso;
import Universidad.Escuela;
import Universidad.Grado;
import Universidad.HelperEscuela;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

public class GUI extends JFrame {
    private JTree arbolEscuela;
    private JPanel panelAlumno;
    private JLabel labelNombre;
    private JLabel labelApellidos;
    private JLabel labelDNI;
    private JLabel labelTelefono;
    private JMenuBar menuBar;
    private JMenu menuAdd;
    private JMenuItem menuItemGrado;
    private JMenuItem menuItemCurso;
    private JMenuItem menuItemAlumno;

    private Escuela escuela;

    public GUI(Escuela escuela) {
        this.escuela = escuela;

        initComponents();
        createTree();
        updateAlumnoPanel(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Árbol de Escuela");
        setLayout(new BorderLayout());
        add(menuBar, BorderLayout.NORTH);
        add(new JScrollPane(arbolEscuela), BorderLayout.WEST);
        add(panelAlumno, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        arbolEscuela = new JTree();
        arbolEscuela.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) arbolEscuela.getLastSelectedPathComponent();
            if (selectedNode != null && selectedNode.getUserObject() instanceof Alumno) {
                Alumno alumno = (Alumno) selectedNode.getUserObject();
                updateAlumnoPanel(alumno);
            } else {
                updateAlumnoPanel(null);
            }
        });

        panelAlumno = new JPanel();
        panelAlumno.setLayout(new GridLayout(4, 2));

        labelNombre = new JLabel();
        panelAlumno.add(new JLabel("Nombre:"));
        panelAlumno.add(labelNombre);

        labelApellidos = new JLabel();
        panelAlumno.add(new JLabel("Apellidos:"));
        panelAlumno.add(labelApellidos);

        labelDNI = new JLabel();
        panelAlumno.add(new JLabel("DNI:"));
        panelAlumno.add(labelDNI);

        labelTelefono = new JLabel();
        panelAlumno.add(new JLabel("Teléfono:"));
        panelAlumno.add(labelTelefono);

        menuBar = new JMenuBar();
        menuAdd = new JMenu("Add...");

        menuItemGrado = new JMenuItem("Grado");
        menuItemGrado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addNewGrado();
            }
        });
        menuAdd.add(menuItemGrado);

        menuItemCurso = new JMenuItem("Curso");
        menuItemCurso.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addNewCurso();
            }
        });
        menuAdd.add(menuItemCurso);

        menuItemAlumno = new JMenuItem("Alumno");
        menuItemAlumno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAddAlumnoDialog();
            }
        });
        menuAdd.add(menuItemAlumno);

        menuBar.add(menuAdd);
    }

    private void createTree() {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(escuela.getNombre());
        DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
        arbolEscuela.setModel(treeModel);

        List<Grado> grados = escuela.getGrados();
        for (Grado grado : grados) {
            DefaultMutableTreeNode gradoNode = new DefaultMutableTreeNode(grado.getNombre());
            rootNode.add(gradoNode);

            List<Curso> cursos = grado.getCursos();
            for (Curso curso : cursos) {
                DefaultMutableTreeNode cursoNode = new DefaultMutableTreeNode(curso.getNombre());
                gradoNode.add(cursoNode);

                List<Alumno> alumnos = curso.getAlumnos();
                for (Alumno alumno : alumnos) {
                    DefaultMutableTreeNode alumnoNode = new DefaultMutableTreeNode(alumno);
                    cursoNode.add(alumnoNode);
                }
            }
        }
    }

    private void updateAlumnoPanel(Alumno alumno) {
        if (alumno != null) {
            labelNombre.setText(alumno.getNombre());
            labelApellidos.setText(alumno.getApellido1());
            labelDNI.setText(alumno.getDNI());
            labelTelefono.setText(alumno.getTelefono());
        } else {
            labelNombre.setText("");
            labelApellidos.setText("");
            labelDNI.setText("");
            labelTelefono.setText("");
        }
    }

    private void addNewGrado() {
        Random random = new Random();
        String nombreGrado = "Grado " + random.nextInt(1000);
        Grado grado = new Grado(nombreGrado);
        escuela.addGrado(grado);

        DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) arbolEscuela.getModel().getRoot();
        DefaultMutableTreeNode gradoNode = new DefaultMutableTreeNode(nombreGrado);
        rootNode.add(gradoNode);

        ((DefaultTreeModel) arbolEscuela.getModel()).reload(rootNode);
    }

    private void addNewCurso() {
        if (escuela.getGrados().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay grados disponibles.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Random random = new Random();
        int randomGradoIndex = random.nextInt(escuela.getGrados().size());
        Grado grado = escuela.getGrados().get(randomGradoIndex);

        String nombreCurso = "Curso " + random.nextInt(1000);
        Curso curso = new Curso(nombreCurso);
        grado.addCurso(curso);

        DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) arbolEscuela.getModel().getRoot();
        DefaultMutableTreeNode gradoNode = (DefaultMutableTreeNode) rootNode.getChildAt(randomGradoIndex);
        DefaultMutableTreeNode cursoNode = new DefaultMutableTreeNode(nombreCurso);
        gradoNode.add(cursoNode);

        ((DefaultTreeModel) arbolEscuela.getModel()).reload(gradoNode);
    }

    private void showAddAlumnoDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setModal(true);
        dialog.setVisible(true);
    }

    private class Dialog extends JDialog {
        private JLabel labelNombre;
        private JLabel labelApellidos;
        private JLabel labelDNI;
        private JLabel labelTelefono;
        private JTextField textFieldNombre;
        private JTextField textFieldApellidos;
        private JTextField textFieldDNI;
        private JTextField textFieldTelefono;
        private JButton buttonCancelar;
        private JButton buttonAceptar;

        public Dialog(Frame owner) {
            super(owner, "Agregar Alumno", true);

            labelNombre = new JLabel("Nombre:");
            labelApellidos = new JLabel("Apellidos:");
            labelDNI = new JLabel("DNI:");
            labelTelefono = new JLabel("Teléfono:");

            textFieldNombre = new JTextField();
            textFieldApellidos = new JTextField();
            textFieldDNI = new JTextField();
            textFieldTelefono = new JTextField();

            buttonCancelar = new JButton("Cancelar");
            buttonCancelar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });

            buttonAceptar = new JButton("Aceptar");
            buttonAceptar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String nombre = textFieldNombre.getText();
                    String apellidos = textFieldApellidos.getText();
                    String dni = textFieldDNI.getText();
                    String telefono = textFieldTelefono.getText();

                    Alumno alumno = new Alumno(nombre, apellidos, telefono, dni, 0, 0, 0);

                    Random random = new Random();
                    int randomGradoIndex = random.nextInt(escuela.getGrados().size());
                    Grado grado = escuela.getGrados().get(randomGradoIndex);

                    int randomCursoIndex = random.nextInt(grado.getCursos().size());
                    Curso curso = grado.getCursos().get(randomCursoIndex);

                    curso.addAlumno(alumno);

                    DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) arbolEscuela.getModel().getRoot();
                    DefaultMutableTreeNode gradoNode = (DefaultMutableTreeNode) rootNode.getChildAt(randomGradoIndex);
                    DefaultMutableTreeNode cursoNode = (DefaultMutableTreeNode) gradoNode.getChildAt(randomCursoIndex);
                    DefaultMutableTreeNode alumnoNode = new DefaultMutableTreeNode(alumno);
                    cursoNode.add(alumnoNode);

                    ((DefaultTreeModel) arbolEscuela.getModel()).reload(cursoNode);

                    dispose();
                }
            });

            JPanel panel = new JPanel(new GridLayout(5, 2));
            panel.add(labelNombre);
            panel.add(textFieldNombre);
            panel.add(labelApellidos);
            panel.add(textFieldApellidos);
            panel.add(labelDNI);
            panel.add(textFieldDNI);
            panel.add(labelTelefono);
            panel.add(textFieldTelefono);
            panel.add(buttonCancelar);
            panel.add(buttonAceptar);

            add(panel);
            pack();
            setLocationRelativeTo(owner);
        }
    }

    public static void main(String[] args) {
        Escuela escuelaEjemplo = HelperEscuela.createEscuela(10, 5, 3, "Escuela Ejemplo");
        SwingUtilities.invokeLater(() -> new GUI(escuelaEjemplo));
    }
}
