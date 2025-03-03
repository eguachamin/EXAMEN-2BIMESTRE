package PRUEBA12_7_2024;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menu extends JFrame{

    private JButton button1;
    private JRadioButton REGISTRARDATOSRadioButton;
    private JRadioButton BUSQUEDARadioButton;
    private JPanel JPanel_menu;
    private JRadioButton MODIFICARRadioButton;
    private JRadioButton ELIMINARRadioButton;

    public menu() {
        super("Menu Principal");
        setContentPane(JPanel_menu);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login salir = new login();
                salir.iniciar();
                dispose();
            }
        });
        REGISTRARDATOSRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrar registro=new registrar();
                registro.iniciar();
                dispose();
            }
        });
        BUSQUEDARadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                busqueda busc = new busqueda();
                busc.iniciar();
                dispose();
            }
        });
        MODIFICARRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificar modif = new modificar();
                modif.iniciar();
                dispose();
            }
        });
        ELIMINARRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminar ventana = new eliminar();
                ventana.iniciar();
                dispose();
            }
        });
    }

    public void inciar(){
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(600,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
