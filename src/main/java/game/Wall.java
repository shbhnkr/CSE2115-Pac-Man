package game;

import java.awt.*;

public class Wall extends Rectangle {

    public static final long serialVersionUID = 4328743;

    public Wall(int x, int y) {
        setBounds(x, y, 20, 20);
    }

    public void render(Graphics g) {
        g.setColor(new Color(33, 0, 127));
        g.fillRect(x, y, width, height);
    }
}
