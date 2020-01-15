package ghost;

import game.SpriteSheet;
import game.Types;

import java.awt.Point;

import static game.Level.pixels;

public class Blinky extends Ghost {
    /**
     * ghost 1.
     */
    public static final long serialVersionUID = 4328743;

    /**
     * ghost constructor 1.
     */
    Blinky(int x, int y, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet);
    }

    @Override
    public void moveGhost(int height, int width) {
        Point destination = this.unitLocations.get(Types.playerType());
        if (unitLocations.isEmpty() || destination == null) {
            System.out.println("no destination!");
            return;
        }
        if (getLocation().y != 0 && pixels[getLocation().x / 20][(getLocation().y - 20) / 20] == '#') {

        }
        if (getLocation().x != 0 && pixels[(getLocation().x - 20) / 20][getLocation().y / 20] == '#') {

        }
        if (getLocation().y != height - 20 && pixels[getLocation().x / 20][(getLocation().y + 20) / 20] == '#') {

        }
        if (getLocation().x != width - 20 && pixels[(getLocation().x + 20) / 20][getLocation().y / 20] == '#') {
            
        }
    }

    @Override
    public String getType() {
        return "b";
    }
}

