import database.DBconnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Register {
    public static JFrame rFrame;
    public transient JPanel panel1;
    private transient JButton button1;
    private transient JTextField enterUsernameTextField;
    private transient JPasswordField enterPasswordPasswordField;
    private transient JTextArea pacmanTextArea;

    private transient Font font;
    private transient char passwordChar;
    private transient Connection conn;


    /**
     * Constructor.
     */
    public Register() {

        URL path = ClassLoader.getSystemResource("crackman.ttf");
        File file = new File(path.getFile());
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, file);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);

        passwordChar = enterPasswordPasswordField.getEchoChar();
        enterPasswordPasswordField.setEchoChar((char) 0);
        enterPasswordPasswordField.setText("Create Password");
        enterPasswordPasswordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (enterPasswordPasswordField.getText().equals("Create Password")) {
                    enterPasswordPasswordField.setText("");
                    enterPasswordPasswordField.setEchoChar(passwordChar);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (enterPasswordPasswordField.getText().equals("")) {
                    enterPasswordPasswordField.setEchoChar((char) 0);
                    enterPasswordPasswordField.setText("Create Password");

                } else {

                    enterPasswordPasswordField.setEchoChar(passwordChar);
                }

            }
        });

        enterUsernameTextField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent fe) {
                if (enterUsernameTextField.getText().equals("Create Username")) {
                    enterUsernameTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
                if (enterUsernameTextField.getText().equals("")) {
                    enterUsernameTextField.setText("Create Username");
                }

            }
        });
        Dimension dimension = new Dimension(5, 5);
        enterUsernameTextField.setPreferredSize(dimension);
        enterPasswordPasswordField.setPreferredSize(dimension);
        Color color = new Color(255, 255, 0);
        enterUsernameTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, color));
        enterPasswordPasswordField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, color));
        button1.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, color));
        pacmanTextArea.setFont(new Font("crackman", Font.PLAIN, 35));
        button1.setFont(new Font("crackman", Font.PLAIN, 20));
        button1.setCursor(Cursor.getPredefinedCursor(12));
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//
//                 if (!display(enterPasswordPasswordField.getText())) {
//                 enterPasswordPasswordField.setBorder(BorderFactory.createMatteBorder(
//                 0, 0, 1, 0, new Color(255, 0, 0)));
//                 } else {
//                 StartScreen.frame1.setVisible(true);
//                 rFrame.setVisible(false);
//                 }

                System.out.println(enterUsernameTextField.getText() + " Username");
                System.out.println(enterPasswordPasswordField.getText() + " Password");
                String uname = enterUsernameTextField.getText();
                String pwd = getSha(String.valueOf(enterPasswordPasswordField.getPassword()));
                System.out.println(pwd.length() + " pwd");


                PreparedStatement ps;
                String query = "INSERT INTO login(username, password) VALUES (?, ?)";
                try {

//                     boolean qwerty = false;
//                     while (qwerty == false) {
//                     JOptionPane.showMessageDialog(null, "choose a stronger password");
//                     if(display(enterPasswordPasswordField.getText()))
//                     {
//                     qwerty = true;
//                     }
//                     }

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
     * checks if password is possible.
     */
    public static boolean display(String password) {
        if (password.matches(".*[0-9]{1,}.*") && password.matches(".*[@#$]{1,}.*")
                && password.length() >= 6 && password.length() <= 20) {
            return true;
        } else {
            return false;
        }
    }

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



