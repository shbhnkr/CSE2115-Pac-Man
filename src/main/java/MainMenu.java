import game.Game;
import game.Gamesettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MainMenu {
    public static JFrame frame;
    public transient JPanel panel1;
    private transient JButton playGameButton;
    private transient JButton logoutButton;
    private transient JComboBox comboBox1;
    private transient JTextArea title;
    private transient JPanel playerDetails;
    private transient JTextArea textArea1;
    private transient JTextArea textArea2;
    private transient Font font;
    private transient String board2 = "board2.txt";
    private transient Gamesettings gamesettings = new Gamesettings(20);;
    private transient String board1 = "board1.txt";

    private transient String board3 = "board3.txt";

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
        String crackman = "crackman";
        playGameButton.setFont(new Font(crackman, Font.PLAIN, 20));
        logoutButton.setFont(new Font(crackman, Font.PLAIN, 20));
        title.setFont(new Font(crackman, Font.PLAIN, 35));

        Color color = new Color(0, 0, 0);

        comboBox1.setFont(new Font(crackman, Font.PLAIN, 15));


        comboBox1.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, color));
        comboBox1.setBackground(Color.orange);
        logoutButton.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, color));
        logoutButton.setBackground(Color.orange);
        playGameButton.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, color));
        playGameButton.setBackground(Color.orange);
        playGameButton.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {

                String pop;
                if (comboBox1.getSelectedItem().toString().equals("Easy")) {
                    pop = board2;
                } else if (comboBox1.getSelectedItem().toString().equals("Medium")) {
                    pop = board1;
                } else if (comboBox1.getSelectedItem().toString().equals("Hard")) {
                    pop = board3;
                } else {
                    pop = "";
                }
                Game game = new Game(gamesettings, pop);
                JFrame frame1 = new JFrame();
                frame1.setTitle(Game.TITLE);
                frame1.add(game);
                frame1.setResizable(false);
                frame1.pack();
                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame1.setLocationRelativeTo(null);
                frame1.setVisible(true);
                game.start();
                frame.setVisible(false);
            }
        });

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
}
