import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaludoInterfaz extends JFrame {
    private JLabel respuestaLabel;

    public SaludoInterfaz() {
        // Configuración de la ventana
        setTitle("Saludo");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear los componentes
        JButton holaButton = new JButton("Hola");
        JButton adiosButton = new JButton("Adiós");
        respuestaLabel = new JLabel("");

        // Configurar el diseño de la interfaz
        setLayout(new FlowLayout());

        // Agregar los componentes a la ventana
        add(holaButton);
        add(adiosButton);
        add(respuestaLabel);

        // Configurar los controladores de eventos para los botones
        holaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                respuestaLabel.setText("¡Hola! ¿Qué tal?");
            }
        });

        adiosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                respuestaLabel.setText("¡Adiós! Hasta luego.");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SaludoInterfaz interfaz = new SaludoInterfaz();
                interfaz.setVisible(true);
            }
        });
    }
}
