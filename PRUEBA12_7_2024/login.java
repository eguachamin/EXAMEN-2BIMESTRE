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
        String sql ="SELECT * FROM acceso WHERE usuario=? AND password=?";
        PreparedStatement strm = conectarse.prepareStatement(sql);
        strm.setString(1,usuario1);
        strm.setString(2,pass);
        ResultSet rs = strm.executeQuery();
        if (rs.next()){
            String user2=rs.getString("usuario");
            String pass2=rs.getString("password");
            if (user2.equals(usuario1) && pass2.equals(pass)){
                menu catalogo = new menu();
                catalogo.inciar();
                dispose();
            }
        }
        else {
            JOptionPane.showMessageDialog(null,"El usuario o contraseña se encuentran erroneos");
            user.setText("");
            password.setText("");
        }
        conectarse.close();
        strm.close();

    }

    public Connection conectar() throws SQLException {
        String url = "jdbc:mysql://uceiprz5lnyxwcun:2MEuIBNxrt0DUkvVbQaE@bi7ej80dgemnxzfdcj4h-mysql.services.clever-cloud.com:3306/bi7ej80dgemnxzfdcj4h";
        String usurious="uceiprz5lnyxwcun";
        String password1="2MEuIBNxrt0DUkvVbQaE";

        return DriverManager.getConnection(url,usurious,password1);
    }
    public void iniciar(){
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(400,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
