package game;

import java.awt.Color;
import java.awt.Graphics;

public class Wall extends Unit {

    public static final long serialVersionUID = 4328743;

    public Wall(int x, int y) {
        setBounds(x, y, 20, 20);
    }

    public void render(Graphics g) {
        g.setColor(new Color(0, 12, 182));
        g.fillRect(x, y, width, height);
    }

    @Override
    String getType() {
        return "#";
    }
}
