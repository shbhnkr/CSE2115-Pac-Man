import Database.DBConnection;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;



public class Register {
    public static JFrame rFrame;
    public transient JPanel panel1;
    private transient JButton button1;
    private transient JTextField enterUsernameTextField;
    private transient JPasswordField enterPasswordPasswordField;
    private transient JTextArea pacmanTextArea;

    /**
     * Constructor.
     */
    public Register() {
        Dimension dimension = new Dimension(20, 20);
        enterUsernameTextField.setPreferredSize(dimension);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(enterUsernameTextField.getText() + " Username");
                System.out.println(enterPasswordPasswordField.getText() + " Password");
                String uName = enterUsernameTextField.getText();
                String pwd = getSHA(enterPasswordPasswordField.getText());


                PreparedStatement ps;
                String query = "INSERT INTO login(username, password) VALUES (?, ?)";
                try {
                    ps = DBConnection.getConnection().prepareStatement(query);
                    ps.setString(1, uName);
                    ps.setString(2, pwd);
                    if(ps.executeUpdate() > 0){
                        JOptionPane.showMessageDialog(null, "new user added");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "nope");

                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                StartScreen.frame1.setVisible(true);
                rFrame.setVisible(false);
            }
        });
    }
    /**
     * Hashes password.
     *
     * @param input password entered by user
     */
    public static String getSHA(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
            return null;
        }
    }



}
