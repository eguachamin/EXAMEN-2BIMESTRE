package PRUEBA12_7_2024;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class eliminar extends JFrame{
    private JPanel JPanel_Elim;
    private JTextField cod_buscar;
    private JButton eliminarButton;
    private JButton menuButton;
    private JLabel nombre_prod;
    private JButton buscarButton;

    public eliminar() {
        super("Eliminar Producto");
        setContentPane(JPanel_Elim);
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    eliminando();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu salida3 = new menu();
                salida3.inciar();
                dispose();
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    buscando();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }
    public  void eliminando() throws SQLException {
        String codbusca1=cod_buscar.getText();
        Connection conectanding = conectare();
        String sql = "DELETE FROM PRODUCTO WHERE codigo_producto=?";
        PreparedStatement stm = conectanding.prepareStatement(sql);
        stm.setString(1,codbusca1);
        int affectedRows = stm.executeUpdate();
        if (affectedRows> 0){
            JOptionPane.showMessageDialog(null,"El producto se encuentrta eliminado");
            cod_buscar.setText("");
            nombre_prod.setText("");
        }
        else {
            JOptionPane.showMessageDialog(null,"El codigo ingresado es incorrecto");
            cod_buscar.setText("");
        }
        stm.close();
        conectanding.close();
    }


    public void buscando() throws SQLException {
        String codbusca = cod_buscar.getText();
        Connection conectar = conectare();
        String sql = "SELECT * FROM PRODUCTO WHERE codigo_producto=?";
        PreparedStatement stm = conectar.prepareStatement(sql);
        stm.setString(1,codbusca);
        ResultSet rs = stm.executeQuery();
        if (rs.next()){
            String buscado = rs.getNString("codigo_producto");
            if (buscado.equals(codbusca)){
                nombre_prod.setText(rs.getString("nombre"));
            }
        }
        else {
            JOptionPane.showMessageDialog(null,"El codigo ingresado no es correcto");
            cod_buscar.setText("");
        }
        conectar.close();
        stm.close();
    }
    public Connection conectare() throws SQLException {
        String url = "jdbc:mysql://localhost/productos_cp";
        String user = "root";
        String pass="";

        return DriverManager.getConnection(url,user,pass);
    }
    public void iniciar(){
      setVisible(true);
      setLocationRelativeTo(null);
      setSize(600,400);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
