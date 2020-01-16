package ghost;

import game.MoveBuilder;
import game.SpriteSheet;

import java.awt.*;
import java.util.Random;

import static game.Level.pixels;

/**
 * ghost 5.
 */
public class Randy extends Ghost {
    public static final long serialVersionUID = 4328743;

    public Randy(int x, int y, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet);
    }

    @Override
    public void moveGhost(int height, int width) {
        Random rand = new Random();
        int random = rand.nextInt(4);

        switch (random) {
            case 0:
                this.moveUpGhost(height);
                break;
            case 1:
                this.moveLeftGhost(width);
                break;
            case 2:
                this.moveDownGhost(height);
                break;
            default:
                this.moveRightGhost(width);
                break;
        }
    }

    @Override
    public String getType() {
        return "r";
    }
}
