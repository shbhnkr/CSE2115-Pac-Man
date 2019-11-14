package game;

import java.awt.*;

public class Pellet extends Rectangle {

    public static final long serialVersionUID = 4328743;

    public Pellet(int x, int y) {
        setBounds(x, y, 4, 4);
    }

    public void render(Graphics g) {
        g.setColor(new Color(0xe6e600));
        g.fillRect(x + 7, y + 7, width, height);
    }
}
