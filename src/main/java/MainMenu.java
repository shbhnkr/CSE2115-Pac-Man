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
    public static JFrame gameFrame = new JFrame();
    private transient JButton playGameButton;
    private transient JTextArea textArea;
    private transient JButton logoutButton;
    private transient JComboBox comboBox1;
    private transient JTextArea title;
    private transient JPanel playerDetails;
    private transient JTextField textField1;

    private transient JButton backButton;
    private transient JButton pause;
    private transient Font font;
    private transient String board2 = "board2.txt";
    private transient Gamesettings gamesettings = new Gamesettings(20, StartScreen.username);

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
        String welcome = "Hi, " + StartScreen.username;
        textField1.setText(welcome);
        textField1.setFont(new Font("Monaco",Font.PLAIN, 15));
        textField1.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.yellow));

        Color color = new Color(0, 0, 0);
        textArea = new JTextArea("Quick Instructions"+ "\n" + "\n");
        textArea.setEnabled(false);
        textArea.setBackground(color);
        textArea.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.orange));
        textArea.setDisabledTextColor(Color.cyan);
        textArea.append("* Pellet : 10 points" + "\n");
        textArea.append("* Cherry : 100 points"+ "\n");
        textArea.append("* Strawberry : 300 points"+ "\n");
        textArea.append("* Apple : 500 points" + "\n");
        textArea.setFont(new Font("Comic Sans MS", Font.ITALIC, 9));
        comboBox1.setFont(new Font(crackman, Font.PLAIN, 15));
        backButton = new JButton("Back to Main Menu");
        backButton.setFont(new Font(crackman, Font.PLAIN, 20));
        backButton.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        backButton.setBackground(Color.orange);
        pause = new JButton("Pause");
        pause.setFont(new Font(crackman, Font.PLAIN, 20));
        pause.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        pause.setBackground(Color.orange);
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
                JPanel gamePanel = new JPanel();
                Game game = new Game(gamesettings, pop);



                gameFrame.setTitle(Game.TITLE);
                gamePanel.setLayout(new BorderLayout());
                gamePanel.add(game, BorderLayout.CENTER);
                //gamePanel.add(pause, BorderLayout.NORTH);
                gamePanel.add(backButton, BorderLayout.NORTH);
                gamePanel.add(textArea, BorderLayout.WEST);
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

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Main Menu");
//        frame.setContentPane(new MainMenu().panel1);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(600, 300);
//        frame.setLocation(500, 300);
//        frame.setResizable(false);
//        frame.setVisible(true);
//    }
        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Game.isRunning) {
                    Game.isRunning = false;
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.isRunning = false;
                frame.setVisible(true);
                gameFrame.setVisible(false);
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
