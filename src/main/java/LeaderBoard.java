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
    private JButton back;
    private JTextField title2;
    private JTextField player1;
    private JTextField player2;
    private JTextField player3;
    private JTextField player4;
    private JTextField player5;
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
        player1.setText(DBconnection.fromResultsetToString(
                DBconnection.prepAndExecuteQuery(0)));
        player2.setText(DBconnection.fromResultsetToString(
                DBconnection.prepAndExecuteQuery(1)));
        player3.setText(DBconnection.fromResultsetToString(
                DBconnection.prepAndExecuteQuery(2)));
        player4.setText(DBconnection.fromResultsetToString(
                DBconnection.prepAndExecuteQuery(3)));
        player5.setText(DBconnection.fromResultsetToString(
                DBconnection.prepAndExecuteQuery(4)));
    }

}
