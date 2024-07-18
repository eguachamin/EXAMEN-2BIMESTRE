package CONEXION_NUBE;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class registros1 extends JFrame{
    private JTextField cod_ing;
    private JTextField nom_ing;
    private JTextField apel_ing;
    private JButton registrarButton;
    private JButton MENUButton;
    private JPanel JPanle_registro;

    public registros1() {
        super("Registro");
        setContentPane(JPanle_registro);
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    registrar();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        MENUButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu1 ingre = new menu1();
                ingre.iniciar();
                dispose();
            }
        });
    }
    public void registrar() throws SQLException {
        String cedula= cod_ing.getText() ;
        String nombre = nom_ing.getText();
        String apellido = apel_ing.getText();
        Connection conexion = conectado();
        String sql = "INSERT INTO PRUEBA_CONEC (CI_CLIENTE,NOMBRE_CLI,APELLIDO_CLI) VALUES (?,?,?)";
        PreparedStatement stm = conexion.prepareStatement(sql);
        stm.setInt(1,Integer.parseInt(cedula));
        stm.setString(2,nombre);
        stm.setString(3,apellido);
        int Affectrows = stm.executeUpdate();
        if (Affectrows>0){
            JOptionPane.showMessageDialog(null,"Registro ingresado correctamente");
            cod_ing.setText("");
            nom_ing.setText("");
            apel_ing.setText("");
        }
        else {
            JOptionPane.showMessageDialog(null,"Hay un problema con la coneccion, no se ingreso el registro");
        }
        stm.close();
        conexion.close();
    }
    public void iniciar(){
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,600);
    }
    public Connection conectado() throws SQLException {
        String url = "jdbc:mysql://uzkbu5ogetqorsi9:1Kwlp5ZUmsW8z9u0iuFh@bcjr18avtrqj7jmwdeko-mysql.services.clever-cloud.com:3306/bcjr18avtrqj7jmwdeko";
        String user = "uzkbu5ogetqorsi9";
        String pass="1Kwlp5ZUmsW8z9u0iuFh";

        return DriverManager.getConnection(url,user,pass);
    }
}
