package game;

import java.awt.Color;
import java.awt.Graphics;

public class Pellet extends Unit {

    public static final long serialVersionUID = 4328743;

    public Pellet(int x, int y) {
        setBounds(x, y, 4, 4);
    }

    public void render(Graphics g) {
        g.setColor(new Color(0xFFFB00));
        g.fillRect(x + 7, y + 7, width, height);
    }

    @Override
    public String getType() {
        return ".";
    }
}
