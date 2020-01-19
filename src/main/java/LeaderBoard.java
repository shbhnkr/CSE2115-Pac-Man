import database.DBconnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;

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
        player1.setText(fromResultsetToString(prepAndExecuteQuery(1)));
        player2.setText(fromResultsetToString(prepAndExecuteQuery(2)));
        player3.setText(fromResultsetToString(prepAndExecuteQuery(3)));
        player4.setText(fromResultsetToString(prepAndExecuteQuery(4)));
        player5.setText(fromResultsetToString(prepAndExecuteQuery(5)));
    }

    public static ResultSet prepAndExecuteQuery( int rank) {
        ResultSet rs = null;
        String query =  "SELECT username, score" +
        "FROM ScoreBoard Emp1" +
        "WHERE (1) = (" +
        "SELECT COUNT(DISTINCT(Emp2.score)) " +
        "FROM ScoreBoard Emp2" +
        "WHERE Emp2.score > Emp1.score)";

        try {
            Connection conn = DBconnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(2, rank);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static String fromResultsetToString(ResultSet rs){
        String result = "";
        ResultSetMetaData rsmd = null;
        try {
            rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                   result += (columnValue + " " + rsmd.getColumnName(i));
                }
                System.out.println("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
