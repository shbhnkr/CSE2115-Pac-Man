package ghost;

import game.Game;
import game.MoveBuilder;
import game.SpriteSheet;
import game.Unit;

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
    public void move() {
        Random rand = new Random();
        int random = rand.nextInt(4);

        switch (random) {
            case 0:
                if (this.getLocation().y == 0) {
                    // TODO getHeight may not work as intended now since the code was moved from Game to Randy
                    Point point = new Point(this.getLocation().x, (int) getHeight() - 20);
                    this.moveGhost(point);
                }
                this.moveUpGhost();
                break;
            case 1:
                if (this.getLocation().y == getHeight() - 20) {
                    Point point = new Point(this.getLocation().x, 0);
                    this.moveGhost(point);
                }
                this.moveDownGhost();
                break;
            case 2:
                if (this.getLocation().x == 0) {
                    Point point = new Point((int) getWidth() - 20, this.getLocation().y);
                    this.moveGhost(point);
                }
                this.moveLeftGhost();
                break;
            default:
                if (this.getLocation().x == getWidth() - 20) {
                    Point point = new Point(0, this.getLocation().y);
                    this.moveGhost(point);
                }
                this.moveRightGhost();
                break;
        }
    }

    public static int xPixelGhost = 0;
    public static int yPixelGhost = 0;
    public static int coolDown = 300;


    public void moveGhost(Point movePosition) {
        this.setLocation((int) movePosition.getX(), (int) movePosition.getY());
    }

//    public boolean hasCollided(Player player) {
//        if (player == null) {
//            return false;
//        }
//        return (this.getLocation().x == player.getLocation().x && this.getLocation().y == player.getLocation().y);
//    }

    public void moveUpGhost() {
        moveGhost(MoveBuilder.UP(getLocation()));
        xPixelGhost = 0;
        yPixelGhost = 0;
        switch (pixels[getLocation().x / 20][getLocation().y / 20]) {
            case '#':
                moveGhost(MoveBuilder.DOWN(getLocation()));
                break;
//            case 'p':
//                System.out.println("PLAYER SHOULD DIE NOW");
//                break;
            default:
                break;
        }
    }

    public void moveDownGhost() {
        moveGhost(MoveBuilder.DOWN(getLocation()));
        xPixelGhost = 0;
        yPixelGhost = 32;
        switch (pixels[getLocation().x / 20][getLocation().y / 20]) {
            case '#':
                moveGhost(MoveBuilder.UP(getLocation()));
                break;
//            case 'p':
//                System.out.println("PLAYER SHOULD DIE NOW");
//                break;
            default:
                break;
        }
    }

    public void moveLeftGhost() {
        moveGhost(MoveBuilder.LEFT(getLocation()));
        xPixelGhost = 0;
        yPixelGhost = 48;
        switch (pixels[getLocation().x / 20][getLocation().y / 20]) {
            case '#':
                moveGhost(MoveBuilder.RIGHT(getLocation()));
                break;
//            case 'p':
//                System.out.println("PLAYER SHOULD DIE NOW");
//                break;
            default:
                break;
        }
    }

    public void moveRightGhost() {
        moveGhost(MoveBuilder.RIGHT(getLocation()));
        xPixelGhost = 0;
        yPixelGhost = 16;
        switch (pixels[getLocation().x / 20][getLocation().y / 20]) {
            case '#':
                moveGhost(MoveBuilder.LEFT(getLocation()));
                break;
//            case 'p':
//                System.out.println("PLAYER SHOULD DIE NOW");
//                break;
            default:
                break;
        }
    }
    @Override
    public String getType() {
        return "r";
    }
}
