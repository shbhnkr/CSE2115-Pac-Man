package game;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends Rectangle {
    public static final long serialVersionUID = 4328743;

    public Player(int x, int y) {
        setBounds(x, y, 20, 20);
    }

    /**
     * renders the player on board.
     * @param g Graphics
     */
    public void render(Graphics g) {
        SpriteSheet sheet = Game.spriteSheet;
        g.drawImage(sheet.getSprite(0,0),x,y, null);
        //g.setColor(Color.YELLOW);
        //g.fillRect(x, y, width, height);
    }

}
