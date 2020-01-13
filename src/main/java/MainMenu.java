import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MainMenu {
    private JPanel panel1;
    private JButton playGameButton;
    private JButton logoutButton;
    private JComboBox comboBox1;
    private JTextArea title;
    private JPanel playerDetails;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private Font font;
    
    public MainMenu() {
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

        playGameButton.setFont(new Font("crackman", Font.PLAIN, 20));
        logoutButton.setFont(new Font("crackman", Font.PLAIN, 20));
        title.setFont(new Font("crackman", Font.PLAIN, 35));

        Color color = new Color(0, 0, 0);

        comboBox1.setFont(new Font("crackman", Font.PLAIN, 15));

        comboBox1.setBorder(BorderFactory.createMatteBorder(0,0,0,0,color));
        logoutButton.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, color));
        playGameButton.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, color));

    }
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Main Menu");
//        frame.setContentPane(new MainMenu().panel1);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(600, 300);
//        frame.setLocation(500, 300);
//        frame.setResizable(false);
//        frame.setVisible(true);
//    }
}
