package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Inky extends Rectangle {
    public static final long serialVersionUID = 4328743;

    public Inky(int x, int y) {
        setBounds(x, y, 20, 20);
    }

    /**
     * render inky.
     * @param g - graphics
     */
    public void render(Graphics g) {
        SpriteSheet sheet = Game.inkySprite;
        g.drawImage(sheet.getSprite(0,0),x,y,18,18, null);
        //g.setColor(Color.CYAN);
        //g.fillRect(x, y, width, height);
    }

}
