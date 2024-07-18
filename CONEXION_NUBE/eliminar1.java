package CONEXION_NUBE;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class eliminar1 extends JFrame{
    private JRadioButton REGISTRORadioButton;
    private JPanel JPanel_Elimi;
    private JTextField cod_busc;
    private JButton ELIMINARButton;
    private JButton MENUButton;

    public eliminar1() {
        super("ELIMINAR");
        setContentPane(JPanel_Elimi);
        ELIMINARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    eliminar2();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        MENUButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu1 salir=new menu1();
                salir.iniciar();
                dispose();
            }
        });
    }
    public void eliminar2 () throws SQLException {
        String codbusca=cod_busc.getText();
        Connection conecct=conectar();
        String sql = "DELETE FROM PRUEBA_CONEC WHERE CI_CLIENTE=?";
        PreparedStatement stm = conecct.prepareStatement(sql);
        stm.setString(1,codbusca);
        int Affecctrows = stm.executeUpdate();
        if (Affecctrows>0){
            JOptionPane.showMessageDialog(null,"El cliente se encuentra eliminado");
        }
        else {
            JOptionPane.showMessageDialog(null,"Codigo ingresado incorrectamente");
        }
        stm.close();
        conecct.close();
        cod_busc.setText("");
    }
    public Connection conectar() throws SQLException {
        String url = "jdbc:mysql://uzkbu5ogetqorsi9:1Kwlp5ZUmsW8z9u0iuFh@bcjr18avtrqj7jmwdeko-mysql.services.clever-cloud.com:3306/bcjr18avtrqj7jmwdeko";
        String user = "uzkbu5ogetqorsi9";
        String pass="1Kwlp5ZUmsW8z9u0iuFh";

        return DriverManager.getConnection(url,user,pass);
    }
    public void iniciar(){
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,400);
    }
}
