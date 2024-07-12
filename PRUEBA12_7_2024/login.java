package PRUEBA12_7_2024;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class login extends JFrame{
    private JTextField user;
    private JTextField password;
    private JButton ingresarButton;
    private JPanel JPanel_Login;

    public login() {
        super("Login");
        setContentPane(JPanel_Login);
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ingresando();
                }
                catch (SQLException ex)
                {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    public void ingresando () throws SQLException {
        String usuario1= user.getText();
        String pass = password.getText();
        Connection conectarse = conectar();
        String sql ="SELECT * FROM USUARIO WHERE username=? AND password=?";
        PreparedStatement strm = conectarse.prepareStatement(sql);
        strm.setString(1,usuario1);
        strm.setString(2,pass);
        ResultSet rs = strm.executeQuery();
        if (rs.next()){
            String user2=rs.getString("username");
            String pass2=rs.getString("password");
            if (user2.equals(usuario1) && pass2.equals(pass)){
                menu catalogo = new menu();
                catalogo.inciar();
            }
            else {
                JOptionPane.showMessageDialog(null,"El usaurio o contraseña se encuentran erroneos");
            }
        }
        conectarse.close();
        strm.close();

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
