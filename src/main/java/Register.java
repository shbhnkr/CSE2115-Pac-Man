import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
                StartScreen.frame1.setVisible(true);
                rFrame.setVisible(false);
            }
        });
    }

}
