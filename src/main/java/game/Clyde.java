package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Clyde extends Rectangle {
    public static final long serialVersionUID = 4328743;

    public Clyde(int x, int y) {
        setBounds(x, y, 20, 20);
    }

    public void render(Graphics g) {
        SpriteSheet sheet = Game.clydeSprite;
        g.drawImage(sheet.getSprite(0,0),x,y,18,18, null);
        //g.setColor(Color.ORANGE);
        //g.fillRect(x, y, width, height);
    }

}
