package PRUEBA12_7_2024;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class busqueda extends JFrame{

    private JPanel JPanel_register;
    private JTextField busqueda;
    private JButton buscarButton;
    private JLabel name;
    private JLabel precio;
    private JLabel categ;
    private JLabel stock;
    private JButton menuButton;
    private JButton limpiarButton;

    public busqueda() {
        super("Busqueda");
        setContentPane(JPanel_register);
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    search();
                } catch (SQLException ex) {
                    menu salida = new menu();
                    salida.inciar();
                }
            }
        });
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu salida1 = new menu();
                salida1.inciar();
            }
        });
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                busqueda.setText("");
                name.setText("Nombre");
                precio.setText("Precio");
                categ.setText("Categoria");
                stock.setText("Cantidad");
            }
        });
    }
    public void search() throws SQLException {
        String item_buscar=busqueda.getText();
        Connection connecta=conectar();
        String sql= "SELECT * FROM PRODUCTO WHERE codigo_producto=?";
        PreparedStatement pstmt = connecta.prepareStatement(sql);
        pstmt.setString(1,item_buscar);
        ResultSet rs= pstmt.executeQuery();
        if (rs.next()){
            String buscando = rs.getNString("codigo_producto");
            if (buscando.equals(item_buscar)){
                name.setText(rs.getString("nombre"));
                precio.setText(rs.getString("precio"));
                categ.setText(rs.getString("categoria"));
                stock.setText(rs.getString("cantidad"));
            }
        }
        else {
            JOptionPane.showMessageDialog(null,"El codigo no existe, intente de nuevo");
        }
        connecta.close();
        pstmt.close();
    }
    public Connection conectar() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/productos_cp";
        String usurious="root";
        String password1="";

        return DriverManager.getConnection(url,usurious,password1);
    }
    public void iniciar(){
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(400,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
