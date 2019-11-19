package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Pinky extends Rectangle {
    public static final long serialVersionUID = 4328743;

    public Pinky(int x, int y) {
        setBounds(x, y, 20, 20);
    }

    public void render(Graphics g) {
        g.setColor(Color.PINK);
        g.fillRect(x, y, width, height);
    }

}
