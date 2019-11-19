
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Register {
    private transient JButton button1;
    private transient JPanel panel1;
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
            }
        });
    }

    /**
     * Main method.
     * @param args no use.
     */
    public static void main(String [] args) {
        JFrame frame = new JFrame("Register");
        frame.setContentPane(new Register().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setBackground(Color.BLACK);
        frame.setLocation(500, 300);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
