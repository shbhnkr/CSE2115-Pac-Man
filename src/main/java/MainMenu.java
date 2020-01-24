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
    public static JFrame gameFrame;
    private transient JButton playGameButton;
    private transient JTextArea textArea;
    private transient JButton logoutButton;
    private transient JComboBox comboBox1;
    private transient JTextArea title;
    private transient JPanel playerDetails;
    private transient JTextField textField1;

    private transient Font font;

    private transient String board1 = "map1.txt";
    private transient String board2 = "map2.txt";
    private transient String board3 = "map3.txt";
    private transient Gamesettings gamesettings = new Gamesettings(20, StartScreen.username);

    /**
     * the main menu.
     */
    public MainMenu() {
        URL path = ClassLoader.getSystemResource("crackman.ttf");
        File file = new File(path.getFile());
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, file);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);

        String crackman = "crackman";

        playGameButton.setFont(new Font(crackman, Font.PLAIN, 20));
        logoutButton.setFont(new Font(crackman, Font.PLAIN, 20));

        title.setFont(new Font(crackman, Font.PLAIN, 35));
        String welcome = "Hi, " + StartScreen.username;
        textField1.setText(welcome);
        textField1.setFont(new Font("Monaco", Font.PLAIN, 15));
        textField1.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.yellow));

        Color color = new Color(0, 0, 0);
        textArea = new JTextArea("Quick Instructions" + "\n" + "\n");
        textArea.setEnabled(false);
        textArea.setBackground(color);
        textArea.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.orange));
        textArea.setDisabledTextColor(Color.cyan);
        textArea.append("* Pellet : 10 points" + "\n");
        textArea.append("* Cherry : 100 points" + "\n");
        textArea.append("* Strawberry : 300 points" + "\n");
        textArea.append("* Apple : 500 points" + "\n");
        textArea.setFont(new Font("Comic Sans MS", Font.ITALIC, 9));
        comboBox1.setFont(new Font(crackman, Font.PLAIN, 15));
        comboBox1.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, color));
        comboBox1.setBackground(Color.orange);
        logoutButton.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, color));
        logoutButton.setBackground(Color.orange);
        playGameButton.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, color));
        playGameButton.setBackground(Color.orange);
        playGameButton.addActionListener(new ActionListener() {

            @SuppressWarnings("PMD")
            @Override
            public void actionPerformed(ActionEvent e) {

                String pop;
                if (comboBox1.getSelectedItem().toString().equals("Easy")) {
                    pop = board1;
                } else if (comboBox1.getSelectedItem().toString().equals("Medium")) {
                    pop = board2;
                } else if (comboBox1.getSelectedItem().toString().equals("Hard")) {
                    pop = board3;
                } else {
                    pop = "";
                }
                JPanel gamePanel = new JPanel();


                gameFrame = new JFrame();
                Game game = new Game(gamesettings, pop);
                gameFrame.setTitle(Game.TITLE);
                gamePanel.setLayout(new BorderLayout());
                gamePanel.add(game, BorderLayout.CENTER);
                //gamePanel.add(textArea, BorderLayout.WEST);
                gameFrame.add(gamePanel);
                gameFrame.setResizable(false);
                gameFrame.pack();
                gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gameFrame.setLocationRelativeTo(null);
                gameFrame.setVisible(true);
                game.start();
                frame.setVisible(false);
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StartScreen.frame1.setVisible(true);
                frame.setVisible(false);
            }
        });
    }
}
