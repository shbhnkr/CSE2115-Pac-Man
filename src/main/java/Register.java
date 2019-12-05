import database.DBconnection;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
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
    private transient Connection conn;

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
                String uname = enterUsernameTextField.getText();
                String pwd = getSha(String.valueOf(enterPasswordPasswordField.getPassword()));
                System.out.println(pwd.length() + " pwd");


                PreparedStatement ps;
                String query = "INSERT INTO login(username, password) VALUES (?, ?)";
                try {
                    conn = DBconnection.getConnection();
                    ps = conn.prepareStatement(query);
                    ps.setString(1, uname);
                    ps.setString(2, pwd);
                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "new user added");
                    } else {
                        JOptionPane.showMessageDialog(null, "nope");

                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } finally {

                    try {
                        conn.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }


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
    public static String getSha(String input) {
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
