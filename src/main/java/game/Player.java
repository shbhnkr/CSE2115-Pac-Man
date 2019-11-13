package game;

import java.awt.*;

public class Player extends Rectangle {
    public static final long serialVersionUID = 4328743;

    public Player(int x, int y) {
        setBounds(x, y, 20, 20);
    }

    public void render(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, width, height);
    }

}
