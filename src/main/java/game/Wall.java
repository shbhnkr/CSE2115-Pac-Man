package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall extends Unit {

    public static final long serialVersionUID = 4328743;

    public Wall(int x, int y) {
        setBounds(x, y, 20, 20);
    }

    public void render(Graphics g) {
        g.setColor(new Color(33, 0, 127));
        g.fillRect(x, y, width, height);
    }
}
