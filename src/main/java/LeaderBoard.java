import database.DBconnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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


    }

    public static void prepAndExecuteQuery(String uname, int rank) {

        String query = "select * from(\n" +
                "select `username`, `score`, dense_rank() \n" +
                "over(order by `score` desc)r from `ScoreBoard`) \n" +
                "where r = rank;";

        try {
            Connection conn = DBconnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, uname);
            ps.setInt(2, rank);
            ResultSet rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}