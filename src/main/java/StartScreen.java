
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class StartScreen {
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
            }
        });
        newUserClickHereButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //LINK WITH REGISTER
            }
        });
    }

    /**
     * Main method.
     *
     * @param args no use.
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("StartScreen");
        frame.setContentPane(new StartScreen().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);
        frame.setLocation(500, 300);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
