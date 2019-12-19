package game;

// import java.awt.Color;
import java.awt.Graphics;
// import java.awt.Rectangle;

/**
 * ghost 2.
 */
public class Clyde extends Unit {
    public static final long serialVersionUID = 4328743;

    /**
     * ghost constructor 2.
     */
    public Clyde(int x, int y) {
        setBounds(x, y, 20, 20);
    }

    /**
     * ghost render 2.
     */
    public void render(Graphics g) {
        SpriteSheet sheet = Game.clydeSprite;
        g.drawImage(sheet.getSprite(0,0),x,y,18,18, null);
        //g.setColor(Color.ORANGE);
        //g.fillRect(x, y, width, height);
    }

    @Override
    String getType() {
        return "c";
    }
}
