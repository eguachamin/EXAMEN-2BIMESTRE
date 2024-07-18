package PRUEBA12_7_2024;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class registrar extends  JFrame{
    private JButton REGISTRARButton;
    private JButton MENUButton;
    private JTextField codprod;
    private JTextField name;
    private JTextField descrip;
    private JTextField precio;
    private JTextField cantidad;
    private JTextField catego;
    private JPanel JPanel_Registro;

    public registrar() {
        super("Registro de Items");
        setContentPane(JPanel_Registro);
        REGISTRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    registrase();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        MENUButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu categoria=new menu();
                categoria.inciar();
                setVisible(false);
                dispose();
            }
        });
    }
    public void registrase () throws SQLException {
        String codigo1=codprod.getText();
        String nombre1=name.getText();
        String descrip1 = descrip.getText();
        String precio1=precio.getText();
        String cantidad1=cantidad.getText();
        String categoria1=catego.getText();

        Connection connecta=conectar();
        String sql= "INSERT INTO PRODUCTO (codigo_producto,nombre,descripcion,precio,cantidad, categoria)VALUES(?,?,?,?,?,?)";
        PreparedStatement pstmt = connecta.prepareStatement(sql);
        pstmt.setString(1,codigo1);
        pstmt.setString(2,nombre1);
        pstmt.setString(3,descrip1);
        pstmt.setDouble(4,Double.parseDouble(precio1));
        pstmt.setInt(5,Integer.parseInt(cantidad1));
        pstmt.setString(6,categoria1);
        int rowAffected = pstmt.executeUpdate();
        if(rowAffected>0){
            JOptionPane.showMessageDialog(null,"Registro insertado correctamente");
            codprod.setText("");
            name.setText("");
            descrip.setText("");
            precio.setText("");
            cantidad.setText("");
            catego.setText("");
        }
        pstmt.close();
        connecta.close();
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
