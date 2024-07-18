package PRUEBA12_7_2024;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class modificar extends JFrame {
    private JPanel JPanel_Modf;
    private JTextField cambio_carac;
    private JButton MODIFICARButton;
    private JTextField Cod_search;
    private JComboBox<String>  caracteristica;
    private JButton MENUButton;

    public modificar() {
        super("Modificar");
        setContentPane(JPanel_Modf);
        MODIFICARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    modificar1();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        lista_combobox();
        MENUButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu salir2 =new menu();
                salir2.inciar();
                dispose();
            }
        });
    }
    public void lista_combobox() {
        String[] lista = {"nombre","descripcion","precio","cantidad","categoria"};
        caracteristica.removeAllItems();
        for (String item : lista) {
            caracteristica.addItem(item);
        }
    }
    public  void  modificar1() throws SQLException {
        String codsearch= Cod_search.getText();
        String colum_mod = (String) caracteristica.getSelectedItem();
        String cambio = cambio_carac.getText();
        Connection conexion = conectar();
        String sql = "UPDATE PRODUCTO SET " + colum_mod + " = ? WHERE codigo_producto = ?";
        PreparedStatement stm = conexion.prepareStatement(sql);
        stm.setString(1,cambio);
        stm.setString(2,codsearch);
        int affectedRows = stm.executeUpdate();
        if (affectedRows > 0) {
            JOptionPane.showMessageDialog(null, "La actualización se realizó correctamente");
        } else {
            JOptionPane.showMessageDialog(null, "El código es incorrecto");
        }
        stm.close();
        conexion.close();
        Cod_search.setText("");
        cambio_carac.setText("");
    }
    public Connection conectar() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/productos_cp";
        String user="root";
        String pass = "";

        return DriverManager.getConnection(url,user,pass);
    }
    public void iniciar(){
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(600,500);
    }

}
