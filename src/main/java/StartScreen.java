import Database.DBConnection;
import game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class StartScreen {
    public static JFrame frame1;
    private transient JTextField textField1;
    private transient JPanel panel1;
    private transient JPasswordField passwordField1;
    private transient JButton loginButton;
    private transient JButton newUserClickHereButton;
    private transient Connection conn;
    private transient ResultSet rs;
    private transient boolean pop;
    /**
     * Constructor.
     */
    public StartScreen() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(textField1.getText() + " Username");
                System.out.println(passwordField1.getText() + " Password");
                String Uname = textField1.getText();
                String pwd = String.valueOf(passwordField1.getPassword());
                pop = false;

                String query = "SELECT * FROM `login` WHERE `username`=? AND `password` =?";

                try {
                    conn = DBConnection.getConnection();
                    PreparedStatement ps = conn.prepareStatement(query);
                    ps.setString(1, Uname);
                    ps.setString(2, pwd);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        pop = true;
                        JOptionPane.showMessageDialog(null, "YES");
                    } else {
                        JOptionPane.showMessageDialog(null, "NO");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } finally {
                        try {
                            rs.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                    }

                    try {
                        conn.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                }

                if (pop) {
                    Game game = new Game();
                    JFrame frame = new JFrame();
                    frame.setTitle(Game.TITLE);
                    frame.add(game);
                    frame.setResizable(false);
                    frame.pack();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    game.start();
                    frame1.setVisible(false);
                }
            }
        });
        newUserClickHereButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Register.rFrame = new JFrame("Register");
                Register.rFrame.setContentPane(new Register().panel1);
                Register.rFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Register.rFrame.setSize(500, 300);
                Register.rFrame.setBackground(Color.BLACK);
                Register.rFrame.setLocation(500, 300);
                Register.rFrame.setResizable(false);
                Register.rFrame.setVisible(true);
                frame1.setVisible(false);

            }
        });
    }

    /**
     * Main method.
     *
     * @param args no use.
     */
    public static void main(String[] args) {
        frame1 = new JFrame("StartScreen");
        frame1.setContentPane(new StartScreen().panel1);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setSize(600, 300);
        frame1.setLocation(500, 300);
        frame1.setResizable(false);
        frame1.setVisible(true);
    }
}
