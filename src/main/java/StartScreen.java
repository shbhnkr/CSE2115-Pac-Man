import game.Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class StartScreen {
    public static JFrame frame1;
    private transient JTextField textField1;
    private transient JPanel panel1;
    private transient JPasswordField passwordField1;
    private transient JButton loginButton;
    private transient JButton newUserClickHereButton;

    /**
     * Constructor.
     */
    public StartScreen() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(textField1.getText() + " Username");
                System.out.println(passwordField1.getText() + " Password");
                Game game = new Game();
                Register.rFrame = new JFrame();
                Register.rFrame.setTitle(Game.TITLE);
                Register.rFrame.add(game);
                Register.rFrame.setResizable(false);
                Register.rFrame.pack();
                Register.rFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Register.rFrame.setLocationRelativeTo(null);
                Register.rFrame.setVisible(true);
                game.start();
                frame1.setVisible(false);
            }
        });
        newUserClickHereButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Register");
                frame.setContentPane(new Register().panel1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(500, 300);
                frame.setBackground(Color.BLACK);
                frame.setLocation(500, 300);
                frame.setResizable(false);
                frame.setVisible(true);
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
