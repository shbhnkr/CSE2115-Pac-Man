
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.swing.*;


public class Register {
    public static JFrame rFrame;
    public transient JPanel panel1;
    private transient JButton button1;
    private transient JTextField enterUsernameTextField;
    private transient JPasswordField enterPasswordPasswordField;
    private transient JTextArea pacmanTextArea;
    private transient Font font;

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

        Dimension dimension = new Dimension(5, 5);
        enterUsernameTextField.setPreferredSize(dimension);
        enterPasswordPasswordField.setPreferredSize(dimension);
        Color color = new Color(255, 255, 255);
        enterUsernameTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, color));
        enterPasswordPasswordField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, color));
        button1.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, color));
        pacmanTextArea.setFont(new Font("crackman", Font.PLAIN, 35));
        button1.setFont(new Font("crackman", Font.PLAIN, 20));
        button1.setCursor(Cursor.getPredefinedCursor(12));
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
