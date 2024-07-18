package CONEXION_NUBE;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menu1 extends JFrame{
    private JRadioButton registrarRadioButton;
    private JRadioButton eliminarRadioButton;
    private JPanel JPanel_MEnu;

    public menu1() {
        super("Iniciar");
        setContentPane(JPanel_MEnu);
        registrarRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registros1 regis = new registros1();
                regis.iniciar();
                dispose();
            }
        });
        eliminarRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminar1 elimi = new eliminar1();
                elimi.iniciar();
                dispose();
            }
        });
    }
    public void iniciar(){
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,600);
    }
}
