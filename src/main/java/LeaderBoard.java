import database.DBconnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class LeaderBoard {
    public static JFrame lframe;
    public transient JPanel leaderPanel;
    private transient JTextField title;
    private transient JButton back;
    private transient JTextField title2;
    private transient JTextField player1;
    private transient JTextField player2;
    private transient JTextField player3;
    private transient JTextField player4;
    private transient JTextField player5;
    private transient Font font;

    public LeaderBoard() {
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
        String fontName = "crackman";

        Color color = new Color(255, 255, 0);

        title.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, color));

        back.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, color));
        title.setFont(new Font(fontName, Font.PLAIN, 30));
        back.setFont(new Font(fontName, Font.PLAIN, 20));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StartScreen.frame1.setVisible(true);
                lframe.setVisible(false);
            }
        });
        player1.setText("Rank 1:  " + DBconnection.fromResultsetToString(
                DBconnection.prepAndExecuteQuery(0)));
        player2.setText("Rank 2:  " + DBconnection.fromResultsetToString(
                DBconnection.prepAndExecuteQuery(1)));
        player3.setText("Rank 3:  " + DBconnection.fromResultsetToString(
                DBconnection.prepAndExecuteQuery(2)));
        player4.setText("Rank 4:  " + DBconnection.fromResultsetToString(
                DBconnection.prepAndExecuteQuery(3)));
        player5.setText("Rank 5:  " + DBconnection.fromResultsetToString(
                DBconnection.prepAndExecuteQuery(4)));
    }

}
