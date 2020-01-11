package ghost;

import game.Game;
import game.SpriteSheet;
import game.Unit;

// import java.awt.Color;
import java.awt.Graphics;
// import java.awt.Rectangle;

public class Inky extends Ghost {
    public static final long serialVersionUID = 4328743;

    public Inky(int x, int y, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet);
    }
    
    @Override
    public String getType() {
        return "i";
    }

    @Override
    public void move() {

    }
}
