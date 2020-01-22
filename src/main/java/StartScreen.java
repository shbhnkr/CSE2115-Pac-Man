import database.DBconnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class StartScreen {
    static JFrame frame1;
    public transient JTextField textField1;
    private transient JPanel panel1;
    private transient JPasswordField passwordField1;
    private transient JButton loginButton;
    private transient JButton newUserClickHereButton;
    private transient Font font;
    private transient JTextArea pacmanText;
    private transient JButton leaderboardButton;
    private transient char password;
    private transient Connection conn;
    private transient ResultSet rs;
    private transient boolean pop;

    public static transient String username;

    public transient String crackman = "crackman";
    /**
     * Constructor.
     */
    private StartScreen() {
        URL path = ClassLoader.getSystemResource("crackman.ttf");
        File file = new File(path.getFile());
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, file);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
        loginButton.addActionListener(new ActionListener() {
            @Override
            @SuppressWarnings("PMD")
            public void actionPerformed(ActionEvent e) {

                String uname = textField1.getText();


                String pwd = Register.getSha(String.valueOf(passwordField1.getPassword()));
                pop = false;

                String query = "SELECT * FROM `login` WHERE `username`=? AND `password` =?";

                try {
                    conn = DBconnection.getConnection();
                    PreparedStatement ps = conn.prepareStatement(query);
                    ps.setString(1, uname);
                    ps.setString(2, pwd);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        pop = true;

                        username = uname;
                        JOptionPane.showMessageDialog(null, "Welcome " + uname);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid password/username");
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

                    MainMenu.frame = new JFrame("Main Menu");
                    MainMenu.frame.setContentPane(new MainMenu().panel1);
                    MainMenu.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    MainMenu.frame.setSize(600, 300);
                    MainMenu.frame.setLocation(500, 300);
                    MainMenu.frame.setResizable(false);
                    MainMenu.frame.setVisible(true);
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
        Dimension dimension = new Dimension(5, 5);
        textField1.setPreferredSize(dimension);
        passwordField1.setPreferredSize(dimension);
        pacmanText.setFont(new Font(crackman, Font.PLAIN, 35));
        loginButton.setFont(new Font(crackman, Font.PLAIN, 20));
        leaderboardButton.setFont(new Font(crackman, Font.PLAIN, 20));
        newUserClickHereButton.setFont(new Font(crackman, Font.PLAIN, 20));
        Color color = new Color(0, 0, 0);
        textField1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, color));
        passwordField1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, color));
        loginButton.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, color));
        loginButton.setBackground(Color.orange);
        newUserClickHereButton.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, color));
        newUserClickHereButton.setBackground(Color.orange);
        leaderboardButton.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, color));
        leaderboardButton.setBackground(Color.orange);
        textField1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField1.getText().equals("Enter Username")) {
                    textField1.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField1.getText().equals("")) {
                    textField1.setText("Enter Username");
                }
            }
            public String getUserName(){
                return username;
            }
        });

        password = passwordField1.getEchoChar();
        passwordField1.setEchoChar((char) 0);
        passwordField1.setText("Enter Password");
        passwordField1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (passwordField1.getText().equals("Enter Password")) {
                    passwordField1.setText("");
                    passwordField1.setEchoChar(password);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (passwordField1.getText().equals("")) {
                    passwordField1.setEchoChar((char) 0);
                    passwordField1.setText("Enter Password");

                } else {

                    passwordField1.setEchoChar(password);
                }

            }
        });
        leaderboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LeaderBoard.lframe = new JFrame("Leaderboard");
                LeaderBoard.lframe.setContentPane(new LeaderBoard().leaderPanel);
                LeaderBoard.lframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                LeaderBoard.lframe.setSize(600, 300);
                LeaderBoard.lframe.setLocation(500, 300);
                LeaderBoard.lframe.setResizable(false);
                LeaderBoard.lframe.setVisible(true);

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
