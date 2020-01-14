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
                if (this.getLocation().y == 0) {
                    Point point = new Point(this.getLocation().x, height - 20);
                    this.move(point);
                    return;
                }
                this.moveUpGhost();
                break;
            case 1:
                if (this.getLocation().y == height - 20) {
                    Point point = new Point(this.getLocation().x, 0);
                    this.move(point);
                    return;
                }
                this.moveDownGhost();
                break;
            case 2:
                if (this.getLocation().x == 0) {
                    Point point = new Point( width - 20, this.getLocation().y);
                    this.move(point);
                    return;
                }
                this.moveLeftGhost();
                break;
            default:
                if (this.getLocation().x == width - 20) {
                    Point point = new Point(0, this.getLocation().y);
                    this.move(point);
                    return;
                }
                this.moveRightGhost();
                break;
        }
    }

    @Override
    public String getType() {
        return "r";
    }
}
