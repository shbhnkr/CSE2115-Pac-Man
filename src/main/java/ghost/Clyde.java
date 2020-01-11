package ghost;

import game.Game;
import game.SpriteSheet;
import game.Unit;

// import java.awt.Color;
import java.awt.Graphics;
// import java.awt.Rectangle;

/**
 * ghost 2.
 */
public class Clyde extends Ghost {
    public static final long serialVersionUID = 4328743;

    public Clyde(int x, int y, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet);
    }

    @Override
    public void move() {

    }

    @Override
    public String getType() {
        return "c";
    }
}
